package com.example.busticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class admin_home extends Fragment {
    TextView totalUsers,aname,new_user,totalbus,tbooking;
    DBhelper myDB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totalUsers=view.findViewById(R.id.users);
        aname=view.findViewById(R.id.admin_name);
        new_user=view.findViewById(R.id.new_user);
        totalbus=view.findViewById(R.id.adminbuses);
        tbooking=view.findViewById(R.id.booked);

        Intent intent=getActivity().getIntent();
        String admin_name=intent.getStringExtra("uname");
        aname.setText(""+admin_name);

        myDB=new DBhelper(getActivity().getApplicationContext());
        totalUsers.setText("Total Users:"+myDB.getTotalUsers());
        new_user.setText("New Users:"+myDB.getNewUser());
        totalbus.setText("Total Buses:"+myDB.getBusesTotal());
        tbooking.setText("Total Booking:"+myDB.getTotalBooking());
    }
}