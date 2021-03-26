package demo;

import adapter.EntityManager;
import util.ConnectionManager;
import util.MyThread;

import java.sql.*;

/**
 * Runs the demo application for the long-sleep ORM.
 */
public class DemoDriver {

    public static void main(String[] args) {
        // create some objects to persist
        User u1 = new User("bob", "asdf1234", 2);
        User u2 = new User("fred", "qwerty", 1);
        Car c1 = new Car(201, "Ford", "F150", 2010, 14000);
        Car c2 = new Car(202, "Dodge", "Ram", 2016, 20000);

        if (ConnectionManager.isAvailable()) {
            try {
                // get connection
                Connection conn = ConnectionManager.getConnection();
                // get "entity manager" with "default config"
                EntityManager emanager = new EntityManager(0);
                // get thread
                MyThread thread = new MyThread(conn);

                emanager.save(u1);
                emanager.save(u2);
                emanager.save(c1);
                emanager.save(c2);
                // set a savepoint b/c transaction management
                Savepoint save1 = conn.setSavepoint("save1");
                // do more stuff
                emanager.remove(u1);
                emanager.remove(c1);
                // we could rollback here, but we aren't
                //conn.rollback(save1);
                // after we're done, have that thread we made commit everything
                thread.run();
                thread.join();
            } catch (SQLException | InterruptedException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
