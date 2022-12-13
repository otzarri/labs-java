package net.otzarri.orgcensus;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *	Class for mapping <census> nodes and their children.
 */
@XmlRootElement( name = "census" )
public class Census {
	
	/** 
	 * Variable containing <organization> child node value.
	 * Note that the variable stores a List of Organization objects, which maps <organization> node and child node values.
	 * This is the way for including multiple child nodesets into their parent using JAXB.
	 */
	private List<Organization> organizations;
	
	/**  Getter for <organization> child node value */
	public List<Organization> getOrganizations(){
		return organizations;
	}

	/**  Setter for <organization> child node value */
	@XmlElement(name = "organization")
	public void setOrganizations(List<Organization> organizations){
		this.organizations = organizations;
	}
	
}