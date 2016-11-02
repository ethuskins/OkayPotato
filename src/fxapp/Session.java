package fxapp;

/**
 * Created by Forrest on 10/30/2016.
 */

import firebase4j.src.net.thegreshams.firebase4j.error.FirebaseException;
import firebase4j.src.net.thegreshams.firebase4j.model.FirebaseResponse;
import model.UserProfile;
import model.WaterQualityReport;
import model.WaterSourceReport;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import firebase4j.src.net.thegreshams.firebase4j.service.Firebase;
/**
 * Singleton class that holds the session info.
 */
public class Session {
    private static final Session instance = new Session();
    public static Session getInstance() {
        return instance;
    }

    String baseURL = "https://okaypotato-2368f.firebaseio.com/";
    String wsrURL = "WaterSourceReports";
    String wqrURL = "WaterQualityReports";
    String peopleURL = "People";
    Firebase fbCurrent = null;
    /**
     * Private constructor; so only 1 instance of this class is garunteed
     */
    private Session() {
        try {
            fbCurrent = new Firebase(baseURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase url");
        }

        try {
            FirebaseResponse wsrFirebase = fbCurrent.get(wsrURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase wsr url");

        } catch (UnsupportedEncodingException usex) {
            System.out.println("Unsupported Encoding wsr");
        }

        try {
            FirebaseResponse wqrFirebase = fbCurrent.get(wqrURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase wqr url");

        } catch (UnsupportedEncodingException usex) {
            System.out.println("Unsupported Encoding wqr");
        }

        try {
            FirebaseResponse peopleFirebase = fbCurrent.get(peopleURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase people url");

        } catch (UnsupportedEncodingException useex) {
            System.out.println("Unsupported Encoding people");
        }

        


    }














    //This is used to increment the report number when a report is generated
    public Integer reportnumber = 1;
    //we can remove this array list if we want. the hashmap should do what we want but better
    private List<UserProfile> userProfileList = new ArrayList<UserProfile>();
    private HashMap<String, UserProfile> userProfileStringHashMap = new HashMap<String, UserProfile>();
    private UserProfile currentUser;
    private static HashMap<Integer, WaterSourceReport> waterSourceReportHashMap = new HashMap<Integer, WaterSourceReport>();
    private static HashMap<Integer, WaterQualityReport> waterQualityReportHashMap = new HashMap<Integer, WaterQualityReport>();

    public HashMap<String, UserProfile> getUserProfileStringHashMap(){return userProfileStringHashMap;}

    //has a hashmap that stores the water source reports
    public static HashMap<Integer, WaterSourceReport> getWaterSourceReportHashMap() {return waterSourceReportHashMap;}

    //has a hashmap that stores the water quality reports
    public static HashMap<Integer, WaterQualityReport> getWaterQualityReportHashMap() {return waterQualityReportHashMap;}

    /**
     * Adds a userProfile to the Hashmap
     * @param userProfile the User Profile to add to the hashmap.
     */
    public void addUserProfile(UserProfile userProfile){
        //we can remove the array list if we want.
        userProfileList.add(userProfile);
        userProfileStringHashMap.put(userProfile.getId(),userProfile);
    }

    /**
     * Getter for current user
     * @return currentUser the current user
     */
    public UserProfile getCurrentUser() {
        return currentUser;
    }

    /**
     * Setter for current user
     * @param currentUser the UserProfile of the Current User
     */
    public void setCurrentUser(UserProfile currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * dummy method to simulate a callback from the map view
     */

}
