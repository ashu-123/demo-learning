package com.learning.demo.testdata;

import com.learning.demo.model.api.PersonApiDto;

import static com.learning.demo.constant.PersonTestConstants.TEST_FIRST_NAME;
import static com.learning.demo.constant.PersonTestConstants.TEST_LAST_NAME;

public class PersonApiDtoTestData {

    public static PersonApiDto getPersonApiDto() {
        return new PersonApiDto().setFirstName(TEST_FIRST_NAME)
                   .setLastName(TEST_LAST_NAME);
    }
}
