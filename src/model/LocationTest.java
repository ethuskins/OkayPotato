
package model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class LocationTest {

    private Location location1;
    private Location location2;
    private Location location3;
    private Location location4;


    @Before
    public void setup() throws Exception {
        location1 = new Location(37.7, 27.5, "test", "test", QuaCondition.SAFE);
        location2 = new Location(-37.7, 27.5, "test", "test", QuaCondition.SAFE);
        location3 = new Location(37.7, -27.5, "test", "test", QuaCondition.SAFE);
        location4 = new Location(0, 0, "test", "test", QuaCondition.SAFE);
    }

    @org.junit.Test
    public void testToString() throws Exception {
        String test = "37.7N, 27.5E";
        Assert.assertEquals(test, location1.toString());
    }

    @Test
    public void testSouth() throws Exception {
        String test = "37.7S, 27.5E";
        Assert.assertEquals(test, location2.toString());
    }

    @Test
    public void testWest() throws Exception {
        String test = "37.7N, 27.5W";
        Assert.assertEquals(test, location3.toString());
    }

    @Test
    public void testEquator() throws Exception {
        String test = "0.0, 0.0";
        Assert.assertEquals(test, location4.toString());
    }
}
