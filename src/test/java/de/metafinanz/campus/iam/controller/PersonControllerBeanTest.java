package de.metafinanz.campus.iam.controller;

import static org.easymock.classextension.EasyMock.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.easymock.classextension.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.metafinanz.campus.iam.entities.Person;

public class PersonControllerBeanTest {

	private PersonControllerBean controller;
	private EasyMockSupport mockSupport = new EasyMockSupport();
	private EntityManager em;
	private FacesContext facesContext;

	@Before
	public void init() {
		mockSupport.resetAll();
		controller = new PersonControllerBean();
		em = mockSupport.createMock(EntityManager.class);
		controller.setEntityManager(em);

		facesContext = mockSupport.createMock(FacesContext.class);
		controller.setFacesContext(facesContext);
	}

	private void expectPersist(int count) {
		em.persist(isA(Person.class));
		expectLastCall().times(count);

		
	}

	@Test
	public void testNewPerson() {
		expectPersist(2);
		mockSupport.replayAll();

		controller.newPerson();

		Person person = controller.getCurrent();
		Assert.assertNotNull(person);
		Assert.assertNotSame(person, controller.newPerson());
		mockSupport.verifyAll();
	}

	@Test
	public void testIsInit() {
		expectPersist(1);
		mockSupport.replayAll();
		Assert.assertFalse(controller.isInit());
		controller.newPerson();
		mockSupport.verifyAll();
		Assert.assertTrue(controller.isInit());
	}
}
