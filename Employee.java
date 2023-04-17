import librarypackage.SSN;
import java.util.Date;


public class Employee extends Person{
    protected int employeeID;

    public void setID () {
        this.employeeID = this.toString().hashCode();
    }
    public int getID() {
        return this.employeeID;
    }

    Employee() {
        super();
    }
    Employee(String name, String address, Date dob, String email, SSN ssn) {
       super(name, address, dob, email, ssn);
       this.employeeID = this.toString().hashCode();
    }
}
