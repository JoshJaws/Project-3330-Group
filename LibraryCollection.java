import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

/* 
 * This class controls what happens at day 12, 14 and so on. The time in between these events is controlled by the
 * timer.schedule call.
*/ 
class Helper extends TimerTask {
    public int daycount = 12;
    public Librarian librarian;
    public Member member;
    public LibraryCollection item;

    Helper(Librarian librarian, Member member, LibraryCollection item) {
        this.librarian = librarian;
        this.member = member;
        this.item = item;
    }
    public void run() {
        if (daycount == 12 || daycount == 14) {
            this.librarian.sendReminder(member, item, daycount);
        } else if (daycount > 14 && daycount <= 31) {
            this.librarian.addFees(member, item, daycount);
        }
        daycount++;
    }
}

/*
 * Abstract class with the ability to be borrowed and returned from a library by a member
 */
public abstract class LibraryCollection {
    protected String title;
    protected Date dateRented;
    protected Section section;
    protected boolean isBorrowed;
    protected String memberInformation;
    protected double price;

    protected Timer timer;
    

    public abstract void borrowItem(Library library, Member member);
    public abstract void returnItem();

    public void starttimer(Librarian librarian, Member member) {
        this.timer = new Timer();
        TimerTask task = new Helper(librarian, member, this);
        this.timer.schedule(task, 10000, 1000); //2nd paramter is time it takes for the first task to happen, 3rd is time to repeat task
    }
    public void stoptimer() {
        this.timer.cancel();
    }

}
