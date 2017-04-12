package com.example.erikgarcia.otm.CreateRestaurant;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erikgarcia.otm.R;

import java.util.ArrayList;

/**
 * Created by Maverick on 3/26/2017.
 */

public class FragmentResTab extends Fragment {

    ResTempSaveService tempSave = new ResTempSaveService();

    ArrayList<String> appetizersArray = tempSave.tempSave().appetizersArray;
    ArrayList<String> entreesArray = tempSave.tempSave().entreesArray;
    ArrayList<String> dessertsArray = tempSave.tempSave().dessertsArray;
    ArrayList<String> beveragesArray = tempSave.tempSave().beveragesArray;
    ArrayList<String> kidsArray = tempSave.tempSave().kidsArray;
    ArrayList<String> specialsArray = tempSave.tempSave().specialsArray;

    int getItemID = 0, items = 0;
    int[] tempID = new int[4];

    LinearLayout newItem;
    TextView itemText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_menu_tab, container, false);
        TextView title = (TextView) v.findViewById(R.id.tabTitle);

        String editRes = tempSave.tempSave().editRes;
        String[] editResArr = tempSave.tempSave().editResArr;

        if(editRes != null && editRes.contains("~")){
            editRes = editRes.replaceFirst("~", "");
            editResArr = editRes.split("~");
        }

        if(editResArr != null) {
            for (int i = 0; i < editResArr.length; i++) {
                if (editResArr[i] != null && editResArr[i].contains(this.getTag())) {
                    editResArr[i] = editResArr[i].replaceFirst(this.getTag()+"\n", ""); //editResArr[0] is to be Negated (it contains resDetails)
                    editResArr[i] = editResArr[i].replaceAll(System.getProperty("line.separator"), "");
                }
            }
        }

        String[] getTempItems = new String[0];

        newItem = (LinearLayout)v.findViewById(R.id.newItemOnClick);
        itemText = (TextView)v.findViewById(R.id.newItemText);

        switch (this.getTag()){
            case "Appetizers":
                title.setText(this.getTag());
                itemText.setText("New "+this.getTag().substring(0, this.getTag().length() - 1));

                if(editResArr != null) {
                    getTempItems = editResArr[1].split("\\^");
                }

                loadTab(v, this.getTag(), inflater, getTempItems);

                break;
            case "Entrees":
                title.setText(this.getTag());
                itemText.setText("New "+this.getTag().substring(0, this.getTag().length() - 1));

                if(editResArr != null) {
                    getTempItems = editResArr[2].split("\\^");
                }

                loadTab(v, this.getTag(), inflater, getTempItems);

                break;
            case "Desserts":
                title.setText(this.getTag());
                itemText.setText("New "+this.getTag().substring(0, this.getTag().length() - 1));

                if(editResArr != null) {
                    getTempItems = editResArr[3].split("\\^");
                }

                loadTab(v, getTag(), inflater, getTempItems);

                break;
            case "Beverages":
                title.setText(this.getTag());
                itemText.setText("New "+this.getTag().substring(0, this.getTag().length() - 1));

                if(editResArr != null) {
                    getTempItems = editResArr[4].split("\\^");
                }

                loadTab(v, this.getTag(), inflater, getTempItems);

                break;
            case "Kids":
                title.setText(this.getTag());
                itemText.setText("New "+this.getTag());

                if(editResArr != null) {
                    getTempItems = editResArr[5].split("\\^");
                }

                loadTab(v, this.getTag(), inflater, getTempItems);

                break;
            case "Specials":
                title.setText(this.getTag());
                itemText.setText("New "+this.getTag().substring(0, this.getTag().length() - 1));

                if(editResArr != null) {
                    getTempItems = editResArr[6].split("\\^");
                }

                loadTab(v, this.getTag(), inflater, getTempItems);


        }

        return v;
    }

    int tag = 0;

    public void loadTab(final View v, String tabTag, final LayoutInflater inflater, String[] itemsArr){

        ArrayList<String> arrList = new ArrayList<>();

        switch (tabTag){
            case "Appetizers":
                tag = 1;

                for(int i=0; i<itemsArr.length; i++){
                    appetizersArray.add(itemsArr[i]);
                }

                arrList = appetizersArray;
                break;
            case "Entrees":
                tag = 2;

                for(int i=0; i<itemsArr.length; i++){
                    entreesArray.add(itemsArr[i]);
                }

                arrList = entreesArray;
                break;
            case "Desserts":
                tag = 3;

                for(int i=0; i<itemsArr.length; i++){
                    dessertsArray.add(itemsArr[i]);
                }

                arrList = dessertsArray;
                break;
            case "Beverages":
                tag = 4;

                for(int i=0; i<itemsArr.length; i++){
                    beveragesArray.add(itemsArr[i]);
                }

                arrList = beveragesArray;
                break;
            case "Kids":
                tag = 5;

                for(int i=0; i<itemsArr.length; i++){
                    kidsArray.add(itemsArr[i]);
                }

                arrList = kidsArray;
                break;
            case "Specials":
                tag = 6;

                for(int i=0; i<itemsArr.length; i++){
                    specialsArray.add(itemsArr[i]);
                }

                arrList = specialsArray;
        }

        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0; i<tempID.length; i++){
                    tempID[i] = getItemID;
                    getItemID++;
                }
                newItem(v, inflater, tempID);
                items++;
            }
        });

        for(int i=0; i<arrList.size(); i++){
            String s = arrList.get(i);
            s=s.replaceFirst("\\^", "");
            String[] item = s.split(">");
            createListItem(v, item[0], item[3]);
        }

    }

    ImageView close;
    Button saveItem;
    EditText itemName;
    EditText price;
    EditText itemDetails;
    EditText foodType;

    public void newItem(final View v, LayoutInflater inflater, int[] IDs){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View alertView = inflater.inflate(R.layout.new_item_layout, null);
        builder.setView(alertView);
        final AlertDialog alert = builder.create();
        alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        alert.show();

        close = (ImageView)alert.findViewById(R.id.newItemClose);
        saveItem = (Button)alert.findViewById(R.id.newItemSave);
        itemName = (EditText)alert.findViewById(R.id.newItemName);
        price = (EditText)alert.findViewById(R.id.newItemPrice);
        itemDetails = (EditText)alert.findViewById(R.id.newItemDetails);
        foodType = (EditText)alert.findViewById(R.id.newFoodType);

        itemName.setId(IDs[0]);
        price.setId(IDs[1]);
        itemDetails.setId(IDs[2]);
        foodType.setId(IDs[3]);

        itemName.requestFocus();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                alert.dismiss();
            }
        });

        saveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = itemName.getText().toString(),
                        priceStr = price.getText().toString(),
                        details = itemDetails.getText().toString(),
                        type = foodType.getText().toString();

                if(name.length() > 0 && priceStr.length() > 0 && details.length() > 0 && type.length() > 0) {
                    tabBuilder(v, name, priceStr, details, type);

                    if(!duplicate){
                        alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        alert.dismiss();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please Fill Out All Fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    boolean duplicate = false;

    public void tabBuilder(View v, String name, String price, String details, String type){

        String nl = System.getProperty("line.separator");
        if(details.contains(nl)){
            details=details.replaceAll(nl, "\\|");
        }

        String str = "^" + name + ">" + price + ">" + details + ">" + type;

        switch (this.getTag()){
            case "Appetizers":
                if(appetizersArray != null) {
                    if(appetizersArray.size() > 0) {
                        for (int i = 0; i < appetizersArray.size(); i++) {
                            if (appetizersArray.get(i).equals(str)) {
                                Toast.makeText(getActivity(), "Menu Item Already Exists", Toast.LENGTH_LONG).show();
                                duplicate = true;
                            } else {
                                appetizersArray.add(str);
                                duplicate = false;
                                break;
                            }
                        }
                    } else {
                        appetizersArray.add(str);
                        duplicate = false;
                    }
                }
                break;
            case "Entrees":
                if(entreesArray != null) {
                    if(entreesArray.size() > 0) {
                        for (int i = 0; i < entreesArray.size(); i++) {
                            if (entreesArray.get(i).equals(str)) {
                                Toast.makeText(getActivity(), "Menu Item Already Exists", Toast.LENGTH_LONG).show();
                                duplicate = true;
                            } else {
                                entreesArray.add(str);
                                duplicate = false;
                            }
                        }
                    } else {
                        entreesArray.add(str);
                        duplicate = false;
                    }
                }
                break;
            case "Desserts":
                if(dessertsArray != null) {
                    if(dessertsArray.size() > 0) {
                        for (int i = 0; i < dessertsArray.size(); i++) {
                            if (dessertsArray.get(i).equals(str)) {
                                Toast.makeText(getActivity(), "Menu Item Already Exists", Toast.LENGTH_LONG).show();
                                duplicate = true;
                            } else {
                                dessertsArray.add(str);
                                duplicate = false;
                            }
                        }
                    } else {
                        dessertsArray.add(str);
                        duplicate = false;
                    }
                }
                break;
            case "Beverages":
                if(beveragesArray != null) {
                    if(beveragesArray.size() > 0) {
                        for (int i = 0; i < beveragesArray.size(); i++) {
                            if (beveragesArray.get(i).equals(str)) {
                                Toast.makeText(getActivity(), "Menu Item Already Exists", Toast.LENGTH_LONG).show();
                                duplicate = true;
                            } else {
                                beveragesArray.add(str);
                                duplicate = false;
                            }
                        }
                    } else {
                        beveragesArray.add(str);
                        duplicate = false;
                    }
                }
                break;
            case "Kids":
                if(kidsArray != null) {
                    if(kidsArray.size() > 0) {
                        for (int i = 0; i < kidsArray.size(); i++) {
                            if (kidsArray.get(i).equals(str)) {
                                Toast.makeText(getActivity(), "Menu Item Already Exists", Toast.LENGTH_LONG).show();
                                duplicate = true;
                            } else {
                                kidsArray.add(str);
                                duplicate = false;
                            }
                        }
                    } else {
                        kidsArray.add(str);
                        duplicate = false;
                    }
                }
                break;
            case "Specials":
                if(specialsArray != null) {
                    if(specialsArray.size() > 0) {
                        for (int i = 0; i < specialsArray.size(); i++) {
                            if (specialsArray.get(i).equals(str)) {
                                Toast.makeText(getActivity(), "Menu Item Already Exists", Toast.LENGTH_LONG).show();
                                duplicate = true;
                            } else {
                                specialsArray.add(str);
                                duplicate = false;
                            }
                        }
                    } else {
                        specialsArray.add(str);
                        duplicate = false;
                    }
                }
        }

        if(!duplicate) {
            createListItem(v, name, type);
        }

    }

    public void createListItem(View v, final String name, final String type){

        final LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);

        final LinearLayout layout = (LinearLayout)v.findViewById(R.id.tabFoodGroupFrame);
        final View itemLayout = layoutInflater.inflate(R.layout.item_list_view, null);
        ImageView minusItem = (ImageView)itemLayout.findViewById(R.id.minusItem);
        TextView listItem = (TextView)itemLayout.findViewById(R.id.listItem);
        TextView listType = (TextView)itemLayout.findViewById(R.id.listType);

        listItem.setText(name);
        listType.setText(type);

        layout.addView(itemLayout);

        minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.removeView(itemLayout);

                switch (getTag()) {
                    case "Appetizers":
                        if (appetizersArray != null && appetizersArray.size() > 0) {
                            for (int i = 0; i < appetizersArray.size(); i++) {
                                if (appetizersArray.get(i).contains(name) && appetizersArray.get(i).contains(type)) {
                                    appetizersArray.remove(i);
                                }
                            }
                        }
                        break;
                    case "Entrees":
                        if (entreesArray != null && entreesArray.size() > 0) {
                            for (int i = 0; i < entreesArray.size(); i++) {
                                if (entreesArray.get(i).contains(name) && entreesArray.get(i).contains(type)) {
                                    entreesArray.remove(i);
                                }
                            }
                        }
                        break;
                    case "Desserts":
                        if (dessertsArray != null && dessertsArray.size() > 0) {
                            for (int i = 0; i < dessertsArray.size(); i++) {
                                if (dessertsArray.get(i).contains(name) && dessertsArray.get(i).contains(type)) {
                                    dessertsArray.remove(i);
                                }
                            }
                        }
                        break;
                    case "Beverages":
                        if (beveragesArray != null && beveragesArray.size() > 0) {
                            for (int i = 0; i < beveragesArray.size(); i++) {
                                if (beveragesArray.get(i).contains(name) && beveragesArray.get(i).contains(type)) {
                                    beveragesArray.remove(i);
                                }
                            }
                        }
                        break;
                    case "Kids":
                        if (kidsArray != null && kidsArray.size() > 0) {
                            for (int i = 0; i < kidsArray.size(); i++) {
                                if (kidsArray.get(i).contains(name) && kidsArray.get(i).contains(type)) {
                                    kidsArray.remove(i);
                                }
                            }
                        }
                        break;
                    case "Specials":
                        if (specialsArray != null && specialsArray.size() > 0) {
                            for (int i = 0; i < specialsArray.size(); i++) {
                                if (specialsArray.get(i).contains(name) && specialsArray.get(i).contains(type)) {
                                    specialsArray.remove(i);
                                }
                            }
                        }
                }
            }
        });

    }

    @Override
    public void onPause(){
        super.onPause();

        tempSave.tempSave().appetizersArray = appetizersArray;
        tempSave.tempSave().entreesArray = entreesArray;
        tempSave.tempSave().dessertsArray = dessertsArray;
        tempSave.tempSave().beveragesArray = beveragesArray;
        tempSave.tempSave().kidsArray = kidsArray;
        tempSave.tempSave().specialsArray = specialsArray;
    }
}
