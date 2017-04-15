package com.example.erikgarcia.otm.ViewRestaurant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.erikgarcia.otm.R;

/**
 * Created by Maverick on 3/31/2017.
 */

public class FragmentResMenuView extends Fragment {
    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.content_res_menu_view, container, false);

        mTabHost = (FragmentTabHost)view.findViewById(R.id.resMenuTabHostView);
        mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        String[] tabs = getResources().getStringArray(R.array.tab_tags);

        for(int i=0; i<tabs.length; i++){
            mTabHost.addTab(
                    mTabHost.newTabSpec(tabs[i]).setIndicator(tabs[i], null),
                    FragmentResTabView.class, null);
        }

        return view;
    }
}
