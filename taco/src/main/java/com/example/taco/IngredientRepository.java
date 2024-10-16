package com.example.taco;

import java.util.Optional;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	
	Optional<Ingredient> findById(String Id);
	
	Ingredient save(Ingredient ingredient);
}
