package FinanceApp.service.Category;

import FinanceApp.dto.Category.CategoryRequest;
import FinanceApp.dto.Category.CategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest categoryRequest);
    CategoryResponse update(Long categoryId,CategoryRequest categoryRequest);
    CategoryResponse get(Long categoryId);
    List<CategoryResponse> getAll();
    ResponseEntity<String> delete(Long categoryId);
}
