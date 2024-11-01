package com.project.farmingHub.handler;

import com.project.farmingHub.exception.BreedServiceException;
import com.project.farmingHub.exception.HealthProductServiceException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String , String> methodArgumentException(MethodArgumentNotValidException exception){

        Map<String , String> errorMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(
           error->errorMap.put(error.getField() , error.getDefaultMessage())
        );

        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({BreedServiceException.class , HealthProductServiceException.class})
    public Map<String,String> handleBusinessException(RuntimeException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage" , exception.getMessage());
        return errorMap;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode status = ex.getStatusCode();
        String errorMessage = ex.getReason();
        String formattedErrorMessage = "errorMessage: " + errorMessage;
        return ResponseEntity.status(status).body(formattedErrorMessage);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<Map<String, Object>>> handle(ConstraintViolationException exception) {
        return Collections.singletonMap(
                "errors",
                exception
                        .getConstraintViolations()
                        .stream()
                        .map(
                                x -> {
                                    HashMap<String, Object> error = new HashMap<>();
                                    error.put("field", x.getPropertyPath().toString());
                                    error.put("error", x.getMessage());
                                    return error;
                                })
                        .collect(Collectors.toList()));
    }

}
