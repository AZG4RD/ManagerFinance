package FinanceApp.controller.Account;

import FinanceApp.dto.Account.AccountRequest;
import FinanceApp.dto.Account.AccountResponse;
import FinanceApp.service.Account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public AccountResponse create(@RequestBody @Valid AccountRequest accountRequest) {
        return accountService.create(accountRequest);
    }

    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable Long id) {
        return accountService.get(id);
    }

    @GetMapping
    public List<AccountResponse> getAll() {
        return accountService.getAll();
    }

    @PutMapping("/{id}")
    public AccountResponse update(@PathVariable Long id, @RequestBody @Valid AccountRequest accountRequest) {
        return accountService.update(id, accountRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return accountService.delete(id);
    }
}
