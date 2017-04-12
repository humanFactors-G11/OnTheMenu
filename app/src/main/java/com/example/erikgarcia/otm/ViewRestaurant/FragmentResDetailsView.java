package com.example.erikgarcia.otm.ViewRestaurant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.erikgarcia.otm.R;

/**
 * Created by Maverick on 3/31/2017.
 */

public class FragmentResDetailsView extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.content_res_details_view , container, false);

        return view;
    }


}
