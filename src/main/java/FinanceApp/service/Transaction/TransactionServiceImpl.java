package FinanceApp.service.Transaction;

import FinanceApp.dto.Transaction.TransactionRequest;
import FinanceApp.dto.Transaction.TransactionResponse;
import FinanceApp.exception.Transaction.TransactionNotFoundException;
import FinanceApp.model.Transaction;
import FinanceApp.model.User;
import FinanceApp.repository.TransactionRepository;
import FinanceApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private String getEmailName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private User getCurrentUser() {
        Optional<User> userOptional = userRepository.findByEmailAndIsDeletedFalse(getEmailName());
        if (!userOptional.isPresent()) {
            throw new TransactionNotFoundException("Авторизация пользователя не найдена");
        }
        return userOptional.get();
    }

    @Override
    public TransactionResponse create(TransactionRequest transactionRequest) {
        User user = getCurrentUser();
        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        transaction.setUserId(user.getUserid());
        Transaction savedTransaction = transactionRepository.save(transaction);
        return modelMapper.map(savedTransaction, TransactionResponse.class);
    }

    @Transactional
    @Override
    public TransactionResponse update(Long transactionId, TransactionRequest transactionRequest) {
        User user = getCurrentUser();
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (!transactionOptional.isPresent()) {
            throw new TransactionNotFoundException("Транзакция не найдена");
        }
        Transaction transaction = transactionOptional.get();
        if (!transaction.getUserId().equals(user.getUserid())){
            throw new TransactionNotFoundException("Транзакция не найдена для текущего пользователя");
        }
        return modelMapper.map(transactionRequest, TransactionResponse.class);
}
    @Override
    public TransactionResponse get(Long transactionId) {
        User user = getCurrentUser();
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (!transactionOptional.isPresent()) {
            throw new TransactionNotFoundException("Транзакция не найдена");
        }
        Transaction transaction = transactionOptional.get();
        if(!transaction.getUserId().equals(user.getUserid())){
            throw new TransactionNotFoundException("Транзакция не найдена для текущего пользователя");
        }
        return modelMapper.map(transaction, TransactionResponse.class);
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        User user = getCurrentUser();
        List<TransactionResponse> list = new ArrayList<>();
        Iterable<Transaction> allTransactions = transactionRepository.findAll();
        for (Transaction transaction : allTransactions) {
            if(transaction.getUserId().equals(user.getUserid())){
            list.add(modelMapper.map(transaction, TransactionResponse.class));
        }
    }
    return list;
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(Long transactionId) {
        User user = getCurrentUser();
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (!transactionOptional.isPresent()) {
            throw new TransactionNotFoundException("Транзакция не найдена");
        }
        Transaction transaction = transactionOptional.get();
        if(!transaction.getUserId().equals(user.getUserid())){
            throw new TransactionNotFoundException("Транзакция не найдена для текущего пользователя");
        }
        transactionRepository.deleteById(transactionId);
        return ResponseEntity.ok("Транзакция удалена успешно");
    }
}
