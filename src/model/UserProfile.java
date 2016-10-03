package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Contains the user's information
 */
public class UserProfile {
    //actually come to think of it should these be simple object properties?
    //which do we prefer?
    //private SimpleStringProperty name;
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

    //Getters
    public String getName() {return name;}
    //public String getName() {return name.getValue();}
    public String getId() {return id;}
    public String getPassword() {return password;}
    public AccountType getAccountType() {return accountType;}

    //Setters
    public void setName(String name){this.name = name;}
    //public void setName(String name){this.name.setValue(name);}
    public void setId(String id){this.id = id;}
    public void setPassword(String password){this.password = password;}
    public void setAccountType(AccountType accountType){this.accountType = accountType;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
