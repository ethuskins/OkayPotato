package controller;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import model.*;
import fxapp.*;

/**
 * Created by Jack Winski on 11/10/2016.
 */
public class MainMenuControllerTest {

    UserProfile userTestProfile;
    UserProfile workerTestProfile;
    UserProfile managerTestProfile;
    UserProfile administratorTestProfile;
    MainMenuController testController;

    @org.junit.Before
    public void setUp() throws Exception {
        userTestProfile = new UserProfile("Test1","id1","password",model.AccountType.USER);
        workerTestProfile = new UserProfile("Test2","id2","password",model.AccountType.WORKER);
        managerTestProfile = new UserProfile("Test3","id3","password",model.AccountType.MANAGER);
        administratorTestProfile = new UserProfile("Test4","id4","password",model.AccountType.ADMINISTRATOR);
        testController = new MainMenuController();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
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
    }

}