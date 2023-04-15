import librarypackage.SSN;
import java.util.*;

/*
 * Librarians are assinged a library to supervise. For that library they are responsible for giving memberships to
 * people and making them their correct member status. Libarians are also responsible for sending emails to members
 * who may have overdue items, and removing items from the library.
 */
public class Librarian extends Employee {
    Library libraryToSupervise;

    Librarian() {
        super();
        libraryToSupervise = null;
    }
    Librarian(String name, String address, Date dob, String email, SSN ssn, Library library) {
        super(name, address, dob, email, ssn);
        this.libraryToSupervise = library;
    }
    public void giveMembership(Person person) {
        person = new Member(person.name, person.address, person.dob, person.email, person.ssn);
    }
    public void makeProfessor(Member person) {
        person = new Professor(person.name, person.address, person.dob, person.email, person.ssn);
    }
    public void makeStudent(Member person) {
        person = new Student(person.name, person.address, person.dob, person.email, person.ssn);
    }
    public void sendReminder(Member member, LibraryCollection item, int daycount) {
        if (daycount == 12) {
            System.out.printf("Sent email to %s to remind %s to remind them their item is due in 2 days!\n", member.email, member.name);
        } else if (daycount == 14) {
            System.out.printf("Sent email to %s to remind %s to remind them their item is due!\nTomorrow we will start adding a fee of $1 for each day not returned.\n", member.email, member.name);
        }
    }
    public void addFees(Member member, LibraryCollection item, int daycount) {
        if (daycount == 31) {
            member.moneyOwed += item.price;
            item.stoptimer();
            System.out.printf("Item not returned within a month of checking out. Current fees for %s: $%f\n", member.name, member.moneyOwed);
        } else if (daycount > 14 && daycount < 31) {
            member.moneyOwed += 1;
        }
    }
    public void fetchItem(LibraryCollection item) {
        switch (item.section) {
            case ARTS:
            if (this.libraryToSupervise.artSection.contains(item))
                this.libraryToSupervise.artSection.remove(item);
            break;
            case SCIENCES:
            if (this.libraryToSupervise.scienceSection.contains(item))
                this.libraryToSupervise.scienceSection.remove(item);
            break;
            case NEWSPAPERS:
            if (this.libraryToSupervise.newspaperSection.contains(item))
                this.libraryToSupervise.newspaperSection.remove(item);
            break;
            case LAWS:
            if (this.libraryToSupervise.lawSection.contains(item))
                this.libraryToSupervise.lawSection.remove(item);
            break;
        }
    }
}
