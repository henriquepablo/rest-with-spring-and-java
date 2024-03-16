package br.com.lacerda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lacerda.Convert;
import br.com.lacerda.exceptions.UnsuppotedMathOperationException;

@RestController
@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
public class SumController {
	
	@GetMapping
	public Double sum(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception {
		
		if (!Convert.isNumeric(numberOne) || !Convert.isNumeric(numberTwo)){
			throw new UnsuppotedMathOperationException("Please set a numeric value");
		}
		
		return  Convert.convertToDouble(numberOne) + Convert.convertToDouble(numberTwo);
	}
}
