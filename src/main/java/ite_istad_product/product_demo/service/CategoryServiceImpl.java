package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.CategoryRequest;
import ite_istad_product.product_demo.dto.UpdateCategoryRequest;
import ite_istad_product.product_demo.dto.CategoryResponse;
import ite_istad_product.product_demo.entity.Category;
import ite_istad_product.product_demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    /// inject category repository here
    private final CategoryRepository categoryRepository;
    private Integer nextId = 5; // Starting at 5 since your mock list has 4 items

    /// map to entity
    private Category mapToCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        category.setActive(categoryRequest.isActive());

        return category;
    }

    /// mapToResponse -> convert to DTO
    private CategoryResponse mapToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.isActive()
        );
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        var category = mapToCategory(categoryRequest);
        category.setId(nextId++);
        categoryRepository.createCategory(category); // Add to the mock list
        return mapToCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.getCategorieslist()
                .stream()
                .map(this::mapToCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        var category = categoryRepository.findCategoryById(id);
        if(category == null) {
            // throw not found exception, but skip it for now
            log.info("Category with id {} not found", id);
            return null;
        }
        return mapToCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest updateCategoryrequest) {
        var existingCategory = categoryRepository.findCategoryById(id);
        if(existingCategory == null) {
            log.info("No category found with id " + id);
            // throw exception
            return null;
        }

        if(updateCategoryrequest.name() != null)
            existingCategory.setName(updateCategoryrequest.name());
        if(updateCategoryrequest.description() != null)
            existingCategory.setDescription(updateCategoryrequest.description());
        if(updateCategoryrequest.isActive() != null)
            existingCategory.setActive(updateCategoryrequest.isActive());

        // update category
        categoryRepository.UpdateCategory(existingCategory);
        return mapToCategoryResponse(existingCategory);
    }

    @Override
    public boolean deleteCategory(int id) {
        return categoryRepository.deleteCategoryById(id);
    }
}