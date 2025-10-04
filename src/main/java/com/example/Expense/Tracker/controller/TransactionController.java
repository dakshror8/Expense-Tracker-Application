package com.example.Expense.Tracker.controller;

import com.example.Expense.Tracker.dto.TransactionRequestDto;
import com.example.Expense.Tracker.dto.TransactionDto;
import com.example.Expense.Tracker.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionDto>> getTransaction(@PathVariable Long userId){
        return ResponseEntity.ok(transactionService.getTransactions(userId));
    }

    @GetMapping(path = "/user/{userId}/transaction/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long userId,
                                                             @PathVariable("transactionId") Long t_id){
        return ResponseEntity.ok(transactionService.getTransactionById(userId,t_id));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> addNewTransaction(
            @RequestBody @Valid TransactionRequestDto addNewTransactionDto,
            Long userId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.addTransaction(addNewTransactionDto, userId));
    }

    @DeleteMapping(path = "/user/{userID}/transaction/{transactionId}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long userId,
                                                      @PathVariable("transactionId") Long t_id){
        transactionService.deleteTransactionById(userId, t_id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/user/{userID}/transaction/{transactionId}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long userId,
                                                            @PathVariable("transactionId") Long t_id,
                                                        @RequestBody @Valid TransactionRequestDto transactionRequestDto){
        return ResponseEntity.ok(transactionService.updateTransaction(userId, t_id, transactionRequestDto));
    }

    @PatchMapping(path = "/user/{userId}/transaction/{transactionId}")
    public ResponseEntity<TransactionDto>  updateTransactionPartially(@PathVariable Long userId,
                                                                      @PathVariable("transactionId") Long id,
                                                                      @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(transactionService.updatePartially(userId,id,updates));
    }
}
