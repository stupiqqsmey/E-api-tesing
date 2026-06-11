package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.advisor.ResourceAlreadyExistException;
import ite_istad_product.product_demo.dto.CategoryRequest;
import ite_istad_product.product_demo.dto.UpdateCategoryRequest;
import ite_istad_product.product_demo.dto.CategoryResponse;
import ite_istad_product.product_demo.entity.Category;
import ite_istad_product.product_demo.mapper.CategoryMapper;
import ite_istad_product.product_demo.repository.CategoryRepository;
import ite_istad_product.product_demo.repository.CategoryRepositoryNew;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepositoryNew categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        if (categoryRepository.existsByName(request.name())) {
            throw new ResourceAlreadyExistException("category with = " + request.name() + " already exists");
        }

        var newCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<CategoryResponse> findByName(String name) {
        return List.of();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        return null;
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest updateCategoryrequest) {
        return null;
    }

    @Override
    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new
                    NoSuchElementException("category with id = " + id + " does not exist");
        }
        categoryRepository.deleteById(id);
    }
}
