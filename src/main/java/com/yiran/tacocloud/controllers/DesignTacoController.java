package com.yiran.tacocloud.controllers;

import com.yiran.tacocloud.models.Ingredient;
import com.yiran.tacocloud.models.Ingredient.Type;
import com.yiran.tacocloud.models.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("APPL", "Apple", Type.SAUCE),
                new Ingredient("MOMO", "MoMo", Type.WRAP),
                new Ingredient("BING", "BingZi", Type.WRAP),
                new Ingredient("BEEF", "Beef", Type.PROTEIN),
                new Ingredient("PORK", "Pork", Type.PROTEIN),
                new Ingredient("TUDOU", "patato", Type.VEGGIES),
                new Ingredient("RUISHI", "Ruishi Nailao", Type.CHEESE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
