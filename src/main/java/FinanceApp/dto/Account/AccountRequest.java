package FinanceApp.dto.Account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotNull
    @Size(min = 1, max = 128)
    private String name;

    @NotNull
    private BigDecimal balance;
}
