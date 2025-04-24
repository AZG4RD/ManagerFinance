package FinanceApp.dto.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {
    @NotNull
    @Size(min = 1, max = 64)
    private String firstname;
    private String lastname;
    private LocalDate birthday;
}
