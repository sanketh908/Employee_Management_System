package com.Sanketh.demo.Repository;

import com.Sanketh.demo.Entity.Admin;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, ObjectId> {

}
