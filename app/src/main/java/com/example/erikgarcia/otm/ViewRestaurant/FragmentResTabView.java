package com.example.erikgarcia.otm.ViewRestaurant;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.erikgarcia.otm.R;

/**
 * Created by Maverick on 3/31/2017.
 */

public class FragmentResTabView extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_menu_tab_view, container, false);
        TextView title = (TextView)v.findViewById(R.id.menuTitleView);
        ListView listView = (ListView)v.findViewById(R.id.menuItemList);

        String[] listArray = {"Item One", "Item Two"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                itemView(v);

            }
        });


        switch (this.getTag()){
            case "Appetizers":
                title.setText(this.getTag());
                break;
            case "Etrees":
                title.setText(this.getTag());

                break;
            case "Desserts":
                title.setText(this.getTag());

                break;
            case "Beverages":
                title.setText(this.getTag());

                break;
            case "Kids":
                title.setText(this.getTag());

                break;
            case "Specials":
                title.setText(this.getTag());

        }

        return v;
    }

    public void itemView(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View itemLayout = getActivity().getLayoutInflater().inflate(R.layout.item_details_popup, null);
        builder.setView(itemLayout);

        final AlertDialog alert = builder.create();
        alert.show();

        RatingBar rating = (RatingBar)alert.findViewById(R.id.itemRatingView);
        final TextView ratingText = (TextView)alert.findViewById(R.id.itemRatingText);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingText.setText(String.valueOf(v));
            }
        });

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //Save Rating here
            }
        });
    }
}
