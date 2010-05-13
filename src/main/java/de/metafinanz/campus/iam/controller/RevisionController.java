package de.metafinanz.campus.iam.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.envers.AuditReader;

@Named("revisionController")
public class RevisionController {

	@Inject
	public void setAuditReader(AuditReader auditReader) {
		// TODO Auto-generated method stub
		
	}

}
