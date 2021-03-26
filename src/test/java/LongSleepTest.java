import adapter.EntityManager;
import org.junit.*;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;

public class LongSleepTest {

    @Test
    public void testRetrieveByString() {
        EntityManager emTest = new EntityManager(0);
        // should return null because no item matches this name
        try {
            Assert.assertNull(emTest.retrieve("abc"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testRetrieveByInt() {
        EntityManager emTest = new EntityManager(0);
        // should return null because no item matches this id
        try {
            Assert.assertNull(emTest.retrieve("12345"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
