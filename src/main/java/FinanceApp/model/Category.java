package FinanceApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "categories")
public class Category {
    @Id
    private Long categoryId;
    private String Name;
    private String type; // Типом у меня будет доход/расход
}
