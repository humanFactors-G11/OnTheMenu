package com.example.erikgarcia.otm.CreateRestaurant;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.erikgarcia.otm.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maverick on 3/26/2017.
 */

public class FragmentResDetails extends android.support.v4.app.Fragment {

    ResTempSaveService tempSave = new ResTempSaveService();

    EditText newResName;
    Spinner resType;
    EditText newResAdd;
    EditText newResCity;
    EditText newResState;
    EditText newResZip;
    EditText newResDescription;
    ScrollView scroll;

    TextView sunOpen;
    TextView monOpen;
    TextView tuesOpen;
    TextView wedOpen;
    TextView thuOpen;
    TextView friOpen;
    TextView satOpen;
    TextView sunClose;
    TextView monClose;
    TextView tuesClose;
    TextView wedClose;
    TextView thuClose;
    TextView friClose;
    TextView satClose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_new_res_details, container, false);

        newResName = (EditText)view.findViewById(R.id.newResName);
        resType = (Spinner)view.findViewById(R.id.res_type);
        newResAdd = (EditText)view.findViewById(R.id.newResAddress);
        newResCity = (EditText)view.findViewById(R.id.newResCity);
        newResState = (EditText)view.findViewById(R.id.newResState);
        newResZip = (EditText)view.findViewById(R.id.newResZip);
        newResDescription = (EditText)view.findViewById(R.id.resDescription);

        sunOpen = (TextView)view.findViewById(R.id.sunOpen);
        monOpen = (TextView)view.findViewById(R.id.monOpen);
        tuesOpen = (TextView)view.findViewById(R.id.tuesOpen);
        wedOpen = (TextView)view.findViewById(R.id.wedOpen);
        thuOpen = (TextView)view.findViewById(R.id.thuOpen);
        friOpen = (TextView)view.findViewById(R.id.friOpen);
        satOpen = (TextView)view.findViewById(R.id.satOpen);
        sunClose = (TextView)view.findViewById(R.id.sunClose);
        monClose = (TextView)view.findViewById(R.id.monClose);
        tuesClose = (TextView)view.findViewById(R.id.tuesClose);
        wedClose = (TextView)view.findViewById(R.id.wedClose);
        thuClose = (TextView)view.findViewById(R.id.thuClose);
        friClose = (TextView)view.findViewById(R.id.friClose);
        satClose = (TextView)view.findViewById(R.id.satClose);

        tempSave.tempSave().clearCache = false;

        //Putting Time Data Back Into Time Views
        if(tempSave.tempSave().editRes != null && tempSave.tempSave().editRes.length() > 0) {
            String edit = tempSave.tempSave().editRes, detailsHeader = "~Details\n", timeHeader = "<Time\n", timeId = "- ";
            String[] editDetails = edit.split(timeHeader), firstDetails, timeDetails, tempTimeArr, timeArr;
            if(editDetails[0].contains(detailsHeader)) {
                editDetails[0] = editDetails[0].replaceAll(detailsHeader, "");
            }

            firstDetails = editDetails[0].split("\\r?\\n");

            for(int i=0; i<firstDetails.length; i++){
                tempSave.tempSave().resDetails[i] = firstDetails[i];
            }

            timeDetails = editDetails[1].split("\\r?\\n");

            timeArr = new String[14];
            tempTimeArr = new String[2];
            for(int i=0; i<timeDetails.length; i++){
                if(timeDetails[i].contains(timeId)) {
                    tempTimeArr = timeDetails[i].split(timeId);
                    tempTimeArr[0] += " -";
                } if(timeDetails[i].contains("Closed")){
                    tempTimeArr = new String[2];
                    tempTimeArr[0] = timeDetails[i];
                    tempTimeArr[1] = "";
                }

                    timeArr[i] = tempTimeArr[0];
                    timeArr[i + 7] = tempTimeArr[1];
            }

            for(int i=0; i<timeArr.length; i++){
                tempSave.tempSave().resTime[i] = timeArr[i];
            }

        }

        for (int i = 0; i < tempSave.tempSave().resTime.length; i++) {
            if (tempSave.tempSave().resTime[i] != null) {
                switch (i) {
                    case 0:
                        sunOpen.setText(tempSave.tempSave().resTime[0]);
                        if (sunOpen.getText().equals("Closed"))
                            sunClose.setVisibility(View.GONE);
                        else sunClose.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        monOpen.setText(tempSave.tempSave().resTime[1]);
                        if (monOpen.getText().equals("Closed"))
                            monClose.setVisibility(View.GONE);
                        else monClose.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        tuesOpen.setText(tempSave.tempSave().resTime[2]);
                        if (tuesOpen.getText().equals("Closed"))
                            tuesClose.setVisibility(View.GONE);
                        else tuesClose.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        wedOpen.setText(tempSave.tempSave().resTime[3]);
                        if (wedOpen.getText().equals("Closed"))
                            wedClose.setVisibility(View.GONE);
                        else wedClose.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        thuOpen.setText(tempSave.tempSave().resTime[4]);
                        if (thuOpen.getText().equals("Closed"))
                            thuClose.setVisibility(View.GONE);
                        else thuClose.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        friOpen.setText(tempSave.tempSave().resTime[5]);
                        if (friOpen.getText().equals("Closed"))
                            friClose.setVisibility(View.GONE);
                        else friClose.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        satOpen.setText(tempSave.tempSave().resTime[6]);
                        if (satOpen.getText().equals("Closed"))
                            satClose.setVisibility(View.GONE);
                        else satClose.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        sunClose.setText(tempSave.tempSave().resTime[7]);
                        break;
                    case 8:
                        monClose.setText(tempSave.tempSave().resTime[8]);
                        break;
                    case 9:
                        tuesClose.setText(tempSave.tempSave().resTime[9]);
                        break;
                    case 10:
                        wedClose.setText(tempSave.tempSave().resTime[10]);
                        break;
                    case 11:
                        thuClose.setText(tempSave.tempSave().resTime[11]);
                        break;
                    case 12:
                        friClose.setText(tempSave.tempSave().resTime[12]);
                        break;
                    case 13:
                        satClose.setText(tempSave.tempSave().resTime[13]);
                }
            }
        }


        //Putting Data into Blocks
        for (int i = 0; i < tempSave.tempSave().resDetails.length; i++) {
            String str = tempSave.tempSave().resDetails[i], nl = System.getProperty("line.separator");
            if (str != null && str.length() > 0) {
                switch (i) {
                    case 0:
                        newResName.setText(str);
                        break;
                    case 2:
                        newResAdd.setText(str);
                        break;
                    case 3:
                        newResCity.setText(str);
                        break;
                    case 4:
                        newResState.setText(str);
                        break;
                    case 5:
                        newResZip.setText(str);
                        break;
                    case 6:
                        if (str.contains("|")) {
                            str = str.replaceAll("\\|", nl);
                        }
                        newResDescription.setText(str);
                }
            }
        }

        newResName = (EditText)view.findViewById(R.id.newResName);
        newResAdd = (EditText)view.findViewById(R.id.newResAddress);
        newResCity = (EditText)view.findViewById(R.id.newResCity);
        newResState = (EditText)view.findViewById(R.id.newResState);
        newResZip = (EditText)view.findViewById(R.id.newResZip);
        newResDescription = (EditText)view.findViewById(R.id.resDescription);

        mSpinner();

        resType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tempSave.tempSave().resDetails[1] = resType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public void cache(){
        newResName = (EditText)getView().findViewById(R.id.newResName);
        resType = (Spinner) getView().findViewById(R.id.res_type);
        newResAdd = (EditText)getView().findViewById(R.id.newResAddress);
        newResCity = (EditText)getView().findViewById(R.id.newResCity);
        newResState = (EditText)getView().findViewById(R.id.newResState);
        newResZip = (EditText)getView().findViewById(R.id.newResZip);
        newResDescription = (EditText)getView().findViewById(R.id.resDescription);

        String nl = System.getProperty("line.separator");

        String[] arr = new String[7];
        arr[0] = newResName.getText().toString();
        arr[1] = resType.getSelectedItem().toString();
        arr[2] = newResAdd.getText().toString();
        arr[3] = newResCity.getText().toString();
        arr[4] = newResState.getText().toString();
        arr[5] = newResZip.getText().toString();
        if(newResDescription.getText().toString().contains(nl)) {
            arr[6] = newResDescription.getText().toString().replaceAll(nl, "|");
        }

        tempSave.tempSave().resDetails = arr;

    }

    public void clearCache(){
        String[] arr = new String[7];
        tempSave.tempSave().resDetails = arr;
        tempSave.tempSave().editRes = "";
    }

    public void mSpinner(){
        ArrayList<String> type = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.res_type)));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_dropdown_item, type);
        resType.setAdapter(adapter);

        //Setting the Temp Save of the ResType Spinner
        if(tempSave.tempSave().resDetails[1] != null && tempSave.tempSave().resDetails[1].length() > 0){
            int spinnerPosition = adapter.getPosition(tempSave.tempSave().resDetails[1]);
            resType.setSelection(spinnerPosition);
        }
    }

    @Override
    public void onPause(){
        super.onPause();

        if(!tempSave.tempSave().clearCache) cache();
        if(tempSave.tempSave().clearCache) clearCache();
    }
}
