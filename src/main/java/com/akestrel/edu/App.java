package com.akestrel.edu;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.support.GenericGroovyApplicationContext;

/**
 * 
 * @author Aleksey Kovalenko
 */
public class App {
	public static void main(String[] args) {
		try (GenericGroovyApplicationContext context = new GenericGroovyApplicationContext()) {

			context.load("classpath:com/akestrel/edu/config.groovy");
			context.refresh();
			PersonService ps = context.getBean("personService",
					PersonService.class);
			Person person = new Person();
			person.setName("asd");
			person.setMotto("Geronimo!!!");
			person.setAge(17);

			try {
				ps.processAge(person);
			} catch (ConstraintViolationException e) {
				for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
					System.out.println(cv);
				}				
			}

			try {
				ps.processMotto(person);
			} catch (ConstraintViolationException e) {
				for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
					System.out.println(cv);
				}
			}

		}

	}
}
