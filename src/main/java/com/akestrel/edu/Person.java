package com.akestrel.edu;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 
 * @author Aleksey Kovalenko
 */
public class Person {
	LocalValidatorFactoryBean d;
	
	@Size(min = 2, max = 30)
	private String name;

	@NotNull(groups = { ValidateGroups.MottoGroup.class })
	private String motto;

	@NotNull(groups = { ValidateGroups.AgeGroup.class })
	@Min(value = 18, groups = { ValidateGroups.AgeGroup.class })
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	@Override
	public String toString() {
		return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
	}
}