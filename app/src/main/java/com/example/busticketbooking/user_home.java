package com.example.busticketbooking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class user_home extends Fragment {
    Button displayDate,searchBus;
    EditText tvdate,source,destination;
    TextView uname;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public user_home() {
        // Required empty public constructor
    }

    public static user_home newInstance(String param1, String param2) {
        user_home fragment = new user_home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvdate=view.findViewById(R.id.date);
        displayDate=view.findViewById(R.id.date_button);
        source=view.findViewById(R.id.source);
        destination=view.findViewById(R.id.destination);
        searchBus=view.findViewById(R.id.bus_search);

        uname=view.findViewById(R.id.uname);
        Intent i=getActivity().getIntent();
        uname.setText("Hey "+i.getStringExtra("uname"));

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tvdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        searchBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from=source.getText().toString();
                String to=destination.getText().toString();
                String tripDate=tvdate.getText().toString();

                if (from.equals("")||to.equals("")||tripDate.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Fill all Field", Toast.LENGTH_SHORT).show();
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("from",from);
                    bundle.putString("to",to);
                    bundle.putString("date",tripDate);
                    user_view_buses fragment2 = new user_view_buses();
                    fragment2.setArguments(bundle);
                    FragmentManager fm = getParentFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.addToBackStack("fragment2");
                    ft.replace(R.id.user_fragment_container, fragment2,"fragment2");
                    ft.commit();
                }
            }
        });
    }
}