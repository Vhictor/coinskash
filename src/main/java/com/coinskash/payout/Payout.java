package com.coinskash.payout;

import com.coinskash.config.PropertiesConfig;
import com.coinskash.crypto.TransactionRecord;
import com.coinskash.model.AppUser;
import com.coinskash.model.payout.PayoutData;
import com.coinskash.model.payout.beneficiary.BeneficiaryPayout;
import com.coinskash.repository.BeneficiaryPayoutRepository;
import com.coinskash.repository.TransactionRepository;
import com.coinskash.response.PayoutResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class Payout {

    private WebClient webClient;
    private TransactionRepository transactionRepository;
    private BeneficiaryPayoutRepository beneficiaryPayoutRepository;
    private PayoutData payoutData;
    private PropertiesConfig propertiesConfig;

    @Autowired
    public Payout(
            WebClient webClient,
            TransactionRepository transactionRepository,
            BeneficiaryPayoutRepository beneficiaryPayoutRepository,
            PropertiesConfig propertiesConfig
    ) {
        this.webClient = webClient;
        this.transactionRepository = transactionRepository;
        this.beneficiaryPayoutRepository = beneficiaryPayoutRepository;
        this.propertiesConfig = propertiesConfig;
    }

    public PayoutResponse payout() {
        /*
         *  get the payout data from a utility
         *  that returns the transaction to process
         *  from the database
         *  remember this utility must return only a single
         *  transaction to process each time the
         *  scheduler runs
         * */
        log.info("checking for unprocessed pay out...");

        PayoutData payoutData = getPayoutData();
        PayoutResponse payoutResponse = webClient.post()
                .uri(propertiesConfig.getPayoutUrl())
                .accept(MediaType.APPLICATION_JSON)
                .header("api-key", propertiesConfig.getFincraAccountApiKey())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(payoutData), PayoutData.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(PayoutResponse.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                }).block();
        return payoutResponse;

    }

    private Optional<TransactionRecord> getUnpaidTransaction() {
        return transactionRepository.
                findByHasPaidCryptoAndHasPayout(true, false).
                stream().
                findFirst();
    }

    private PayoutData getPayoutData() {
        //connect to the database and return the payout data
        if (getUnpaidTransaction().isPresent()) {
            log.info("unprocessed payout found");
            TransactionRecord transactionRecord = getUnpaidTransaction().get();
            AppUser appUserDetails = transactionRecord.getAppUser();
            BeneficiaryPayout beneficiaryPayout = beneficiaryPayoutRepository.findByAppUserId(appUserDetails.getId());

            //return appUserDetails;
            payoutData.setAmount(transactionRecord.getAmountInFiat());
            payoutData.setBeneficiary(beneficiaryPayout);
            payoutData.setCustomerReference(UUID.randomUUID().toString());
            return payoutData;
        }

        log.info("no unprocessed payout found");
        return null;
    }
}
