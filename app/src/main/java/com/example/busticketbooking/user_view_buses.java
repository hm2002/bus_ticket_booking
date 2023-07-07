package com.example.busticketbooking;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user_view_buses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user_view_buses extends Fragment {
    DBhelper myDB;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public user_view_buses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment user_view_buses.
     */
    // TODO: Rename and change types and number of parameters
    public static user_view_buses newInstance(String param1, String param2) {
        user_view_buses fragment = new user_view_buses();
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
        View view = inflater.inflate(R.layout.fragment_user_view_buses, container, false);
        myDB=new DBhelper(getActivity().getApplicationContext());

        Bundle bundle = this.getArguments();
        String from = bundle.getString("from");
        String to = bundle.getString("to");
        String tripDate = bundle.getString("date");

        ArrayList<HashMap<String, String>> userBuses = myDB.getUserBuses(from,to);
        ListView lv = view.findViewById(R.id.listUserBuses);

        ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), userBuses, R.layout.user_buses_list,new String[]{"username","source","destination","price","class","departTime","arriveTime"}, new int[]{R.id.cname,R.id.busSource, R.id.busDestination, R.id.busPrice, R.id.busClass, R.id.busdepartTime, R.id.busarriveTime});
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> price = userBuses.get(position);
                String ticketPrice = price.get("price");
                String B_ID = price.get("id");

                Bundle bundle = new Bundle();
                bundle.putString("price",ticketPrice);
                bundle.putString("date",tripDate);
                bundle.putString("B_ID",B_ID);
                seat_layout fragment2 = new seat_layout();
                fragment2.setArguments(bundle);
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack("fragment2");
                ft.replace(R.id.user_fragment_container, fragment2,"fragment2");
                ft.commit();
            }
        });

        setupOnBackPressed();
        return view;
    }
    private void setupOnBackPressed(){
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (isEnabled()){
                    setEnabled(false);
                    requireActivity().onBackPressed();
                }
            }
        });
    }
}