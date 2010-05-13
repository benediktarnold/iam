package de.metafinanz.campus.iam.controller;

import org.easymock.EasyMock;
import org.hibernate.envers.AuditReader;
import org.junit.experimental.theories.suppliers.TestedOn;

public class RevisionControllerTest {
	public void init() {
		RevisionController controller = new RevisionController();
		
		AuditReader auditReader = EasyMock.createNiceMock(AuditReader.class);
		
		controller.setAuditReader(auditReader);
	}
}
