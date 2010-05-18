package de.metafinanz.campus.iam.controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.primefaces.component.tabview.TabView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.metafinanz.campus.iam.entities.WikiPage;

@Named("pageController")
@Scope("view")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class WikiPageControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7502391887385563727L;
	private WikiPage current;
	private Logger log;
	private EntityManager entityManager;
	private RevisionController revisionController;

	private TabView tabView;
	private List<?> queryForRevisions = new LinkedList<Object>();;
	private Integer revisionNumber;
	private String currentMagnet;

	public WikiPageControllerBean() {
		super();
		this.log = Logger.getLogger(this.getClass());
	}

	public void setCurrentMagnet(String magnet) {
		this.currentMagnet = magnet;
	}

	public String getCurrentMagnet() {
		return currentMagnet;
	}

	public void setRevisionNumber(Integer rev) {
		this.revisionNumber = rev;
	}

	public Integer getRevisionNumber() {
		return revisionNumber;
	}

	public void initialize() {
		if (this.currentMagnet != null) {
			if (isHistoric()) {
				current = revisionController.getRevision(this.currentMagnet,
						revisionNumber);
			} else {
				this.current = getWikiPage(this.currentMagnet);
				if (current != null) {
					initRevisionList();
				}
			}
			if (this.current == null) {
				this.current = new WikiPage(this.currentMagnet);
			}
			System.out.println("Init: " + current.toString());
		}
	}

	private void initRevisionList() {
		queryForRevisions = revisionController
				.queryForRevisions(current);
	}

	private WikiPage getWikiPage(String magnet) {
		try {
			return (WikiPage) entityManager.createQuery(
					"from WikiPage wp where wp.magnet=?").setParameter(1,
					magnet).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean isHistoric() {
		return this.currentMagnet != null && this.revisionNumber != null;
	}

	public String save() {
		if (current.getId() == null) {
			entityManager.persist(current);
		} else {
			entityManager.merge(current);
			initRevisionList();
		}
		tabView.setActiveIndex(0);
		return "";
	}

	public WikiPage getCurrent() {
		return current;
	}

	public boolean isInit() {
		return current != null;
	}

	public boolean isNew() {
		return current.getId() == null;
	}

	public int getActiveIndex() {
		if (isNew()) {
			return 1;
		} else
			return 0;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	@Inject
	public void setRevisionController(RevisionController revisionController) {
		this.revisionController = revisionController;
	}

	public List<?> getRevisions() {
		return queryForRevisions;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

	public TabView getTabView() {
		return tabView;
	}

}
