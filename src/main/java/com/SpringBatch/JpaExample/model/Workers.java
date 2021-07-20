package com.SpringBatch.JpaExample.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Workers {

	@Id
	private int id;
	private String name;
	private String address;
	
	public Workers(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public Workers() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Workers [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	
}
