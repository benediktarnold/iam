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

import de.metafinanz.campus.iam.entities.WikiPage;

@Named("pageController")
@Scope("session")
public class PersonControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7502391887385563727L;
	private WikiPage current;
	private Logger log;
	private EntityManager entityManager;
	private FacesContext facesContext;
	
	public PersonControllerBean() {
		super();
		this.log = Logger.getLogger(this.getClass());
	}

	public String newPage() {
		current = new WikiPage();
		entityManager.persist(current);
		return "";
	}

	public WikiPage getCurrent() {
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
