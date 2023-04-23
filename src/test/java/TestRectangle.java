import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRectangle {
    @Test(testName = "testOne"/*, enabled = false*/)
    public void testRectArea() {
        Rectangle rect = new Rectangle(3, 4);
        Assert.assertEquals(rect.area(), 12.0);
    }

    @Test(testName = "test2")
    public void testRectPerimeter() {
        Rectangle rect = new Rectangle(2, 3);
        Assert.assertEquals(rect.perimeter(), 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRectConstruct() {
        Rectangle rect = new Rectangle(-1, -1);
    }

    @Test(testName = "DpTest", dataProvider = "providerNumbers")
    public void test(int number, int expected) {
        System.out.println("DpTest "+number+expected);
        Assert.assertEquals(number + 10, expected, "Error message!!!");
    }

    @DataProvider(name = "providerNumbers")
    public Object[][] provideData() {

        return new Object[][] {
                { 10, 20 },
                { 100, 112 },
                { 200, 210 }
        };
    }
}
