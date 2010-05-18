package de.metafinanz.campus.iam.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class WikiPage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 466881039286494411L;
	private Long id;
	private String magneticLink;
	private String title = "";

	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String nickname) {
		this.title = nickname;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setContent(String hobbies) {
		this.content = hobbies;
	}

	@Lob
	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Wikipage: "+ getId()+" Title: " + getTitle();
	}

	protected void setMagneticLink(String magneticLink) {
		this.magneticLink = magneticLink;
	}

	public String getMagneticLink() {
		return magneticLink;
	}

}
