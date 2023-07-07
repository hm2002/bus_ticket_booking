package com.example.busticketbooking;

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
 * Use the {@link company_view_booking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class company_view_booking extends Fragment {
    DBhelper myDB;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public company_view_booking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment company_view_booking.
     */
    // TODO: Rename and change types and number of parameters
    public static company_view_booking newInstance(String param1, String param2) {
        company_view_booking fragment = new company_view_booking();
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
        View view = inflater.inflate(R.layout.fragment_company_view_booking, container, false);
        myDB=new DBhelper(getActivity().getApplicationContext());

        ArrayList<HashMap<String, String>> viewBookings = myDB.getTotalCompanyBookings();
        ListView lv = view.findViewById(R.id.listCompanyBuses2);
        ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), viewBookings, R.layout.company_booking_list,new String[]{"uname","class","source","destination","price","departTime","arriveTime","seat","date"}, new int[]{R.id.cname,R.id.busClass,R.id.busSource,R.id.busDestination,R.id.busPrice,R.id.busdepartTime,R.id.busarriveTime,R.id.seat_no,R.id.tripdate});
        lv.setAdapter(adapter);

        return view;
    }
}