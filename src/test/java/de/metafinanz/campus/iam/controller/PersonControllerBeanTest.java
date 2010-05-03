package de.metafinanz.campus.iam.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.metafinanz.campus.iam.controller.PersonControllerBean;
import de.metafinanz.campus.iam.entities.Person;



public class PersonControllerBeanTest {
	
	private PersonControllerBean controller;

	@Before
	public void init(){
		controller = new PersonControllerBean();
	}
	
	@Test
	public void testNewPerson(){
		controller.newPerson();
		Person person = controller.getCurrent();
		Assert.assertNotNull(person);
		
		Assert.assertNotSame(person, controller.newPerson());
	}
	
	@Test
	public void testGetCurrent(){
		Person current = controller.getCurrent();
		Assert.assertNull(current);
		controller.newPerson();
		Person newPerson = controller.getCurrent();
		current = controller.getCurrent();
		Assert.assertNotNull(current);
		Assert.assertSame(newPerson, current);
	}
	
	@Test
	public void testIsInit(){
		Assert.assertFalse(controller.isInit());
		controller.newPerson();
		Assert.assertTrue(controller.isInit());
	}
}
