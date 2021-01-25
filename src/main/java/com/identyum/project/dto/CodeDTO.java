package com.identyum.project.dto;

import java.io.Serializable;

public class CodeDTO implements Serializable {

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CodeDTO{" +
                "number='" + number + '\'' +
                '}';
    }
}
