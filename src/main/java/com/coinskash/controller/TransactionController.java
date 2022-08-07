package com.coinskash.controller;

import com.coinskash.crypto.TransactionRecord;
import com.coinskash.repository.TransactionRepository;
import com.coinskash.service.ImplTransactionService;
import com.coinskash.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @GetMapping("/transactionRecord")
    public List<TransactionRecord> getTransactionRecord(@PathVariable("id") Long id){
        return transactionService.getAllTransactionByUserId(id).get();
    }
}
