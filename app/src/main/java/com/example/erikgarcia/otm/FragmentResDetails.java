package com.example.erikgarcia.otm;


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by Maverick on 3/26/2017.
 */

public class FragmentResDetails extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_new_res_details, container, false);

        return view;
    }

    public void time(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View timePickerLayout = getActivity().getLayoutInflater().inflate(R.layout.time_picker, null);
        builder.setView(timePickerLayout);

        final AlertDialog alert = builder.create();
        alert.show();

        final TimePicker time = (TimePicker) alert.findViewById(R.id.timePicker);
        final TextView textView = (TextView) v;

        Button save = (Button) alert.findViewById(R.id.timePickerSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int h = time.getCurrentHour(), m = time.getCurrentMinute();
                int hour = h % 12;
                if (hour == 0) {
                    hour = 12;
                }
                textView.setText(String.format("%2d:%02d %s", hour == 0 ? 12 : hour,
                        m, h < 12 ? "am" : "pm"));

                switch (textView.getId()) {
                    case R.id.sunOpen:
                        textView.append("  -");
                        break;
                    case R.id.monOpen:
                        textView.append("  -");
                        break;
                    case R.id.tuesOpen:
                        textView.append("  -");
                        break;
                    case R.id.wedOpen:
                        textView.append("  -");
                        break;
                    case R.id.thuOpen:
                        textView.append("  -");
                        break;
                    case R.id.friOpen:
                        textView.append("  -");
                        break;
                    case R.id.satOpen:
                        textView.append("  -");
                }

                String setTime = h + ":" + m;
                if (setTime.equals("") || setTime.equals(null)) {
                    textView.setText("Closed");
                }

                switchText(v, textView);

                alert.dismiss();
            }
        });

        Button closed = (Button) alert.findViewById(R.id.timePickerClosed);
        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Closed");
                switchText(v, textView);
                alert.dismiss();
            }
        });
    }

    private void switchText(View v, TextView t) {

        final TextView sunOpen = (TextView) getActivity().findViewById(R.id.sunOpen);
        final TextView monOpen = (TextView) getActivity().findViewById(R.id.monOpen);
        final TextView tuesOpen = (TextView) getActivity().findViewById(R.id.tuesOpen);
        final TextView wedOpen = (TextView) getActivity().findViewById(R.id.wedOpen);
        final TextView thuOpen = (TextView) getActivity().findViewById(R.id.thuOpen);
        final TextView friOpen = (TextView) getActivity().findViewById(R.id.friOpen);
        final TextView satOpen = (TextView) getActivity().findViewById(R.id.satOpen);

        switch (v.getId()) {
            case R.id.sunOpen:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.sunClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.sunClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.sunClose:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.sunClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    sunOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.monOpen:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.monClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.monClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.monClose:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.monClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    monOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.tuesOpen:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.tuesClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.tuesClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.tuesClose:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.tuesClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    tuesOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.wedOpen:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.wedClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.wedClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.wedClose:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.wedClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    wedOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.thuOpen:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.thuClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.thuClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.thuClose:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.thuClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    thuOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.friOpen:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.friClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.friClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.friClose:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.friClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    friOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.satOpen:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.satClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.satClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.satClose:
                if (t.getText().toString().equals("Closed")) {
                    getActivity().findViewById(R.id.satClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    satOpen.setText("Closed");
                }
        }
    }
}
