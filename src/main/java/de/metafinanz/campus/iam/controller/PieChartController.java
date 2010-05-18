package de.metafinanz.campus.iam.controller;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("pieChart")
@Scope("request")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PieChartController {
	private RevisionController revisionController;
	private EntityManager entityManager;

	public List<Object[]> getData() {
		List<Object[]> result = new LinkedList<Object[]>();
		Long numberOfRevisions = revisionController.getNumberOfRevisions();
		result.add(new Object[] { "History", numberOfRevisions.toString() });
		int currentSize = entityManager.createQuery("from WikiPage").getResultList().size();
		result.add(new Object[] { "Pages", new Integer(currentSize) });
		return result;
	}

	@Inject
	public void setRevisionController(RevisionController revisionController) {
		this.revisionController = revisionController;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
