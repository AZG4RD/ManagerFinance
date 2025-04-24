package FinanceApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "users")
public class User {
    @Id
    private Long userid;
    private String email;
    private String password;
    @Column(value = "first_name")
    private String firstname;
    @Column(value = "last_name")
    private String lastname;
    @Column(value = "birthday")
    private LocalDate birthday;
    @Column(value = "created_at")
    private LocalDateTime createdAt;
    @Column(value = "updated_at")
    private LocalDateTime updatedAt;
    @Column(value = "delete_at")
    private LocalDateTime deletedAt;
}
