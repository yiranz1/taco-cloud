package com.yiran.tacocloud.repository;

import com.yiran.tacocloud.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

// CrudRepository first parameter is entity type, second parameter is entity ID type
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
