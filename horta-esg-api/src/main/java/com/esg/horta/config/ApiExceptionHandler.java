package com.esg.horta.config;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex) {
    var errors = new ArrayList<Map<String,String>>();
    ex.getBindingResult().getFieldErrors().forEach(e -> {
      var item = new HashMap<String,String>();
      item.put("field", e.getField());
      item.put("error", e.getDefaultMessage());
      errors.add(item);
    });
    return ResponseEntity.badRequest().body(Map.of(
      "message","Validation error","errors",errors
    ));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String,Object>> handleGeneric(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(Map.of("message","Unexpected error","details", ex.getMessage()));
  }
}

