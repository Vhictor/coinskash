package com.coinskash.model.payout.beneficiary;

import com.coinskash.model.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Data
@Entity(name = "beneficiaries")
@Table
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String type;
    private String accountHolderName;
    private String accountNumber;
    @JsonIgnore
    private String country;
//    @JsonIgnore
//    private Address address;
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
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AppUser appUser;


}
