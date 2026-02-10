package com.Sanketh.demo.Entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "Employee")
public class Employee {
    @Id
    private ObjectId id;
 @Indexed(unique = true)
    private String name;

    private String gender;

    private int age ;

    private String  designation;

    private String department;

    private double salary;

    private String contact;

    @DBRef
    private List<Leave> leaves;

    private List<Duty> duties;

    private Manager manager;
}
