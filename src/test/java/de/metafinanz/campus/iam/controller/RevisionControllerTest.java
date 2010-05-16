package de.metafinanz.campus.iam.controller;

import javax.persistence.EntityManager;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class RevisionControllerTest {
	@Before
	public void init() {
		RevisionController controller = new RevisionController();

		EntityManager entityManager = EasyMock
				.createNiceMock(EntityManager.class);

		controller.setEntityManager(entityManager);
	}

	@Test
	public void testGetRevisions() {

	}
}
