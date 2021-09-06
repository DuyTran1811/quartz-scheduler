package com.example.quartzscheduler.api.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "persons")
public class Person {
    @Id
    private String personId;
    private String fullName;
    private byte age;
    private String address;
    private boolean status;

    public Person() {
    }

    public Person(String personId, String fullName, byte age, String address, boolean status) {
        this.personId = personId;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.status = status;
    }
}
