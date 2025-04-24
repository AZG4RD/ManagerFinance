package FinanceApp.service.Account;

import FinanceApp.dto.Account.AccountRequest;
import FinanceApp.dto.Account.AccountResponse;
import FinanceApp.model.Account;
import FinanceApp.model.User;
import FinanceApp.repository.AccountRepository;
import FinanceApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private String getEmailName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private User getCurrentUser(){
        Optional<User> userOptional = userRepository.findByEmailAndIsDeletedFalse(getEmailName());
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return userOptional.get();
    }

    @Transactional
    @Override
    public AccountResponse create(AccountRequest accountRequest) {
        User user = getCurrentUser();
        Account account = modelMapper.map(accountRequest, Account.class);
        account.setUserId(user.getUserId());
        Account savedAccount = accountRepository.save(account);
        return modelMapper.map(savedAccount, AccountResponse.class);
    }

    @Transactional
    @Override
    public AccountResponse update(Long accountId, AccountRequest accountRequest) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(!accountOptional.isPresent()){
            throw new RuntimeException("Аккаунт не найден");
        }
        Account account = accountOptional.get();
        account.setName(accountRequest.getName());
        account.setBalance(accountRequest.getBalance());
        Account updatedAccount = accountRepository.save(account);
        return modelMapper.map(updatedAccount, AccountResponse.class);
    }

    @Override
    public AccountResponse get(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(!accountOptional.isPresent()){
            throw new RuntimeException("Аккаунт не найден");
        }
        return modelMapper.map(accountOptional.get(), AccountResponse.class);
    }

    @Override
    public List<AccountResponse> getAll() {
        User user = getCurrentUser();
        List<AccountResponse> responseList = new ArrayList<>();
        Iterable<Account> accounts = accountRepository.findAll();
        for(Account account : accounts){
            if(account.getUserId().equals(user.getUserId())){
                responseList.add(modelMapper.map(account, AccountResponse.class));
            }
        }
        return responseList;
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(!accountOptional.isPresent()){
            throw new RuntimeException("Аккаунт не найден");
        }
        accountRepository.deleteById(accountId);
        return ResponseEntity.ok("Аккаунт удален успешно");
    }
}
