package controller;


import fxapp.Session;
import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Controls the main menu.
 */
public class WaterSourceReportControllerTest {


    WaterSourceReport testReport;
    UserProfile testProfile;
    Location testLoc;
    @Before
    public void setUp() throws Exception {
        testProfile = new UserProfile("Test1","id1","password", AccountType.USER);
        testLoc = new Location(0,0,"test","test",QuaCondition.SAFE);
        testReport = new WaterSourceReport(2,testProfile,testLoc, WaterType.BOTTLED, WaterCondition.POTABLE);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUserProfile() throws Exception{
        Session.getInstance().setCurrentUser(testProfile);
        Assert.assertEquals(true, testReport.getReporterID().equals(testProfile.getId()));
    }
    @Test
    public void testReportNum() throws Exception{
        Assert.assertEquals(true, testReport.getReportNumber()==2);
    }
    @Test
    public void testWaterType() throws Exception{
        Assert.assertEquals(true, testReport.getWaterType().equals(WaterType.BOTTLED));
    }
    @Test
    public void testWaterCondition() throws Exception{
        Assert.assertEquals(true, testReport.getWaterCondition().equals(WaterCondition.POTABLE));
    }
    @Test
    public void testLocation() throws Exception{
        Assert.assertEquals(true, testReport.getWaterLocation().equals(testLoc));
    }

    /*@Test
    public void isManager() throws Exception {
        Session.getInstance().setCurrentUser(userTestProfile);
        Assert.assertEquals(false, testController.isManager());
    }

    @Test
    public void testWorker() throws Exception {
        Session.getInstance().setCurrentUser(workerTestProfile);
        Assert.assertEquals(false, testController.isManager());
    }

    @Test
    public void testManager() throws Exception {
        Session.getInstance().setCurrentUser(managerTestProfile);
        Assert.assertEquals(true, testController.isManager());
    }

    @Test
    public void testAdministrator() throws Exception {
        Session.getInstance().setCurrentUser(administratorTestProfile);
        Assert.assertEquals(false, testController.isManager());
    }*/

}