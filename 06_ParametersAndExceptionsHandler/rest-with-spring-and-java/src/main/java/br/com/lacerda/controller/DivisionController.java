package br.com.lacerda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lacerda.Convert;
import br.com.lacerda.exceptions.UnsuppotedMathOperationException;

@RestController
@RequestMapping(value = "/div/{numberOne}/{numberTwo}", method=RequestMethod.GET)
public class DivisionController {
	
	@GetMapping
	public Double division(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception {
		
		if (!Convert.isNumeric(numberOne) || !Convert.isNumeric(numberTwo)){
			throw new UnsuppotedMathOperationException("Please set a numeric value");
		}
		
		if (numberTwo.equals("0")) {
			throw new UnsuppotedMathOperationException("The second number cannot be zero");
		}
		
		return  Convert.convertToDouble(numberOne) / Convert.convertToDouble(numberTwo);
	}

}
