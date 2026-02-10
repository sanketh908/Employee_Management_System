package com.Sanketh.demo.Repository;

import com.Sanketh.demo.Entity.Leave;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LeaveRepository extends MongoRepository<Leave, ObjectId> {
}
