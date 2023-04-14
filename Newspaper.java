import java.util.Date;


public class Newspaper extends LibraryCollection {
    private int ISSN;

    Newspaper() {
        this.title = "";
        this.ISSN = 0;
        this.isBorrowed = false;
        this.section = Section.NEWSPAPERS;
        this.price = 5;
    }
    Newspaper(String title, int ISSN, Section section, float price) {
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
