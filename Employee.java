import librarypackage.SSN;
import java.util.Date;


public class Employee extends Person{
    protected int employeeID;

    Employee() {
        super();
    }
    Employee(String name, String address, Date dob, String email, SSN ssn) {
       super(name, address, dob, email, ssn);
       this.employeeID = this.toString().hashCode();
    }
}
