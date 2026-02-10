package com.Sanketh.demo.Repository;

import com.Sanketh.demo.Entity.Duty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DutyRepository extends MongoRepository<Duty, ObjectId> {
}
