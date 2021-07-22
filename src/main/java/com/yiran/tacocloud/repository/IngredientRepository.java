package com.yiran.tacocloud.repository;

import com.yiran.tacocloud.models.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
