package com.identyum.project.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PhoneDTO implements Serializable {
    @NotNull
    @NotEmpty(message = "*Please provide a valid phone number")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "number='" + number + '\'' +
                '}';
    }
}
