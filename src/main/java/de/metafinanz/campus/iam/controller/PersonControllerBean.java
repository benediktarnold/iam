package de.metafinanz.campus.iam.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import de.metafinanz.campus.iam.entities.Person;

@Named("personController")
@Scope("session")
public class PersonControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7502391887385563727L;
	private Person current;
	private Logger log;
	private EntityManager entityManager;
	private FacesContext facesContext;
	
	public PersonControllerBean() {
		super();
		this.log = Logger.getLogger(this.getClass());
	}

	public String newPerson() {
		log.debug("PersonControllerBean.newPerson()");
		current = new Person();
		entityManager.persist(current);
		facesContext.addMessage("", new FacesMessage("Profil angelegt"));
		return "";
	}

	public Person getCurrent() {
		return current;
	}

	public boolean isInit() {
		return current != null;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
		
	}

	@Inject
	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;		
	}

}
