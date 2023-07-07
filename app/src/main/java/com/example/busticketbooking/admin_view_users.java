package com.example.busticketbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class admin_view_users extends Fragment {
    DBhelper myDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_admin_view_users, container, false);
        myDB=new DBhelper(getActivity().getApplicationContext());

        ArrayList<HashMap<String, String>> userList = myDB.getUsers();
        ListView lv = view.findViewById(R.id.listViewUsers);

        ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), userList, R.layout.admin_users_list_view,new String[]{"username","mobile","email","role"}, new int[]{R.id.textUserName, R.id.textUserMobile, R.id.textUserEmail, R.id.textUserRole});
        lv.setAdapter(adapter);

        return view;
    }
}