package fxapp;

/**
 * Created by Forrest on 10/30/2016.
 */

import firebase4j.src.net.thegreshams.firebase4j.error.JacksonUtilityException;
import firebase4j.src.net.thegreshams.firebase4j.service.Firebase;
import firebase4j.src.net.thegreshams.firebase4j.error.FirebaseException;
import firebase4j.src.net.thegreshams.firebase4j.model.FirebaseResponse;
import model.Location;
import model.UserProfile;
import model.WaterQualityReport;
import model.WaterSourceReport;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;


/**
 * Singleton class that holds the session info.
 */
public class Session {
    private static Session instance = null;
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }


    //URL components to connect to firebase instance
    private final String baseURL = "https://okaypotato-2368f.firebaseio.com/";
    private final String wsrURL = "WaterSourceReports";
    private final String wqrURL = "WaterQualityReports";
    private final String peopleURL = "People";
    private final String numURL = "Numbers";
    //our current connection to firebase
    private Firebase fbCurrent = null;


    /**
     * Private constructor; so only 1 instance of this class is guaranteed
     */
    private Session() {

        //open connection to firebase instance
        try {
            fbCurrent = new Firebase(baseURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase url");
        }

        //get the three tables in firebase that store the Water Source Reports, Water Quality Reports, and User Profiles
        FirebaseResponse wsrFirebase = null;
        try {
            wsrFirebase = fbCurrent.get(wsrURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase wsr url");

        } catch (UnsupportedEncodingException usex) {
            System.out.println("Unsupported Encoding wsr");
        }

        FirebaseResponse wqrFirebase = null;
        try {
            wqrFirebase = fbCurrent.get(wqrURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase wqr url");

        } catch (UnsupportedEncodingException usex) {
            System.out.println("Unsupported Encoding wqr");
        }

        FirebaseResponse peopleFirebase = null;
        try {
             peopleFirebase = fbCurrent.get(peopleURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase people url");

        } catch (UnsupportedEncodingException usex) {
            System.out.println("Unsupported Encoding people");
        }

        FirebaseResponse numFirebase = null;
        try {
            numFirebase = fbCurrent.get(numURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase num url");

        } catch (UnsupportedEncodingException usex) {
            System.out.println("Unsupported Encoding num");
        }

        //checks HTTP status code for connection, then get maps of the info in the tables
        Map<String, Object> wsrFBMap = null;
        if (wsrFirebase.getCode() == 200) {
            wsrFBMap =  wsrFirebase.getBody();
        }
        Map<String, Object> wqrFBMap = null;
        if (wqrFirebase.getCode() == 200) {
            wqrFBMap =  wqrFirebase.getBody();
        }
        Map<String, Object> peopleFBMap = null;
        if (peopleFirebase.getCode() == 200) {
            peopleFBMap =  peopleFirebase.getBody();
        }
        Map<String, Object> numFBMap = null;
        if (numFirebase.getCode() == 200) {
            numFBMap =  numFirebase.getBody();
        }

        //process firebase maps to fill the HashMaps we want the project to use

        Gson gson = new Gson();
        for (Map.Entry<String, Object> entry : wsrFBMap.entrySet()) {

            LinkedHashMap jsonMap = (LinkedHashMap) entry.getValue();
            LinkedHashMap loc = (LinkedHashMap)jsonMap.get("location");

            String jsonString = gson.toJson(jsonMap,LinkedHashMap.class);
            String jsonLoc = gson.toJson(loc, Object.class);

            WaterSourceReport parsedWSR = gson.fromJson(jsonString, WaterSourceReport.class);
            Location parsedLoc = gson.fromJson(jsonLoc, Location.class);

            parsedWSR.setLocation(parsedLoc);

            waterSourceReportHashMap.put(parsedWSR.getReportNumber(), parsedWSR);
        }


        for (Map.Entry<String, Object> entry : wqrFBMap.entrySet()) {
            LinkedHashMap jsonMap = (LinkedHashMap) entry.getValue();
            LinkedHashMap loc = (LinkedHashMap) jsonMap.get("location");

            String jsonString = gson.toJson(jsonMap,LinkedHashMap.class);
            String jsonLoc = gson.toJson(loc, Object.class);

            WaterQualityReport parsedWQR = gson.fromJson(jsonString, WaterQualityReport.class);
            Location parsedLoc = gson.fromJson(jsonLoc, Location.class);

            parsedWQR.setLocation(parsedLoc);

            waterQualityReportHashMap.put(parsedWQR.getReportNumber(), parsedWQR);
        }

        for (Map.Entry<String, Object> entry : peopleFBMap.entrySet()) {
            LinkedHashMap jsonMap = (LinkedHashMap) entry.getValue();
            String jsonString = gson.toJson(jsonMap,LinkedHashMap.class);
            UserProfile parsedPeople = gson.fromJson(jsonString, UserProfile.class);

            userProfileStringHashMap.put(parsedPeople.getId(), parsedPeople);
        }

        HashMap<String, Integer> numberingReports = new HashMap<>();
        for (Map.Entry<String, Object> entry : numFBMap.entrySet()) {
            numberingReports.put(entry.getKey(), (Integer) entry.getValue());
        }

        //this initializes the reports numbers to one if no reports have ever been added
        if (numberingReports.size() < 2) {
            numberingReports.clear();
            numberingReports.put("wsrNumber", 1);
            numberingReports.put("wqrNumber", 1);
        }
        wsrNumber = numberingReports.get("wsrNumber");
        wqrNumber = numberingReports.get("wqrNumber");





    }


    //This is used to increment the report number when a report is generated
    private Integer wsrNumber = null;
    private Integer wqrNumber = null;

    //collection of users that have accounts
    private final HashMap<String, UserProfile> userProfileStringHashMap = new HashMap<>();
    private UserProfile currentUser;

    //where all water source reports are stored
    private static final HashMap<Integer, WaterSourceReport> waterSourceReportHashMap = new HashMap<>();

    //where all water quality reports are stored
    private static final HashMap<Integer, WaterQualityReport> waterQualityReportHashMap = new HashMap<>();

    public HashMap<String, UserProfile> getUserProfileStringHashMap(){return userProfileStringHashMap;}

    //has a HashMap that stores the water source reports
    public static HashMap<Integer, WaterSourceReport> getWaterSourceReportHashMap() {return waterSourceReportHashMap;}

    //has a HashMap that stores the water quality reports
    public static HashMap<Integer, WaterQualityReport> getWaterQualityReportHashMap() {return waterQualityReportHashMap;}

    /**
     * Adds a userProfile to the HashMap
     * @param userProfile the User Profile to add to the HashMap.
     */
    public void addUserProfile(UserProfile userProfile){
        //we can remove the array list if we want.

        userProfileStringHashMap.put(userProfile.getId(),userProfile);

        //sending modified table back up to firebase
        Map<String, Object> fbInsert = new HashMap<>();
        for (Map.Entry<String, UserProfile> entry : userProfileStringHashMap.entrySet()) {
            fbInsert.put(entry.getKey(), entry.getValue());
        }
        try {
            FirebaseResponse resp = fbCurrent.put(Session.getInstance().getPeopleURL(), fbInsert);
        } catch (JacksonUtilityException ignored) {

        } catch (FirebaseException ignored) {

        } catch (UnsupportedEncodingException ignored) {

        }
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


    public String getBaseURL() {
        return baseURL;
    }

    public String getWsrURL() {
        return wsrURL;
    }

    public String getWqrURL() {
        return wqrURL;
    }

    private String getPeopleURL() {
        return peopleURL;
    }
    public String getNumURL() {
        return numURL;
    }

    public Firebase getFbCurrent() {
        return fbCurrent;
    }

    public Integer getWqrNumber() {
        return wqrNumber;
    }

    public Integer getWsrNumber() {
        return wsrNumber;
    }
    public void incrementWqrNumber() {
        wqrNumber++;
    }

    public void incrementWsrNumber() {
        wsrNumber++;
    }
}
