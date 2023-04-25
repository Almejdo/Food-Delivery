package com.fooddelivery.exception;


import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestControllerAdvice
public class EcommerceGlobalExceptionHandling extends ResponseEntityExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
//        ExceptionResponse resp = new ExceptionResponse(HttpStatus.BAD_REQUEST,ex.getMessage());
//        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ExceptionResponse resp = new ExceptionResponse(HttpStatus.BAD_REQUEST,getRequiredFields(ex));
//        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
//    }
//
//    private Map<String,String> getRequiredFields(MethodArgumentNotValidException ex){
//        Map<String,String> errors= new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(e ->{
//            errors.put(e.getField(),e.getDefaultMessage());
//        });
//        return errors;
//    }
}
