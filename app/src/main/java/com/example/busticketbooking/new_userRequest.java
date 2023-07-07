package com.example.busticketbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

public class new_userRequest extends Fragment {
    DBhelper myDB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_new_user_request, container, false);
        myDB=new DBhelper(getActivity().getApplicationContext());

        ArrayList<HashMap<String, String>> userList = myDB.getNewUsers();
        ListView lv = view.findViewById(R.id.listViewEmployees);

        ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), userList, R.layout.new_user_list_view,new String[]{"username","mobile","email","role"}, new int[]{R.id.textViewName, R.id.textViewMobile, R.id.textViewEmail, R.id.textViewRole});
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> uname = userList.get(position);
                String usr = uname.get("id");

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Grant Permission");
                builder.setMessage("Are you Sure You want to Grant Permission ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDB.grantPermission(usr);
//                        getActivity().finish();
                        ArrayList<HashMap<String, String>> userList = myDB.getNewUsers();

                        ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), userList, R.layout.new_user_list_view,new String[]{"username","mobile","email","role"}, new int[]{R.id.textViewName, R.id.textViewMobile, R.id.textViewEmail, R.id.textViewRole});
                        lv.setAdapter(adapter);
//                        Intent i=new Intent(getActivity().getApplicationContext(),admin_panel.class);
//                        startActivity(i);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}