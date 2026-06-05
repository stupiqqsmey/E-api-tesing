package ite_istad_product.product_demo.restcontroller;

import ite_istad_product.product_demo.dto.CategoryRequest;
import ite_istad_product.product_demo.dto.CategoryResponse;
import ite_istad_product.product_demo.dto.UpdateCategoryRequest;
import ite_istad_product.product_demo.repository.CategoryRepository;
import ite_istad_product.product_demo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    /// inject method

    @GetMapping
    public List<CategoryResponse> getCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryByID(@PathVariable Integer id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Integer id, @RequestBody UpdateCategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }
}