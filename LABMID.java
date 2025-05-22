package lab.mid;
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(double bidAmount, String bidderName);
}


interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class Auction implements Subject {
    private String itemName;
    private double currentBid;
    private String highestBidder;
    private List<Observer> observers;

    public Auction(String itemName) {
        this.itemName = itemName;
        this.currentBid = 0.0;
        this.highestBidder = null;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(currentBid, highestBidder);
        }
    }
    public void placeBid(double bidAmount, String bidderName) {
        if (bidAmount > currentBid) {
            currentBid = bidAmount;
            highestBidder = bidderName;
            System.out.println("New highest bid: " + currentBid + " by " + highestBidder);
            notifyObservers(); 
        } else {
            System.out.println("Bid of " + bidAmount + " is too low, must be higher than " + currentBid);
        }
    }
}

class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(double bidAmount, String bidderName) {
        System.out.println(name + " notified: New highest bid is " + bidAmount + " by " + bidderName);
    }
}

public class LABMID{
    public static void main(String[] args) {
       
        Auction auction = new Auction("Antique Vase");
        User user1 = new User("shayan");
        User user2 = new User("khan");

        
        auction.registerObserver(user1);
        auction.registerObserver(user2);

        
        auction.placeBid(100.0, "shayan");
        auction.placeBid(150.0, "khan");
        auction.placeBid(120.0, "shayan");
    }
}


