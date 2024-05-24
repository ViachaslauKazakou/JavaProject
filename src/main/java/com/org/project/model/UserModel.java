package com.org.project.model;

// import javax.persistence.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// @Entity
// @Table(name="users")
public record UserModel (
        long id, 
        String FirstName,
        String LastName,
        String email
    ) { }
