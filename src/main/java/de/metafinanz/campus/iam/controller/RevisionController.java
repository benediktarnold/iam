package de.metafinanz.campus.iam.controller;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.context.annotation.Scope;

import de.metafinanz.campus.iam.entities.WikiPage;

@Named("revisionController")
@Scope("request")
public class RevisionController {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<?> queryForRevisions(WikiPage current) {
		AuditReader auditReader = getAuditReader();
		if (current.getId() != null) {
			return auditReader.createQuery().forRevisionsOfEntity(
					WikiPage.class, false, false).add(
					AuditEntity.id().eq(current.getId())).addOrder(
					AuditEntity.revisionNumber().desc()).getResultList();
		}
		return new LinkedList<Object[]>();
	}

	public List<?> queryForLatestChanges() {
		return getAuditReader().createQuery().forRevisionsOfEntity(
				WikiPage.class, false, false).addOrder(
				AuditEntity.revisionNumber().desc()).getResultList();
	}

	private AuditReader getAuditReader() {
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
		return auditReader;
	}

	public WikiPage getRevision(Long liveId, Integer rev) {
		return getAuditReader().find(WikiPage.class, liveId, rev);
	}
}
