package FinanceApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "transactions")
public class Transaction {
    @Id
    private Long transactionId;
    private Long userId;
    private Long accountId;
    private Long categoryId;
    @Column(value = "amount")
    private BigDecimal amount;
    @Column(value = "date")
    private LocalDate date;
    @Column(value = "type")
    private String type;//Так же доход/расходы
    @Column(value = "description")
    private String description;

}
