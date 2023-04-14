import librarypackage.SSN;
import java.util.Date;


public class Student extends Member {
    Professor advisor;

    Student() {
        super();
    }
    Student(String name, String address, Date dob, String email, SSN ssn) {
        super(name, address, dob, email, ssn);
    }
    public void assignAdvisor(Professor professor) {
        this.advisor = professor;
    }
}