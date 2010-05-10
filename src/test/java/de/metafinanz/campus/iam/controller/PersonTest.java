package de.metafinanz.campus.iam.controller;

import static org.junit.Assert.*;
import org.junit.Test;

import de.metafinanz.campus.iam.entities.Person;

public class PersonTest {
	
	@Test
	public void testInitalValues() {
		Person person = new Person();
		
		assertNotNull(person.getSurname());
		assertNotNull(person.getLastname());
		assertNotNull(person.getNickname());
	}
}
