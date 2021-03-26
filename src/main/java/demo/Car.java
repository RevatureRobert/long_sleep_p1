package demo;

/**
 * A class representing cars for the dealership.
 */
public class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private int price;
    private String owner;

    public Car(int id, String make, String model, int year, int price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        // all new cars start out being owned by "system," representing the dealership itself
        owner = "system";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        // display car info in an easily readable format
        return year + " " + make + " " + model + " #" + id + ", $" + price;
    }
}
