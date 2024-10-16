package com.example.taco;

import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;

@Data
public class TacoOrder implements Serializable{

	private static final long serialVersionUID = 619226869520249012L;
	
	private Long orderId;
	
	public Date datePlaced;

	@NotBlank(message="Delivery name is requied")
	private String deliveryName;
	
	@NotBlank(message="Delivery street is requied")
	private String deliveryStreet;
	
	@NotBlank(message="Delivery city is requied")
	private String deliveryCity;
	
	@NotBlank(message="Delivery state is requied")
	private String deliveryState;
	
	@NotBlank(message="Delivery zip is requied")
	private String deliveryZip;
	
	@CreditCardNumber(message="Enter a valid credit card number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$", message="Enter valid ccExpiration in form of MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Enter a valid ccCVV")
	private String ccCVV;
	
	private List<Taco> tacos = new ArrayList<>();
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}