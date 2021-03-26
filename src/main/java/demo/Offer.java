package demo;

/**
 * A class representing offers to buy cars.
 */
public class Offer {
    private int id;
    private int carID;
    private String username;
    private int period;
    private static int idGenerator = 0;

    public Offer(int carID, String username, int period) {
        this.id = idGenerator++;
        this.carID = carID;
        this.username = username;
        this.period = period;
    }

    public Offer(int id, int carID, String username, int period) {
        this.id = id;
        this.carID = carID;
        this.username = username;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        // display offer info in an easily readable format
        return "Offer #" + id + " by " + username + " for car #" + carID + " over " + period + " months";
    }
}
