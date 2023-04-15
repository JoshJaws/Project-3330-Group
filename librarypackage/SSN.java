package librarypackage;


public class SSN {
    public String ssn = "";

    public SSN(String ssn) {
        this.setSSN(ssn);
    }
    public void setSSN(String ssn) {
        this.ssn = ssn;
    }
    public SSN getSSN() {
        return this;
    }
    public String getSSNString() {
        return this.ssn;
    }
}
