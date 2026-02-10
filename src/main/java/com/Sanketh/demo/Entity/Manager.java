package com.Sanketh.demo.Entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection= "manager")
public class Manager {
    @Id

    private ObjectId id;

    private String name;

    private String username;

    private String email;

    private String password;

    private String department;

    private String contact;

   @DBRef
    private List<Employee> employeeList;



   @DBRef
    private List<Duty> dutyList;
  @DBRef
    private Manager supervisor;
}
