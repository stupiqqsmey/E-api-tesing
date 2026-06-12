package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.CategoryRequest;
import ite_istad_product.product_demo.dto.UpdateCategoryRequest;
import ite_istad_product.product_demo.dto.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    Page<CategoryResponse> findAllCategories(int page, int size);
    List<CategoryResponse> findByName(String name);
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest updateCategoryrequest);
    void deleteCategory(Integer id);
}