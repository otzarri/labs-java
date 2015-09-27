# orgcensus-jaxb
This is a sample implementation of XML marshalling and unmarshalling using JAXB.
Emulates a census of organizations that manages data using XML format.
It's developed as a practical environment for learning how to implement JAXB.
The pages and the source code are well-commented to understand JAXB easily.

Author home: http://otzarri.net/

There are two actions available:
* Marshal
* Unmarshal

JAXB uses Java classes for mapping XML nodes. The XML code used in this implementation
is mapped using Address, Organization and Census classes on net.otzarri.orgcensus package.
Note that each subset (node level) is mapped to a different object and child subsets are assigned
to their parents using object variables with the subset object type.

The diagram below explains the XML mapping with JAXB used in this implementation:

![XML Mapping with JAXB](https://raw.githubusercontent.com/josebamartos/orgcensus-jaxb/master/WebContent/img/xml-mapping-with-jaxb.png "XML Mapping with JAXB")

## Installation

orgcensus-jaxb is developed as a warfile for [JBoss EAP](http://www.jboss.org/products/eap/) 6.x, because JBoss EAP provides full Java EE stack, including JAXB classes, out-of-the-box. The warfile is built and located in [orgcensus-jaxb.war](https://raw.githubusercontent.com/josebamartos/orgcensus-jaxb/master/orgcensus-jaxb.war) file. Deploy it in your EAP and start hacking.