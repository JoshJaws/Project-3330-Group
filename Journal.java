import java.util.Date;

//Journals have an ISSN number and are by default in the laws section, but can be arts and sciences as well. 
public class Journal extends LibraryCollection {
    private int ISSN;

    Journal() {
        this.ISSN = 0;
        this.isBorrowed = false;
        this.section = Section.LAWS;
        this.price = 20;
    }

    Journal(String title, int ISSN, Section section, float price) {
        this.title = title;
        this.ISSN = ISSN;
        this.isBorrowed = false;
        this.section = section;
        this.price = price;
    }

    public void borrowItem(Library library, Member member) {
        this.isBorrowed = true;
        this.dateRented = new Date();
        this.memberInformation = member.toString();
        this.starttimer(library.ondutyLibrarian, member);
    }

    public void returnItem() {
        this.isBorrowed = false;
        this.stoptimer();
    }
}
