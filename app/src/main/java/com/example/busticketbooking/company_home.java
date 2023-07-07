package com.example.busticketbooking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link company_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class company_home extends Fragment {
    TextView cname,totalBus,tbooking;
    DBhelper myDB;
    Cursor cursor;
    int bid=0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public company_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment company_home.
     */
    // TODO: Rename and change types and number of parameters
    public static company_home newInstance(String param1, String param2) {
        company_home fragment = new company_home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_company_home, container, false);
        cname=view.findViewById(R.id.company_name);
        totalBus=view.findViewById(R.id.buses);
        tbooking=view.findViewById(R.id.company_booked);
        myDB=new DBhelper(getActivity().getApplicationContext());

        Intent intent=getActivity().getIntent();
        String company_name=intent.getStringExtra("uname");
        cname.setText(""+company_name);

        int id = 0;
        cursor = myDB.getUserId(company_name);
        if (cursor.moveToFirst()) {
            id = cursor.getShort(0);
        }
        cursor.close();


        cursor = myDB.getBusId(id);
        if (cursor.moveToFirst()) {
            bid = cursor.getShort(0);
        }
        cursor.close();

        myDB=new DBhelper(getActivity().getApplicationContext());
        totalBus.setText("Total Buses:"+myDB.getCompanyTotal(id));
        tbooking.setText("Total Booking:"+myDB.getTotalCompanyRow());

        return view;
    }
}