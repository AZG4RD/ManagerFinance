package FinanceApp.service.Category;

import FinanceApp.dto.Category.CategoryRequest;
import FinanceApp.dto.Category.CategoryResponse;
import FinanceApp.exception.Category.CategoryNotFoundException;
import FinanceApp.model.Category;
import FinanceApp.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Transactional
    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = modelMapper.map(categoryRequest, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryResponse.class);
    }

    @Transactional
    @Override
    public CategoryResponse update(Long categoryId, CategoryRequest categoryRequest) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()) {
            throw new CategoryNotFoundException("Категория не найдено");
        }
        Category category = optionalCategory.get();
        category.setName(categoryRequest.getName());
        category.setType(categoryRequest.getType());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryResponse.class);
    }

    @Override
    public CategoryResponse get(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()) {
            throw new CategoryNotFoundException("Category not found");
        }
        return modelMapper.map(optionalCategory.get(), CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<CategoryResponse> responses = new ArrayList<>();
        Iterable<Category> iterable = categoryRepository.findAll();
        for (Category category : iterable) {
            responses.add(modelMapper.map(category, CategoryResponse.class));
        }
        return responses;
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()) {
            throw new CategoryNotFoundException("Category not found");
        }
        categoryRepository.deleteById(categoryId);
        return ResponseEntity.ok("Категория успешно удалена");
    }
}
