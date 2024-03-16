package br.com.lacerda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuppotedMathOperationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UnsuppotedMathOperationException(String ex) {
		super(ex);
	}
	
}
