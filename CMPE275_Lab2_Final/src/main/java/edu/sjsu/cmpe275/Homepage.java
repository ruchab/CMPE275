/*
 * Domain class for mini web application.
 * Homepage for any user with id,firstname,lastname,email,address,organization,aboutMyself
 */

package edu.sjsu.cmpe275;

public class Homepage {
private String id;
private String firstname;
private String lastname;
private String email;
private String address;
private String organization;
private String aboutMyself;

public Homepage(){}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getOrganization() {
	return organization;
}

public void setOrganization(String organization) {
	this.organization = organization;
}

public String getAboutMyself() {
	return aboutMyself;
}

public void setAboutMyself(String aboutMyself) {
	this.aboutMyself = aboutMyself;
}

//used to print the HashMap who has value as Homepage object.
@Override
public String toString(){
	
	return  "id: " + this.id + "\n" + 
			"firstname: " + this.firstname + "\n" +
			"lastname: " + this.lastname + "\n" +
			"email: " + this.email + "\n" +
			"address: " + this.address + "\n" +
			"organization: " + this.organization + "\n" +
			"aboutMyself: " + this.aboutMyself;
}
	
}
