package com.example.taco;

import java.util.List;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class Taco {
	
	@NotNull
	@Size(min=5, message="must have atleast 5 letters.")
	private String name;
	
	@NotNull
	@Size(min=1, message="must have atleast 1 ingredient.")
	private List<Ingredient> ingredients; 
}
