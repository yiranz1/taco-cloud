//package com.yiran.tacocloud.repository;
//
//import java.util.Optional;
//
//import com.yiran.tacocloud.models.Ingredient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class IngredientByIdConverter implements Converter<String, Ingredient> {
//
//    private final IngredientRepository ingredientRepo;
//
//    @Autowired
//    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
//        this.ingredientRepo = ingredientRepo;
//    }
//
//    @Override
//    public Ingredient convert(String id) {
//        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
//        return optionalIngredient.isPresent() ?
//                optionalIngredient.get() : null;
//    }
//
//}