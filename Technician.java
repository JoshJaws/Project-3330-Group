import librarypackage.SSN;
import java.util.*;

/*
 * Technicians aer assigned a library to supervise. For that library, they put back items in the right sections and
 * sort the sections (not implemented yet)
 */
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
                this.libraryToSupervise.artSection.put(item.title.hashCode(), item);
                break;
            case SCIENCES:
                this.libraryToSupervise.scienceSection.put(item.title.hashCode(), item);
                break;
            case NEWSPAPERS:
                this.libraryToSupervise.newspaperSection.put(item.title.hashCode(), item);
                break;
            case LAWS:
                this.libraryToSupervise.lawSection.put(item.title.hashCode(), item);
                break;
        }
    }
    public void returnItem(LibraryCollection item) {
        this.addItemToLibrary(item);
        item.returnItem();
    }

}
