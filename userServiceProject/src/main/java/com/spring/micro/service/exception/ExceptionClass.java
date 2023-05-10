	package com.spring.micro.service.exception;
	
	public class ExceptionClass extends Exception{
	
		public class ResourceNotFoundException extends Exception{
			public ResourceNotFoundException(String message) {
				super(message);
				// TODO Auto-generated constructor stub
			}
		}
	}
