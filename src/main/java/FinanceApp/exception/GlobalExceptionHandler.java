package FinanceApp.exception;

import FinanceApp.dto.Error.ErrorDto;
import FinanceApp.dto.ErrorDto;
import FinanceApp.exception.Account.AccountNotFoundException;
import FinanceApp.exception.Category.CategoryNotFoundException;
import FinanceApp.exception.Transaction.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ErrorDto(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyDeletedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleUserAlreadyDeletedException(UserAlreadyDeletedException ex) {
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleAccountNotFoundException(AccountNotFoundException ex) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleTransactionNotFoundException(TransactionNotFoundException ex) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleGeneralException(Exception ex) {
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
    }
}
