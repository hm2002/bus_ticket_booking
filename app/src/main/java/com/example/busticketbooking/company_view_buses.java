package com.example.busticketbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class company_view_buses extends Fragment {
    DBhelper myDB;
    Cursor cursor;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public company_view_buses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment company_view_buses.
     */
    // TODO: Rename and change types and number of parameters
    public static company_view_buses newInstance(String param1, String param2) {
        company_view_buses fragment = new company_view_buses();
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
        View view = inflater.inflate(R.layout.fragment_company_view_buses, container, false);

        myDB=new DBhelper(getActivity().getApplicationContext());

        Intent intent=getActivity().getIntent();
        String company_name=intent.getStringExtra("uname");

        int id = 0;
        cursor = myDB.getUserId(company_name);
        if (cursor.moveToFirst()) {
            id = cursor.getShort(0);
        }
        cursor.close();

        ArrayList<HashMap<String, String>> companyBuses = myDB.getCompanyBuses(id);
        ListView lv = view.findViewById(R.id.listCompanyBuses);

        ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), companyBuses, R.layout.company_buses_list,new String[]{"source","destination","price","class","departTime","arriveTime"}, new int[]{R.id.busSource, R.id.busDestination, R.id.busPrice, R.id.busClass, R.id.busdepartTime, R.id.busarriveTime});
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> uname = companyBuses.get(position);
                String usr = uname.get("id");

                Bundle bundle = new Bundle();
                bundle.putString("id",usr);
                company_bus_update fragment2 = new company_bus_update();
                fragment2.setArguments(bundle);
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack("fragment2");
                ft.replace(R.id.company_fragment_container, fragment2,"fragment2");
                ft.commit();
            }
        });

        return view;
    }
}