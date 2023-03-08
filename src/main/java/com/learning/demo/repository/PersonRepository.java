package com.learning.demo.repository;

import com.learning.demo.model.entity.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, ObjectId> {


}
