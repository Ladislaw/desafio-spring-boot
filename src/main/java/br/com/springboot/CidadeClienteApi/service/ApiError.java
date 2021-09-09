package br.com.springboot.CidadeClienteApi.service;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiError implements Serializable {

	private static final long serialVersionUID = 4025004646668848343L;

	public static ResponseEntity<Object> response (HttpStatus status, String message){
    	return new ResponseEntity<Object>(message, status);
    }
}
