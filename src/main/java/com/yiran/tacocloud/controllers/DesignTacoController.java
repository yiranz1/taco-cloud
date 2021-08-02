package com.yiran.tacocloud.controllers;

import com.yiran.tacocloud.models.Ingredient;
import com.yiran.tacocloud.models.Ingredient.Type;
import com.yiran.tacocloud.models.Order;
import com.yiran.tacocloud.models.Taco;
import com.yiran.tacocloud.repository.IngredientRepository;
import com.yiran.tacocloud.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepository) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        return "design";
    }

    /**
     * Handle POST request to "/design"
     * @Valid: tells Spring MVC to validate the Taco object
     *         校验时机： 绑定完表单数据之后，调用processDesign()之前
     * @param design: submitted by the form
     * @param errors: validation errors
     * @return redirect to /orders/current if no validation error occurs, otherwise display the error
     */
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        // Save taco design, will be done in chapter 3
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
