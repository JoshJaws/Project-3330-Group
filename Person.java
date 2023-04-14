import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.*;

import librarypackage.SSN;


public class Person {
    protected String name;
    protected String address;
    protected Date dob;
    protected String email;
    protected SSN ssn;

    Person() {
        this.name = "";
        this.address = "";
        this.dob = new Date();
        this.email = "";
        this.ssn = new SSN("");
    }
    Person(String name, String address, Date dob, String email, SSN ssn) {
        this.setName(name);
        this.setAddress(address);
        this.setDob(dob);
        this.setEmail(email);
        this.ssn = new SSN(ssn.getSSNString());
    }
    public String toString() {
        String s = name + "\n" + address + "\n";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        s += dateFormat.format(dob) + "\n";
        s += email + "\n";
        s += ssn.getSSNString() + "\n";  
        return s;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSSN(SSN ssn) {
        this.ssn.setSSN(ssn.getSSNString());
    }
    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public Date getDob() {
        return this.dob;
    }
    public String getEmail() {
        return this.email;
    }
    public SSN getSSN() {
        return this.ssn;
    }
    public String getSSNString() {
        return this.ssn.getSSNString();
    }
    public Person getPeople() {
      return this;  
    }
}
