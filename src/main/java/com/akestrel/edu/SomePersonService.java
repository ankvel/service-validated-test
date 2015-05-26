package com.akestrel.edu;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 
 * @author Aleksey Kovalenko
 */
@Service
public class SomePersonService implements PersonService {

	public void processAge(
			@Validated({ ValidateGroups.AgeGroup.class }) Person person) {

		System.out.println("age: " + person);
	}

	public void processMotto(
			@Validated({ ValidateGroups.MottoGroup.class }) Person person) {

		System.out.println("motto: " + person);
	}

}
