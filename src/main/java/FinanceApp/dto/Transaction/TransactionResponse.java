package FinanceApp.dto.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long transactionId;
    private Long userId;
    private Long categoryId;
    private Long accountId;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private String type;
}
