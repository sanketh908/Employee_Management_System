package com.Sanketh.demo.Repository;


import com.Sanketh.demo.Entity.Employee;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {
    Employee findEmployeeByname(String str);


}
