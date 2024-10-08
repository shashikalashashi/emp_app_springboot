package com.jsp.emp.service.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoInActiveEmployeeFoundException extends RuntimeException {
	private String message;

	public NoInActiveEmployeeFoundException(String message) {
		this.message = message;
	}
	
	@Override
   public String getMessage()
   {
	   return this.message;
   }
}
