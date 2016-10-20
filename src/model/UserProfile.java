package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Contains the user's information
 */
public class UserProfile {
    //actually come to think of it should these be simple object properties?
    //which do we prefer?
    //This name of the person registering the new UserProfile
    private String name;
    //The name that will be displayed when signed in, submitting reports, etc.
    private String id;
    //The password associated with the UserProfile, used for signing in.
    private String password;
    //The account type that determines what the UserProfile can do.
    private AccountType accountType;
    //TODO implement the following:
    private String address;
    private String emailAddress;
    private String title;
    private int age;

    /**
     * No-args constructor
     */
    public UserProfile(){
        this.name ="user";
        //this.name.setValue("user");
        this.id = "0000";
        this.password="pass";
        this.accountType = AccountType.USER;
    }

    /**
     * constructor
     * @param name the name of the user
     * @param id the id of the user
     * @param password the password of the user
     * @param accountType the accounttype of the user
     */

    public UserProfile(String name, String id, String password, AccountType accountType){
        this();
        this.name = name;
        //this.name.setValue(name);
        this.id = id;
        this.password = password;
        this.accountType = accountType;
        this.address = "";
        this.emailAddress = "";
        this.title = "";
    }

    /**
     * getter for name
     * @return name
     */
    public String getName() {return name;}

    /**
     * setter for name
     * @param name the new name
     */
    public void setName(String name){this.name = name;}

    /**
     * getter for id
     * @return the id
     */
    public String getId() {return id;}

    /**
     * setter for id
     * @param id the new id
     */
    public void setId(String id){this.id = id;}

    /**
     * getter for password
     * @return the password
     */
    public String getPassword() {return password;}

    /**
     * setter for password
     * @param password the new password
     */
    public void setPassword(String password){this.password = password;}

    /**
     * getter for accountType
     * @return the accountType
     */
    public AccountType getAccountType() {return accountType;}

    /**
     * setter for accountType
     * @param accountType the new accountType
     */
    public void setAccountType(AccountType accountType){this.accountType = accountType;}

    /**
     * getter for address
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * setter for address
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter for email address
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * setter for email address
     * @param emailAddress the new email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * getter for title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter for age
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * setter for age
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }
}
