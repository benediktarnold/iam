package de.metafinanz.campus.iam.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class WikiPage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 466881039286494411L;
	private Long id;
	private String title = "";
	
	private String content;

	@Column(unique = true)
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

}
