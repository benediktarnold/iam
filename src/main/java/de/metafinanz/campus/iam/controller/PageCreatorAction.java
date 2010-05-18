package de.metafinanz.campus.iam.controller;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.metafinanz.campus.iam.entities.WikiPage;

@Named("pageCreator")
@Scope("session")
public class PageCreatorAction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WikiPage newPage;
	private EntityManager entityManager;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String newPage() {
		newPage = new WikiPage();
		entityManager.persist(newPage);
		return "page?faces-redirect=true&id="+newPage.getId();
	}
}
