package com.example.taco;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	
	private IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	/**
	 * add ingredients to taco model.
	 * model is the taco being desined.
	 * @param model
	 */
	@ModelAttribute
	public void addIngridientToModel (Model model){
		Iterable<Ingredient> ingredients = ingredientRepo.findAll();
		
		Ingredient.Type[] types = Ingredient.Type.values();
		for(Ingredient.Type elem : types) {
			model.addAttribute(elem.toString().toLowerCase(),
					filterByType((List<Ingredient>) ingredients, elem));
		}
	}
	
	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	
	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		
		if(errors.hasErrors()) {
			//log.info("taco has errors: {}", taco);
			return "design";
		}
		
		tacoOrder.addTaco(taco);
		log.info("processing taco: {}", taco);
		
		return "redirect:/orders/current";
	}
	
	private Iterable<Ingredient> filterByType(
		List<Ingredient> ingredients, Ingredient.Type type) {
			return ingredients
					.stream()
					.filter(x -> x.getType().equals(type))
					.collect(Collectors.toList());
	}

}
