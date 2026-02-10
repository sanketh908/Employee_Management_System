package com.Sanketh.demo.Entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Data
@Document(collation = "leave")
public class Leave {
    @Id

    private ObjectId id;

    private LocalDate startdate;

    private LocalDate enddate;

    private String reason;




    private String stats;

    private Employee employee;
}
