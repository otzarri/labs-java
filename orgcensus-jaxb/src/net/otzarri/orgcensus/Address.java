package net.otzarri.orgcensus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *	Class for mapping <address> nodes and their children.
 */
@XmlType( propOrder = { "street", "city", "country" } )
@XmlRootElement(name="address")
public class Address {
	/**  Variables containing the value of <street>, <city> and <country> child nodes. */
	String street;
	String city;
	String country;
	
	/**  Getter for <street> child node value */
	public String getStreet() {
		return street;
	}
	
	/**  Setter for <street>  child node value */
	@XmlElement(name = "street")
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**  Getter for <city> child node value */
	public String getCity() {
		return city;
	}
	
	/**  Setter for <city> child node value */
	@XmlElement(name = "city")
	public void setCity(String city) {
		this.city = city;
	}
	
	/**  Getter for <country> child node value */
	public String getCountry() {
		return country;
	}
	
	/**  Setter for <country> child node value */
	@XmlElement(name = "country")
	public void setCountry(String country) {
		this.country = country;
	}
	
}
