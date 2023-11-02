package com.omar.spring.carsalesgarage.exceptions;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CarNotFoundException extends Exception{
    private String msgException;

}
