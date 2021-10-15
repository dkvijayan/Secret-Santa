package com.sanatasecret.controller;

import com.sanatasecret.common.CustomExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author Viju on 10/14/21
 * @project santa-secret
 */
@ControllerAdvice
public class CustomExceptionHandler {

   @ExceptionHandler(CustomExceptions.class)
   public ResponseEntity<?> handleMaxSizeException(CustomExceptions exc) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
   }

}
