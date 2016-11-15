package fxapp;



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
        if (wsrFirebase != null && wsrFirebase.getCode() == 200) {
            wsrFBMap =  wsrFirebase.getBody();
        }
        Map<String, Object> wqrFBMap = null;
        if (wqrFirebase != null && wqrFirebase.getCode() == 200) {
            wqrFBMap =  wqrFirebase.getBody();
        }
        Map<String, Object> peopleFBMap = null;
        if (peopleFirebase != null && peopleFirebase.getCode() == 200) {
            peopleFBMap =  peopleFirebase.getBody();
        }
        Map<String, Object> numFBMap = null;
        if (numFirebase != null && numFirebase.getCode() == 200) {
            numFBMap =  numFirebase.getBody();
        }

        //process firebase maps to fill the HashMaps we want the project to use

        Gson gson = new Gson();
        if (wsrFBMap != null && !wsrFBMap.entrySet().isEmpty()) {
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
        }


        if (wqrFBMap != null && !wqrFBMap.entrySet().isEmpty()) {
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
        }

        if (peopleFBMap != null && !peopleFBMap.entrySet().isEmpty()) {
            for (Map.Entry<String, Object> entry : peopleFBMap.entrySet()) {
                LinkedHashMap jsonMap = (LinkedHashMap) entry.getValue();
                String jsonString = gson.toJson(jsonMap,LinkedHashMap.class);
                UserProfile parsedPeople = gson.fromJson(jsonString, UserProfile.class);

                userProfileStringHashMap.put(parsedPeople.getId(), parsedPeople);
            }
        }

        if (numFBMap != null && !numFBMap.entrySet().isEmpty()) {
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


    }


    //This is used to increment the report number when a report is generated
    private Integer wsrNumber = 0;
    private Integer wqrNumber = 0;

    //collection of users that have accounts
    private final HashMap<String, UserProfile> userProfileStringHashMap = new HashMap<>();
    private UserProfile currentUser;

    //where all water source reports are stored
    private static final HashMap<Integer, WaterSourceReport> waterSourceReportHashMap = new HashMap<>();

    //where all water quality reports are stored
    private static final HashMap<Integer, WaterQualityReport> waterQualityReportHashMap = new HashMap<>();

    /**
     * Gets the user profile hash map.
     * @return the hash map
     */
    public HashMap<String, UserProfile> getUserProfileStringHashMap(){return userProfileStringHashMap;}

    /**
     *  Has a HashMap that stores the water source reports.
     * @return the hash map.
     */
    public static HashMap<Integer, WaterSourceReport> getWaterSourceReportHashMap() {return waterSourceReportHashMap;}

    /**
     * Has a HashMap that stores the water quality reports.
     * @return the HashMap
     */
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
            System.out.println("Jackson exception in wqrController");

        } catch (FirebaseException ignored) {
            System.out.println("FB exception in wqrController");

        } catch (UnsupportedEncodingException ignored) {
            System.out.println("Unsupported encoding exception in wqrController");
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
     * Gets the baseURL
     * @return the baseURL
     */
    public String getBaseURL() {
        return baseURL;
    }

    /**
     * Gets the wsrURL.
     * @return the wsr
     */
    public String getWsrURL() {
        return wsrURL;
    }

    /**
     * Gets the wqrURL.
     * @return the wqrURL
     */
    public String getWqrURL() {
        return wqrURL;
    }

    private String getPeopleURL() {
        return peopleURL;
    }

    /**
     * Gets the numURL.
     * @return the numURL
     */
    public String getNumURL() {
        return numURL;
    }

    /**
     * Gets the FbCurrent.
     * @return the FBCurrent
     */
    public Firebase getFbCurrent() {
        return fbCurrent;
    }

    /**
     * gets the WqrNumber.
     * @return the wqrNumber
     */
    public Integer getWqrNumber() {
        return wqrNumber;
    }

    /**
     * Gets the wsrNumber.
     * @return the wsrNumber
     */
    public Integer getWsrNumber() {
        return wsrNumber;
    }

    /**
     * Increases the wqrNumber.
     */
    public void incrementWqrNumber() {
        wqrNumber++;
    }

    /**
     * Increases the wsrNumber.
     */
    public void incrementWsrNumber() {
        wsrNumber++;
    }
}
