package net.otzarri.orgcensus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *	Class for mapping <organization> nodes and their children.
 */
@XmlType( propOrder = { "name", "email", "address" } )
@XmlRootElement(name="organization")
public class Organization {
	/**  Variable containing <name> child node value. */
	String name;
	/**  Variable containing <email> child node value. */
	String email;
	/** 
	 * Variable containing <address> child node value.
	 * Note that the variable stores an Address object, which binds <address> node and child node values.
	 * This is the way for including a child nodeset into it's parent using JAXB.
	 */
	Address address;
	
	/**  Getter for <name> child node value */
	public String getName() {
		return name;
	}
	
	/**  Setter for <name> child node value */
	@XmlElement(name = "name")
	public void setName(String name) {
		this.name = name;
	}
	
	/**  Getter for <email> child node value */
	public String getEmail() {
		return email;
	}
	
	/**  Setter for <email> child node value */
	@XmlElement(name = "email")
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**  Getter for <name> child node value */
	public Address getAddress() {
		return address;
	}
	
	/**  Setter for <name> child node value */
	@XmlElement(name = "address")
	public void setAddress(Address address) {
		this.address = address;
	}	
}