package FinanceApp.controller.Transaction;

import FinanceApp.dto.Transaction.TransactionRequest;
import FinanceApp.dto.Transaction.TransactionResponse;
import FinanceApp.service.Transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public TransactionResponse create(@RequestBody @Valid TransactionRequest transactionRequest) {
        return transactionService.create(transactionRequest);
    }

    @GetMapping("/{id}")
    public TransactionResponse get(@PathVariable Long id) {
        return transactionService.get(id);
    }

    @GetMapping
    public List<TransactionResponse> getAll() {
       return transactionService.getAllTransactions();
    }

    @PutMapping("/{id}")
    public TransactionResponse update(@PathVariable Long id, @RequestBody @Valid TransactionRequest transactionRequest) {
        return transactionService.update(id, transactionRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return transactionService.delete(id);
    }
}
