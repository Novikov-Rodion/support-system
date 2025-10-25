package com.example.support_system.service;

import com.example.support_system.model.Category;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoryService {
    private final Map<Long, Category> categories = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public Optional<Category> getCategoryById(Long id) {
        return Optional.ofNullable(categories.get(id));
    }

    public Category createCategory(Category category) {
        Long id = idCounter.getAndIncrement();
        category.setId(id);
        categories.put(id, category);
        return category;
    }

    public Optional<Category> updateCategory(Long id, Category categoryDetails) {
        if (categories.containsKey(id)) {
            Category existingCategory = categories.get(id);
            existingCategory.setName(categoryDetails.getName());
            existingCategory.setDescription(categoryDetails.getDescription());
            return Optional.of(existingCategory);
        }
        return Optional.empty();
    }

    public boolean deleteCategory(Long id) {
        return categories.remove(id) != null;
    }
}