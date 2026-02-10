package com.Sanketh.demo.Repository;

import com.Sanketh.demo.Entity.Admin;
import com.Sanketh.demo.Entity.Manager;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ManagerRepository extends MongoRepository<Manager, ObjectId> {


}
