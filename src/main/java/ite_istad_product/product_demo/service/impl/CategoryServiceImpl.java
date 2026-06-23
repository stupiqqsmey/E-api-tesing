package ite_istad_product.product_demo.service.impl;

import ite_istad_product.product_demo.advisor.ResourceAlreadyExistException;
import ite_istad_product.product_demo.dto.CategoryRequest;
import ite_istad_product.product_demo.dto.UpdateCategoryRequest;
import ite_istad_product.product_demo.dto.CategoryResponse;
import ite_istad_product.product_demo.entity.Category;
import ite_istad_product.product_demo.mapper.CategoryMapper;
import ite_istad_product.product_demo.repository.CategoryRepositoryNew;
import ite_istad_product.product_demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepositoryNew categoryRepository;

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        if (request.parentCategoryId() != null) {
            /// chek if it exists
        var parentCategory = categoryRepository.findById(request.parentCategoryId()).orElseThrow(()-> new NoSuchElementException(
                ("Parent category with id="+request.parentCategoryId()+"donest exists !")));
        category.setParentCategory(parentCategory);
        }
        // derived query
        if(categoryRepository.existsByName(request.name())){
            throw new ResourceAlreadyExistException("Category with name = "+request.name()+" already exists");
        }

        var newCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(newCategory);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<CategoryResponse> findAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findByName(String name) {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getName().toLowerCase().contains(name.toLowerCase()))
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id = " + id + " does not exist"));
        return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest updateCategoryrequest) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id = " + id + " does not exist"));

        if (updateCategoryrequest.name() != null) {
            existingCategory.setName(updateCategoryrequest.name());
        }

        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toResponse(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id = " + id + " does not exist"));
        categoryRepository.save(category);
        log.info("Category with id {} soft-deleted successfully", id);
    }
}