package com.Sanketh.demo.Entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document( collection= "duty")
public class Duty {
    @Id
    private ObjectId id;

    private String title;

    private String description;

    private Employee employee;

    private Manager assignedbymanager;

    private Admin assignedbyadmin;

}
