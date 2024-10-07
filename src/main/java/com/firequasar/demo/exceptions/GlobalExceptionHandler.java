package com.firequasar.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;
import com.firequasar.demo.entities.dto.ErrorDto;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  
  @ExceptionHandler(LocationException.class)
  public ResponseEntity<ErrorDto> handleLocationException(LocationException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder().message(e.getMessage()).timestamp(Instant.now()).build());
  }

  @ExceptionHandler(MessageException.class)
  public ResponseEntity<ErrorDto> handleMessageException(MessageException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto.builder().message(e.getMessage()).timestamp(Instant.now()).build());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto.builder().message("Invalid Payload").timestamp(Instant.now()).build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleException(Exception e) {
    e.printStackTrace();
    System.out.println(e.getLocalizedMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDto.builder().message(e.getMessage()).timestamp(Instant.now()).build());
  }

}
