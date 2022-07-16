package com.coinskash.controller;

import com.coinskash.payout.Payout;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "payouts")
public class PayoutController {
    private Payout fincraPayout(@RequestBody Payout payout) {
        /*
         call the end point on fincra to confirm the account number matches the user

         call the fincra end point
         that will transfer payment to the user
         using the the details in this end point
         also validate the requestbody and the corresponding user


         */
        return payout;
    }
}
