package com.example.Expense.Tracker.service;

import com.example.Expense.Tracker.dto.AddTransactionRequestDto;
import com.example.Expense.Tracker.entity.User;
import com.example.Expense.Tracker.mapper.TransactionMapper;
import com.example.Expense.Tracker.dto.TransactionDto;
import com.example.Expense.Tracker.repo.TransactionRepository;
import com.example.Expense.Tracker.entity.Transaction;
import com.example.Expense.Tracker.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public List<TransactionDto> getTransactions(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException("User with id "+userId+" not found"));

        List<TransactionDto> list = user.getTransactions()
                .stream().map(transaction -> TransactionDto
                        .builder()
                        .id(transaction.getId())
                        .name(transaction.getName())
                        .type(transaction.getType())
                        .amount(transaction.getAmount())
                        .category(transaction.getCategory())
                        .created_at(transaction.getCreated_at())
                        .updated_at(transaction.getUpdated_at())
                        .build()).toList();

        return list;
    }

    public TransactionDto getTransactionById(Long userId, Long transactionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException(
                        "User with id "+userId+" not found"));

        Transaction transaction = transactionRepository.findById(transactionId).
                orElseThrow(() -> new IllegalStateException(
                        "transaction with id " + transactionId + " not found"));

        if(!transaction.getUser().getId().equals(userId)){
            throw new IllegalStateException("Transaction with id " + transactionId
                    + " does not belong to user with id " + userId);
        }

        return TransactionMapper.toTransactionDto(transaction);
    }

    @Transactional
    public TransactionDto addTransaction(AddTransactionRequestDto addNewTransactionDto,
                                         Long userId)
    {
        Transaction newTransaction = TransactionMapper.toTransaction(addNewTransactionDto);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id "+userId+" not found"));

        user.addTransaction(newTransaction);

        Transaction savedTransaction = transactionRepository.save(newTransaction); // to return auto generated values in dto.
        return TransactionMapper.toTransactionDto(savedTransaction);
    }

    @Transactional
    public void deleteTransactionById(Long userId, Long t_id) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id "+userId+" not found"));

        Transaction transaction = transactionRepository.findById(t_id).
                orElseThrow(() -> new IllegalStateException("Expnese with  id "+t_id+" not found"));

        if(!transaction.getUser().getId().equals(userId)){
            throw new IllegalStateException("Transaction with id " + t_id
                    + " does not belong to user with id " + userId);
        }
        user.removeTransaction(transaction);

    }

    @Transactional
    public TransactionDto updateTransaction(Long id, AddTransactionRequestDto addTransactionRequestDto) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        Transaction transaction = transactionOptional.
                orElseThrow(() -> new IllegalStateException("transaction with id " + id + " not found"));

        transaction.setName(addTransactionRequestDto.getName());
        transaction.setType(addTransactionRequestDto.getType());
        transaction.setAmount(addTransactionRequestDto.getAmount());
        transaction.setCategory(addTransactionRequestDto.getCategory());

        // no need to explicitly saving updated transaction to db. @Transactional takes care of it.
        return TransactionMapper.toTransactionDto(transaction);
    }

    public TransactionDto updatePartially(Long id, Map<String, Object> updates) {
        Transaction existingTransaction = transactionRepository.findById(id).
                orElseThrow(() -> new IllegalStateException("transaction with id: "+id+" not found"));
        updates.forEach((field,value) -> {
            switch(field){
                case "name":
                    existingTransaction.setName((String) value);
                    break;
                case "amount":
                    existingTransaction.setAmount((Double) value);
                    break;
                case "category":
                    existingTransaction.setCategory((String) value);
                    break;
                case "type":
                    existingTransaction.setType((String) value);
                    break;
                default:
                    throw new IllegalStateException("field not found.");
            }
        });
        Transaction savedTransaction = transactionRepository.save(existingTransaction); // managed instance
        return TransactionMapper.toTransactionDto(savedTransaction);
    }
}
