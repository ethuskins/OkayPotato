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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

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
    private String baseURL = "https://okaypotato-2368f.firebaseio.com/";
    private String wsrURL = "WaterSourceReports";
    private String wqrURL = "WaterQualityReports";
    private String peopleURL = "People";
    private String numURL = "Numbers";
    //our current connection to firebase
    private Firebase fbCurrent = null;
    //The information on the current connections
    private FirebaseResponse wsrFirebase = null;
    private FirebaseResponse wqrFirebase = null;
    private FirebaseResponse peopleFirebase = null;
    private FirebaseResponse numFirebase = null;



    /**
     * Private constructor; so only 1 instance of this class is garunteed
     */
    private Session() {

        //open connection to firebase instance
        try {
            fbCurrent = new Firebase(baseURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase url");
        }

        //get the three tables in firebase that store the Water Source Reports, Water Quality Reports, and User Profiles
        try {
            wsrFirebase = fbCurrent.get(wsrURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase wsr url");

        } catch (UnsupportedEncodingException usex) {
            System.out.println("Unsupported Encoding wsr");
        }

        try {
            wqrFirebase = fbCurrent.get(wqrURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase wqr url");

        } catch (UnsupportedEncodingException usex) {
            System.out.println("Unsupported Encoding wqr");
        }

        try {
             peopleFirebase = fbCurrent.get(peopleURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase people url");

        } catch (UnsupportedEncodingException useex) {
            System.out.println("Unsupported Encoding people");
        }

        try {
            numFirebase = fbCurrent.get(numURL);
        } catch (FirebaseException ex) {
            System.out.println("Invalid firebase num url");

        } catch (UnsupportedEncodingException useex) {
            System.out.println("Unsupported Encoding num");
        }

        //checks HTTP status code for connection, then get maps of the info in the tables
        if (wsrFirebase.getCode() == 200) {
            wsrFBMap =  wsrFirebase.getBody();
        }
        if (wqrFirebase.getCode() == 200) {
            wqrFBMap =  wqrFirebase.getBody();
        }
        if (peopleFirebase.getCode() == 200) {
            peopleFBMap =  peopleFirebase.getBody();
        }
        if (numFirebase.getCode() == 200) {
            numFBMap =  numFirebase.getBody();
        }

        //process firebase maps to fill the HashMaps we want the project to use

        Gson gson = new Gson();
        for (Map.Entry<String, Object> entry : wsrFBMap.entrySet()) {

            LinkedHashMap jsonmap = (LinkedHashMap) entry.getValue();
            LinkedHashMap loc = (LinkedHashMap)jsonmap.get("location");

            String jsonString = gson.toJson(jsonmap,LinkedHashMap.class);
            String jsonloc = gson.toJson(loc, Object.class);

            WaterSourceReport parsedWSR = gson.fromJson(jsonString, WaterSourceReport.class);
            Location parsedloc = gson.fromJson(jsonloc, Location.class);

            parsedWSR.setLocation(parsedloc);

            waterSourceReportHashMap.put(parsedWSR.getReportNumber(), parsedWSR);
        }


        for (Map.Entry<String, Object> entry : wqrFBMap.entrySet()) {
            LinkedHashMap jsonmap = (LinkedHashMap) entry.getValue();
            LinkedHashMap loc = (LinkedHashMap) jsonmap.get("location");

            String jsonString = gson.toJson(jsonmap,LinkedHashMap.class);
            String jsonloc = gson.toJson(loc, Object.class);

            WaterQualityReport parsedWQR = gson.fromJson(jsonString, WaterQualityReport.class);
            Location parsedloc = gson.fromJson(jsonloc, Location.class);

            parsedWQR.setLocation(parsedloc);

            waterQualityReportHashMap.put(parsedWQR.getReportNumber(), parsedWQR);
        }

        for (Map.Entry<String, Object> entry : peopleFBMap.entrySet()) {
            LinkedHashMap jsonmap = (LinkedHashMap) entry.getValue();
            String jsonString = gson.toJson(jsonmap,LinkedHashMap.class);
            UserProfile parsedPeople = gson.fromJson(jsonString, UserProfile.class);

            userProfileStringHashMap.put(parsedPeople.getId(), parsedPeople);
        }

        for (Map.Entry<String, Object> entry : numFBMap.entrySet()) {
            numberingReports.put(entry.getKey(), (Integer) entry.getValue());
        }

        //this initializes the reports numbers to one if no reports have ever been added
        if (numberingReports.size() < 2) {
            numberingReports.clear();
            numberingReports.put("wsrNumber", 1);
            numberingReports.put("wqrNumber", 1);
        }
        wsrnumber = numberingReports.get("wsrNumber");
        wqrnumber = numberingReports.get("wqrNumber");





    }


    //Maps that are filled by Firebase with table information
    private Map<String, Object> wsrFBMap = null;
    private Map<String, Object> wqrFBMap = null;
    private Map<String, Object> peopleFBMap = null;
    private Map<String, Object> numFBMap = null;








    //This is used to increment the report number when a report is generated
    private Integer wsrnumber = null;
    private Integer wqrnumber = null;
    // map where reportnumbers are stored to be sent back up to Firebase later
    private HashMap<String, Integer> numberingReports = new HashMap<String, Integer>();

    //collection of users that have accounts
    private HashMap<String, UserProfile> userProfileStringHashMap = new HashMap<String, UserProfile>();
    private UserProfile currentUser;

    //where all water source reports are stored
    private static HashMap<Integer, WaterSourceReport> waterSourceReportHashMap = new HashMap<Integer, WaterSourceReport>();

    //where all water quality reports are stored
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

        userProfileStringHashMap.put(userProfile.getId(),userProfile);

        //sending modified table back up to firebase
        Map<String, Object> fbInsert = new HashMap<String, Object>();
        for (Map.Entry<String, UserProfile> entry : userProfileStringHashMap.entrySet()) {
            fbInsert.put(entry.getKey().toString(), (Object) entry.getValue());
        }
        try {
            FirebaseResponse resp = fbCurrent.put(Session.getInstance().getPeopleURL(), fbInsert);
        } catch (JacksonUtilityException juex) {

        } catch (FirebaseException fbex) {

        } catch (UnsupportedEncodingException ueex) {

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

    public String getPeopleURL() {
        return peopleURL;
    }
    public String getNumURL() {
        return numURL;
    }

    public Firebase getFbCurrent() {
        return fbCurrent;
    }

    public Integer getWqrnumber() {
        return wqrnumber;
    }

    public Integer getWsrnumber() {
        return wsrnumber;
    }
    public void incrementWqrnumber() {
        wqrnumber++;
    }

    public void incrementWsrnumber() {
        wsrnumber++;
    }
}
