package FinanceApp.service.Account;

import FinanceApp.dto.Account.AccountRequest;
import FinanceApp.dto.Account.AccountResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface AccountService {
    AccountResponse create(AccountRequest accountRequest);
    AccountResponse update(Long accountId, AccountRequest accountRequest);
    AccountResponse get(Long accountId);
    List<AccountResponse> getAll();
    ResponseEntity<String> delete(Long accountId);
}
