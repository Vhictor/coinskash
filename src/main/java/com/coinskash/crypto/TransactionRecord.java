package com.coinskash.crypto;

import com.coinskash.model.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@AllArgsConstructor
@Data
@Entity
@Table(name = "transaction_record")
@NoArgsConstructor
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String transactionReference;
    private Double amountInCoin;
    private Double amountInFiat;
    private String coin;
    private String senderAddress;
    private Boolean hasPayout;
    private Boolean hasPaidCrypto;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AppUser appUser;

    public TransactionRecord(
            String transactionReference,
            Boolean hasPayout,
            Boolean hasPaidCrypto
    ) {
        this.transactionReference = transactionReference;
        this.hasPayout = hasPayout;
        this.hasPaidCrypto = hasPaidCrypto;
    }
}
