package com.coinskash.scheduler;

import com.coinskash.payout.Payout;
import com.coinskash.response.PayoutResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class PayoutScheduler {
    @Autowired
    private Payout payout;
    @Scheduled(cron = "0 */1 * * * *")
    private PayoutResponse fincraPayout() {
        log.info("scheduler started, searching for unprocessed transaction...");
        return payout.payout();
    }
}
