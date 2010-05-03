package de.metafinanz.campus.iam.controller;

import javax.inject.Named;

import de.metafinanz.campus.iam.entities.Person;

@Named("personController")
public class PersonControllerBean {

	private Person current;

	public Person newPerson() {
		System.out.println("PersonControllerBean.newPerson()");
		current = new Person();
		return getCurrent();
	}

	public Person getCurrent() {
		return current;
	}

	public boolean isInit() {
		System.out.println("PersonControllerBean.isInit() " + current!=null);
		return current != null;
	}

}
