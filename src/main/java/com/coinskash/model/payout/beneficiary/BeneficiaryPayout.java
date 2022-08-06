package com.coinskash.model.payout.beneficiary;

import com.coinskash.model.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
@Data
@Entity(name = "beneficiaryPayout")
@Table
public class BeneficiaryPayout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    @Value("${payout.beneficiary.type}")
    private String type;
    private String accountHolderName;
    private String accountNumber;
    @JsonIgnore
    private String country;
//    @JsonIgnore
//    private Document document;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String mobileMoneyCode;
    @JsonIgnore
    private String bankCode;
    @JsonIgnore
    private String bankSwiftCode;
    @JsonIgnore
    private String sortCode;
    @JsonIgnore
    private String registrationNumber;
    @OneToOne
    @JoinColumn(name = "app_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AppUser appUser;


}
