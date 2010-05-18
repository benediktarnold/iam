package de.metafinanz.campus.iam.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.primefaces.component.tabview.TabView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.metafinanz.campus.iam.entities.WikiPage;

@Named("pageController")
@Scope("request")
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
	private List<?> queryForRevisions;
	private Integer revision;
	private Long currentId;

	public WikiPageControllerBean() {
		super();
		this.log = Logger.getLogger(this.getClass());
	}

	@Value("#{request.getParameter('id')}")
	public void setId(Long id) {
		this.currentId = id;
	}

	@Value("#{request.getParameter('rev')}")
	public void setRev(Integer rev) {
		this.revision = rev;
	}

	@PostConstruct
	public void initialize() {
		if (isHistoric()) {
			current = revisionController.getRevision(currentId, revision);
		} else if (this.currentId != null) {
			current = entityManager.find(WikiPage.class, currentId);
		}
	}

	public boolean isHistoric() {
		return this.currentId != null && this.revision != null;
	}

	public String newPage() {
		log.info("WikiPageControllerBean.newPage()");
		current = new WikiPage();
		entityManager.persist(current);
		return "page";
	}

	public String save() {
		log.info("WikiPageControllerBean.save() " + current);
		entityManager.merge(current);
		tabView.setActiveIndex(0);
		return "";
	}

	public WikiPage getCurrent() {
		return current;
	}

	public boolean isInit() {
		return current != null;
	}
	
	public boolean hasTitle(){
		return !current.getTitle().equals("");
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
		return revisionController.queryForRevisions(current);
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

	public TabView getTabView() {
		return tabView;
	}

}
