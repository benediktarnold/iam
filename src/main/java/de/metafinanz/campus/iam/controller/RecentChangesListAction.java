package de.metafinanz.campus.iam.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

@Named("recentChanges")
@Scope("request")
public class RecentChangesListAction {

	private RevisionController revisionController;

	@Transactional(readOnly = true)
	public List<?> getList() {
		return revisionController.queryForLatestChanges();
	}

	@Inject
	public void setRevisionController(RevisionController revisionController) {
		this.revisionController = revisionController;
	}
}
