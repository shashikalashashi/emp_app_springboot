package com.jsp.emp.service.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class InvalidCredentialException extends RuntimeException {
	
	private String message;

	public InvalidCredentialException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage()
	{
		return this.message;
		
	}

}
