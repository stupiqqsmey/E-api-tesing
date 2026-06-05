package ite_istad_product.product_demo.repository;

import ite_istad_product.product_demo.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CategoryRepository {

    // បង្កើតទិន្នន័យសិប្បនិម្មិត (Mock Data)
    private final List<Category> categoryList = new ArrayList<>(){{
        add(new Category(1, "Food", "ម្ហូបអាហារ", true));
        add(new Category(2, "Beverage", "ភេសជ្ជៈគ្រប់ប្រភេទ", true));
        add(new Category(3, "Snack", "ចំណីសម្រន់", true));
        add(new Category(4, "Electronic", "គ្រឿងអេឡិចត្រូនិក", false));
    }};

    // ទាញយក Category ទាំងអស់
    public List<Category> getCategorieslist() {
        return categoryList;
    }

    // បង្កើត Category ថ្មី
    public Category createCategory(Category category) {
        categoryList.add(category);
        return category;
    }

    // ស្វែងរកតាមរយៈ ID
    public Category findCategoryById(Integer id) {
        return categoryList.stream()
                .filter(category -> category.getId().equals(id)) // ប្រើ .equals() សម្រាប់ Integer គឺល្អជាង ==
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("រកមិនឃើញ Category ទេ...!!"));
    }

    // លុបតាមរយៈ ID
    public boolean deleteCategoryById(Integer id) {
        return categoryList.removeIf(category -> category.getId().equals(id));
    }

    // ធ្វើបច្ចុប្បន្នភាព (Update)
    public Category UpdateCategory(Category updatecategory) {
        for (int i = 0; i < categoryList.size(); i++) {
            var category = categoryList.get(i);
            if (category.getId().equals(updatecategory.getId())) {
                categoryList.set(i, updatecategory);
                return updatecategory;
            }
        }
        return null;
    }
}