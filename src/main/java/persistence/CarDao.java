package persistence;

import demo.Car;
import demo.User;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A data access object specifically for Car objects.
 */
public class CarDao implements Dao<Car> {
    @Override
    public Car select(String param) throws SQLException {
        return null;
    }

    /**
     * Retrieves and returns the car with the given ID.
     * @param id
     * @return
     * @throws SQLException
     */
    public Car selectByID(int id) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement getCar = conn.prepareStatement(
                "select * from \"Cars\" where \"CarID\" = ?");
        getCar.setInt(1, id);
        ResultSet carData = getCar.executeQuery();
        // parse the results of the query to make a car and return that
        carData.next();
        return new Car(carData.getInt("CarID"), carData.getString("Make"),
                carData.getString("Model"), carData.getInt("Year"),
                carData.getInt("Price"));
    }

    /**
     * Retrieves and returns all cars on the lot.
     * @return
     * @throws SQLException
     */
    @Override
    public Car[] selectAll() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        int countOfCars = countCars();
        Car[] cars = new Car[countOfCars];
        int i = 0;
        PreparedStatement getCar = conn.prepareStatement(
                "select * from \"Cars\"");
        ResultSet carData = getCar.executeQuery();
        // parse the results of the query to make a car and add it to the array
        // remember, only 1 call to next() per row returned!
        while (carData.next() && i < countOfCars) {
            // make new car; add to array
            cars[i++] = new Car(carData.getInt("CarID"), carData.getString("Make"),
                    carData.getString("Model"), carData.getInt("Year"),
                    carData.getInt("Price"));
        }

        return cars;
    }

    /**
     * Retrieves and returns all cars owned by a given user.
     * @param byUser
     */
    public Car[] selectOwned(User byUser) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        int countOfCars;
        Car[] cars;
        int i = 0;
        // set the statement as "scroll insensitive" so we can call last() and similar on it
        PreparedStatement getUsername = conn.prepareStatement(
                "select * from \"Cars\" where \"Owner\" = ?", ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        getUsername.setString(1, byUser.getUsername());
        ResultSet carData = getUsername.executeQuery();
        // get the number of rows returned through clever ResultSet method use
        carData.last();
        countOfCars = carData.getRow();
        cars = new Car[countOfCars];
        carData.beforeFirst();
        // parse the results of the query to make a car and add it to the array
        while (carData.next() && i < countOfCars) {
            // make new car; add to array
            cars[i++] = new Car(carData.getInt("CarID"), carData.getString("Make"),
                    carData.getString("Model"), carData.getInt("Year"),
                    carData.getInt("Price"));
        }

        return cars;
    }

    /**
     * Adds the given car to the lot.
     * @param car
     * @throws SQLException
     */
    @Override
    public void insert(Car car) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement insertCar = conn.prepareStatement(
                "insert into \"Cars\" values (?, ?, ?, ?, ?, ?)");
        insertCar.setInt(1, car.getId());
        insertCar.setString(2, car.getMake());
        insertCar.setString(3, car.getModel());
        insertCar.setInt(4, car.getYear());
        insertCar.setInt(5, car.getPrice());
        insertCar.setString(6, car.getOwner());
        insertCar.executeUpdate();
    }

    @Override
    public Car update(Car car, String[] params) throws SQLException {
        return null;
    }

    /**
     * Removes the given car from the lot.
     * @param car
     */
    @Override
    public void delete(Car car) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement deleteCar = conn.prepareStatement(
                "delete from \"Cars\" where \"CarID\" = ?");
        deleteCar.setInt(1, car.getId());
        deleteCar.executeUpdate();
    }

    /**
     * Returns the number of cars in the lot.
     *
     * @return
     * @throws SQLException
     */
    public int countCars() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement getUsername = conn.prepareStatement(
                "select count(\"Make\") from \"Cars\"");
        ResultSet carData = getUsername.executeQuery();
        carData.next();
        return carData.getInt("count");
    }
}
