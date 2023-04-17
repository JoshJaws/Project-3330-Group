import java.util.*;
import librarypackage.SSN;


enum Section {
    ARTS,
    SCIENCES,
    NEWSPAPERS,
    LAWS;
}


public class Library {
    public Hashtable<Integer, LibraryCollection> artSection; //hashtable keyed using item.title.hashCode()
    public Hashtable<Integer, LibraryCollection> scienceSection; //this can be thought of as bookshelves in different sections of the library
    public Hashtable<Integer, LibraryCollection> newspaperSection;
    public Hashtable<Integer, LibraryCollection> lawSection;

    private Hashtable<Integer, Member> members;
    private Hashtable<Integer, Employee> employees;

    //ondutyTechnician controls adding books, ondutyTechnican handles renting books
    public Librarian ondutyLibrarian;
    public Technician ondutyTechnician;

    Library() {
        this.artSection = new Hashtable<Integer, LibraryCollection>();
        this.scienceSection = new Hashtable<Integer, LibraryCollection>();
        this.newspaperSection = new Hashtable<Integer, LibraryCollection>();
        this.lawSection = new Hashtable<Integer, LibraryCollection>();

        this.members = new Hashtable<Integer, Member>();
        this.employees = new Hashtable<Integer, Employee>();

        this.setLibrarian(null);
        this.setTechnician(null);
    }
    Library(Librarian librarian, Technician technician) {
        this.artSection = new Hashtable<Integer, LibraryCollection>();
        this.scienceSection = new Hashtable<Integer, LibraryCollection>();
        this.newspaperSection = new Hashtable<Integer, LibraryCollection>();
        this.lawSection = new Hashtable<Integer, LibraryCollection>();
        
        this.members = new Hashtable<Integer, Member>();
        this.employees = new Hashtable<Integer, Employee>();

        this.setLibrarian(librarian);
        this.setTechnician(technician);
    }

    public void addMember(Member member) {
        this.members.put(member.name.hashCode(), member);
    }
    public Member getMember(String name) {
        return this.members.get(name.hashCode());
    }
    public void AddEmployee(Employee employee) {
        this.employees.put(employee.name.hashCode(), employee);
    }
    public Employee getEmployee(String name) {
        return this.employees.get(name.hashCode());
    }
    public void setLibrarian(Librarian librarian) {
        this.ondutyLibrarian = librarian;
    }
    public Librarian getLibrarian() {
        return this.ondutyLibrarian;
    }
    public void setTechnician(Technician technician) {
        this.ondutyTechnician = technician;
    }
    public Technician getTechnician() {
        return this.ondutyTechnician;
    }
    public void addLibrarian(Librarian librarian) {
        if ((this.employees.contains(librarian)) == false) {
            this.AddEmployee(librarian);
        }
        librarian.libraryToSupervise = this;
        if (this.ondutyLibrarian == null) {
            this.setLibrarian(librarian);
            return;
        }
    }
    public void addTechnician(Technician technician) {
        if ((this.employees.containsKey(technician.name.hashCode())) == false) {
            this.AddEmployee(technician);
        }
        technician.libraryToSupervise = this;
        if (this.ondutyLibrarian == null) {
            this.setTechnician(technician);
            return;
        }
    }
    public LibraryCollection getItem(Section section, String name) {
        switch (section) {
            case ARTS:
                return this.artSection.get(name.hashCode());
            case SCIENCES:
                return this.scienceSection.get(name.hashCode());       
            case NEWSPAPERS:
                return this.newspaperSection.get(name.hashCode());
            case LAWS:
                return this.lawSection.get(name.hashCode());  
            default:
                if (this.artSection.containsKey(name.hashCode()))
                    return this.artSection.get(name.hashCode());
                else if (this.scienceSection.containsKey(name.hashCode()))
                    return this.scienceSection.get(name.hashCode());    
                if (this.newspaperSection.containsKey(name.hashCode()))
                    return this.newspaperSection.get(name.hashCode());
                else 
                    return this.lawSection.get(name.hashCode());      
        }
    }

    // public static void main(String args[]) {   
    //     Library library = new Library();     
    //     SSN librarianSSN = new SSN("111-22-3333");
    //     Date librarianDOB = new Date(100);
    //     Librarian librarian = new Librarian("Mrs. Scott", "319 Southshore Rd", librarianDOB, "gscottumsystem.edu", librarianSSN, library);
    //     library.addLibrarian(librarian);

    //     Date studentDOB = new Date(1000);
    //     SSN studentSSN = new SSN("552-35-2934");
    //     Student student = new Student("Andrew Stormer", "502 Kentucky Blvd", studentDOB, "abspp8@umsystem.edu", studentSSN);

    //     Date professorDOB = new Date(1000);
    //     SSN professorSSN = new SSN("321-78-4556");
    //     Professor professor = new Professor("Dr. Jones", "1312 Providence Rd", professorDOB, "cjones@umsystem.edu", professorSSN);

    //     SSN librarianSSN2 = new SSN("321-22-7876");
    //     Date librarianDOB2 = new Date(2347);
    //     Librarian librarian2 = new Librarian("Mrs. Brown", "515 Wilson Ave", librarianDOB2, "pbrown@umsystem.edu", librarianSSN2, library);

    //     SSN technicianSSN = new SSN("888-22-3333");
    //     Date technicianDOB = new Date(1245);
    //     Technician technician = new Technician("Mr. Scott", "319 Southshore Rd", technicianDOB, "jscott@umsystem.edu", technicianSSN, library);

    //     Book b1 = new Book("National Geographic", 12, Section.SCIENCES, 20);
    //     Book b2 = new Book("Constitution", 55, Section.LAWS, 25);
    //     DVD dvd1 = new DVD("Pulp Fiction", 1023, Section.ARTS, 15);
    //     Newspaper n1 = new Newspaper("Columbia Tribune", 2023, Section.NEWSPAPERS, 5);
    //     Journal j1 = new Journal("Someones Journal", 3219, Section.SCIENCES, 100);

    //     library.addLibrarian(librarian2);
    //     library.addTechnician(technician);
    //     library.addMember(student);
    //     library.addMember(professor);

    //     library.ondutyTechnician.addItemToLibrary(b1);
    //     library.ondutyTechnician.addItemToLibrary(b2);
    //     library.ondutyTechnician.addItemToLibrary(dvd1);
    //     library.ondutyTechnician.addItemToLibrary(n1);
    //     library.ondutyTechnician.addItemToLibrary(j1);

    //     student.borrowItem(library, b1);
    //     student.borrowItem(library, dvd1);
    //     student.borrowItem(library, j1);

    //     professor.superviseStudent(student);
        
    //     professor.borrowItem(library, dvd1);
    //     professor.borrowItem(library, b2);
    //     professor.borrowItem(library, n1);
    // }
}
