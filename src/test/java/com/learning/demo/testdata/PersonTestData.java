package com.learning.demo.testdata;

import com.learning.demo.model.entity.Person;
import org.bson.types.ObjectId;

import static com.learning.demo.constant.PersonTestConstants.*;

public class PersonTestData {

    public static Person getPerson() {
        return new Person().setId(new ObjectId())
                   .setFirstName(TEST_FIRST_NAME)
                   .setLastName(TEST_LAST_NAME);
    }
}
