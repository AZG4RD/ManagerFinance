package FinanceApp.dto.Transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TransactionRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long accountId;
    @NotNull
    private Long categoryId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private LocalDate date;
    @NotNull
    private String type;
    @NotNull
    private String description;
}
