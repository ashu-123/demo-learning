package com.learning.demo.repository;

import com.learning.demo.model.entity.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * The base repository class to help perform database query operations.
 */
public interface PersonRepository extends ReactiveMongoRepository<Person, ObjectId> {


}
