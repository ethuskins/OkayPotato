package controller;


import javafx.scene.control.TextField;

import model.AccountType;
import model.UserProfile;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class UserProfileControllerTest {

    TextField nameTextField;
    TextField addressTextField;
    TextField emailTextField;
    TextField ageTextField;
    TextField titleTextField;
    UserProfile temp = null;
    @Before
    public void setUp() throws Exception {

        temp = new UserProfile("Emily", "emily", "whatever", AccountType.USER);
        temp.setAddress("831 Techwood Drive");
        temp.setEmailAddress("emily@gatech.edu");
        temp.setAge(20);
        temp.setTitle("A title");
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPopulate() throws Exception {
        testName();
        testAddress();
        testEmail();
        testAge();
        testTitle();
    }

    @Test
    public void testName() throws Exception {
        nameTextField.setText(temp.getName());
        Assert.assertEquals(nameTextField.getText(), temp.getName());
    }

    @Test
    public void testAddress() throws Exception {
        if (temp.getAddress() != null) {
            addressTextField.setText(temp.getAddress());
        }
        Assert.assertEquals(addressTextField.getText(), temp.getAddress());
    }

    @Test
    public void testEmail() throws Exception {
        if (temp.getEmailAddress() != null) {
            emailTextField.setText(temp.getEmailAddress());
        }
        Assert.assertEquals(emailTextField.getText(), temp.getEmailAddress());
    }

    @Test
    public void testAge() throws Exception {
        ageTextField.setText("" + temp.getAge());
        Assert.assertEquals(ageTextField.getText(), Integer.toString(temp.getAge()));
    }
    @Test
    public void testTitle() throws Exception {
        if (temp.getTitle() != null) {
            titleTextField.setText(temp.getTitle());
        }
        Assert.assertEquals(titleTextField.getText(), temp.getTitle());
    }


}