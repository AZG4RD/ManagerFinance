package FinanceApp.dto.Category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryRequest {
    @NotNull
    @Size(min = 1, max = 128)
    private String name;

    @NotNull
    private String type;
}
