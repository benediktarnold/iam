package de.metafinanz.campus.iam.controller;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;

import java.util.List;

import javax.persistence.EntityManager;

import org.easymock.classextension.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.metafinanz.campus.iam.entities.WikiPage;

public class WikiPageControllerBeanTest {

	private WikiPageControllerBean controller;
	private EasyMockSupport mockSupport = new EasyMockSupport();
	private EntityManager em;

	@Before
	public void init() {
		mockSupport.resetAll();
		controller = new WikiPageControllerBean();
		em = mockSupport.createMock(EntityManager.class);
		controller.setEntityManager(em);

		RevisionController revisionController = mockSupport
				.createMock(RevisionController.class);
		controller.setRevisionController(revisionController);
	}

	private void expectPersist(int count) {
		em.persist(isA(WikiPage.class));
		expectLastCall().times(count);
	}


	@Test
	public void testGetRevisions() {
		List<?> revisions = controller.getRevisions();
	}
}
