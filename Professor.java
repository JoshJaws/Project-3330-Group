import librarypackage.SSN;
import java.util.*;


public class Professor extends Member {
    public ArrayList<Student> students;

    Professor() {
        super();
        students = new ArrayList<>();
    }
    Professor(String name, String address, Date dob, String email, SSN ssn) {
        super(name, address, dob, email, ssn);
        students = new ArrayList<>();
    }
    public void superviseStudent(Student student) {
        this.students.add(student);
        student.assignAdvisor(this);
    }
}
