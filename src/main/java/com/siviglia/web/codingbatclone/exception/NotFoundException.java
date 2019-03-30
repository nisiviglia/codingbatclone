package com.siviglia.web.codingbatclone.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

//If you throw an exception of type NotFoundException in your controllers, 
//Spring MVC's exception resolver would catch that exception and convert 
//it to a 404 Not Found HTTP response.
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {}
