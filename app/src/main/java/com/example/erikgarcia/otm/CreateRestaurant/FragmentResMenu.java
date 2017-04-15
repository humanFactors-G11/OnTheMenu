package com.example.erikgarcia.otm.CreateRestaurant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.erikgarcia.otm.R;

/**
 * Created by Maverick on 3/26/2017.
 */

public class FragmentResMenu extends Fragment {
    private FragmentTabHost mTabHost;

    ResTempSaveService tempSave = new ResTempSaveService();

    Button save;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_new_res_menu, container, false);

        mTabHost = (FragmentTabHost)view.findViewById(R.id.resMenuTabHost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        final String[] tabs = getResources().getStringArray(R.array.tab_tags);

        for(int i=0; i<tabs.length; i++){
            mTabHost.addTab(
                    mTabHost.newTabSpec(tabs[i]).setIndicator(tabs[i], null),
                    FragmentResTab.class, null);
        }

        save = (Button)view.findViewById(R.id.saveRes);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRes();
            }
        });

        return view;
    }

    public void saveRes(){
        //Save tempSave.tempSave().Variables Here

        stringBuilder();

    }

    FragmentResTab fTab = new FragmentResTab();

    public void stringBuilder(){

        String strBuilder = "";
        String[] detailsArray = tempSave.tempSave().resDetails,
                timeArray = tempSave.tempSave().resTime;

        for(int i=0; i<detailsArray.length; i++){
            if(i==0){ strBuilder += "~Details\n" + detailsArray[i] + "\n";}
            if(i>0){ strBuilder += detailsArray[i]+"\n";}
        }

        for (int i=0; i<timeArray.length-7; i++){
            if(i==0) strBuilder += "<Time\n";
            if(timeArray[i] != null && !timeArray[i].equals("Closed")) {
                strBuilder += timeArray[i] + timeArray[i + 7] + "\n";
            } if(timeArray[i] != null && timeArray[i].equals("Closed")){
                strBuilder += timeArray[i]+"\n";
            }
        }

        //Save Rest Of Data Here
        strBuilder += "~Appetizers\n";
        for(int i=0; i<fTab.appetizersArray.size(); i++){
            strBuilder += fTab.appetizersArray.get(i)+"\n";
        }
        strBuilder += "~Entrees\n";
        for(int i=0; i<fTab.entreesArray.size(); i++){
            strBuilder += fTab.entreesArray.get(i)+"\n";
        }
        strBuilder += "~Desserts\n";
        for(int i=0; i<fTab.dessertsArray.size(); i++){
            strBuilder += fTab.dessertsArray.get(i)+"\n";
        }
        strBuilder += "~Beverages\n";
        for(int i=0; i<fTab.beveragesArray.size(); i++){
            strBuilder += fTab.beveragesArray.get(i)+"\n";
        }
        strBuilder += "~Kids\n";
        for(int i=0; i<fTab.kidsArray.size(); i++){
            strBuilder += fTab.kidsArray.get(i)+"\n";
        }
        strBuilder += "~Specials\n";
        for(int i=0; i<fTab.specialsArray.size(); i++){
            strBuilder += fTab.specialsArray.get(i)+"\n";
        }

        //Placing all Elements into the String
        tempSave.tempSave().saveString = strBuilder;
    }

    @Override
    public void onPause(){
        super.onPause();

        FragmentResDetails clearCache = new FragmentResDetails();

        if(tempSave.tempSave().clearCache){
            clearCache.clearCache();
        }
    }
}
