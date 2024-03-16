package br.com.lacerda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lacerda.Convert;
import br.com.lacerda.exceptions.UnsuppotedMathOperationException;

@RestController
@RequestMapping(value = "/square/{numberOne}", method=RequestMethod.GET)
public class SquareRootController {

	@GetMapping
	public Double squareRoot(
			@PathVariable(value="numberOne") String numberOne
			) throws Exception{
		
		if (!Convert.isNumeric(numberOne)) {
			throw new UnsuppotedMathOperationException("Please set a numeric value");
		}
		
		return Math.sqrt(Convert.convertToDouble(numberOne));
	}
}
