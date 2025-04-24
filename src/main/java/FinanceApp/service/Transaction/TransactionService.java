package FinanceApp.service.Transaction;

import FinanceApp.dto.Transaction.TransactionRequest;
import FinanceApp.dto.Transaction.TransactionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(TransactionRequest transactionRequest);

    TransactionResponse update(Long transactionId, TransactionRequest transactionRequest);

    TransactionResponse get(Long transactionId);

    List<TransactionResponse> getAllTransactions();

   ResponseEntity<String> delete(Long transactionId);

}
