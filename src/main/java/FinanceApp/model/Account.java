package FinanceApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "accounts")
public class Account {
    @Id
    private Long accountId;
    private Long userId;
    @Column(value = "name")
    private String Name;
    @Column(value = "balance")
    private BigDecimal Balance;
}
