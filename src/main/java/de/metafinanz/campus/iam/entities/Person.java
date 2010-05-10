package de.metafinanz.campus.iam.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 466881039286494411L;
	private Long id;
	private String nickname = "";

	private String lastname = "";
	private String surname = "";
	
	private String hobbies;

	@Column(unique = true)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@Lob
	public String getHobbies() {
		return hobbies;
	}

}
