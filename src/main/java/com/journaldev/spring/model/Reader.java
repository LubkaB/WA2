package com.journaldev.spring.model;
// Generated Aug 18, 2016 9:09:34 PM by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Reader generated by hbm2java
 */
@Entity
@Table(name = "reader", catalog = "ReadersLog")
public class Reader implements java.io.Serializable {

	private Integer id;
	private String name;
	private Integer age;

	public Reader() {
	}

	public Reader(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
