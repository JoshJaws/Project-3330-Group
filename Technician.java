import librarypackage.SSN;
import java.util.*;


public class Technician extends Employee {
    Library libraryToSupervise;

    Technician() {
        super();
        this.libraryToSupervise = null;
    }
    Technician(String name, String address, Date dob, String email, SSN ssn, Library library) {
        super(name, address, dob, email, ssn);
        this.libraryToSupervise = library;
    }
    public void addItemToLibrary(LibraryCollection item) {
        if (this.libraryToSupervise == null) {
            System.out.println("Technician must have a library to supervise in order to add item.");
            return;
        }
        switch (item.section) {
            case ARTS:
                this.libraryToSupervise.artSection.add(item);
                break;
            case SCIENCES:
                this.libraryToSupervise.scienceSection.add(item);
                break;
            case NEWSPAPERS:
                this.libraryToSupervise.newspaperSection.add(item);
                break;
            case LAWS:
                this.libraryToSupervise.lawSection.add(item);
                break;
        }
    }
    public void returnItem(LibraryCollection item) {
        this.addItemToLibrary(item);
        item.returnItem();
    }

}
