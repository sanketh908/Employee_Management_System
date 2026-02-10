package com.Sanketh.demo.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collation = "manager")
public class Manager {
    @Id

    private long id;

    private String name;

    private String username;

    private String email;

    private String password;

    private String department;

    private String contact;

    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    private List<Employee> employeeList;



    @OneToMany(mappedBy = "assignedbymanager",cascade = CascadeType.ALL)
    private List<Duty> dutyList;
    @ManyToOne
    @JoinColumn(name ="supervisor_id")
    private Manager supervisor;
}
