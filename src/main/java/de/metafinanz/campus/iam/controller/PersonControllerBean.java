package de.metafinanz.campus.iam.controller;

import java.io.Serializable;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import de.metafinanz.campus.iam.entities.Person;

@Named("personController")
public class PersonControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7502391887385563727L;
	private Person current = new Person();
	private Logger log;

	public PersonControllerBean() {
		super();
		this.log = Logger.getLogger(this.getClass());
	}

	public String newPerson() {
		log.debug("PersonControllerBean.newPerson()");
		current = new Person();
		return "";
	}

	public Person getCurrent() {
		return current;
	}

	public boolean isInit() {
		// log.debug("PersonControllerBean.isInit() " + current!=null);
		return current != null;
	}

}
