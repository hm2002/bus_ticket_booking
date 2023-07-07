package com.example.busticketbooking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link admin_view_buses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class admin_view_buses extends Fragment {
    DBhelper myDB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public admin_view_buses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment admin_view_buses.
     */
    // TODO: Rename and change types and number of parameters
    public static admin_view_buses newInstance(String param1, String param2) {
        admin_view_buses fragment = new admin_view_buses();
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
        View view = inflater.inflate(R.layout.fragment_admin_view_buses, container, false);

        myDB=new DBhelper(getActivity().getApplicationContext());

        ArrayList<HashMap<String, String>> companyBuses = myDB.getTotalCompany();
        ListView lv = view.findViewById(R.id.listTotalBuses);

        ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), companyBuses, R.layout.admin_total_buses_list,new String[]{"username","source","destination","price","class","departTime","arriveTime"}, new int[]{R.id.cname,R.id.busSource, R.id.busDestination, R.id.busPrice, R.id.busClass, R.id.busdepartTime, R.id.busarriveTime});
        lv.setAdapter(adapter);

        return view;
    }
}