package ite_istad_product.product_demo.restcontroller;

import ite_istad_product.product_demo.dto.CategoryRequest;
import ite_istad_product.product_demo.dto.CategoryResponse;
import ite_istad_product.product_demo.dto.UpdateCategoryRequest;
import ite_istad_product.product_demo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public Page<CategoryResponse> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return categoryService.findAllCategories(page, size);
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
    public CategoryResponse updateCategory(
            @PathVariable Integer id,
            @RequestBody UpdateCategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}