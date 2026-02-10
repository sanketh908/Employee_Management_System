package com.Sanketh.demo.Repository;


import com.Sanketh.demo.Entity.Employee;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.function.Function;

public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {



}
