package com.example.erikgarcia.otm.CreateRestaurant;

import android.app.IntentService;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Maverick on 4/5/2017.
 */

public class ResTempSaveService extends IntentService {
    public ResTempSaveService(){super("Temp Save Service");}

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public void onHandleIntent(Intent intent){

    }

    private static ResTempSaveService tempSave = null;

    public String[] resDetails = new String[7], //0 Name; 1 Type; 2 Address; 3 City; 4 State; 5 Zip; 6 Description
                resTime = new String[14]; //0-6 = Open; 7-13 = Close
    String restaurantItems = "", //Save Restaurant Tab>Items here
                                    /*:: ~ Tabs
                                         ^ Items name>price>description>Type>rating ::*/
        saveString = "", //String To save to File;

    // ========== Read From File and Split ==========
        editRes = ""; // <-- Read from File as a whole String here

    String[] editResArr;

    ArrayList<String> appetizersArray = new ArrayList<>();
    ArrayList<String> entreesArray = new ArrayList<>();
    ArrayList<String> dessertsArray = new ArrayList<>();
    ArrayList<String> beveragesArray = new ArrayList<>();
    ArrayList<String> kidsArray = new ArrayList<>();
    ArrayList<String> specialsArray = new ArrayList<>();

    boolean clearCache = false; //Clear the Cache when finished

    public static synchronized ResTempSaveService tempSave(){
        if(tempSave == null){
            tempSave = new ResTempSaveService();
        }
        return tempSave;
    }
}
