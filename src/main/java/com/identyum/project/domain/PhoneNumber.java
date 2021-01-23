package com.identyum.project.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PhoneNumber {
    @Id
    private String phoneNumber;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
