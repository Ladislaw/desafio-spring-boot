package br.com.springboot.CidadeClienteApi.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiError {

    public static ResponseEntity<Object> response (HttpStatus status, String message){
    	return new ResponseEntity<Object>(message, status);
    }
}
