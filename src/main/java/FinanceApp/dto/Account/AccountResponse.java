package FinanceApp.dto.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountResponse {
    private Long accountId;
    private Long userId;
    private String name;
    private BigDecimal balance;
}
