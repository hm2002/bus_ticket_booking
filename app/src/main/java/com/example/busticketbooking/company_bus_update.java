package com.example.busticketbooking;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link company_bus_update#newInstance} factory method to
 * create an instance of this fragment.
 */
public class company_bus_update extends Fragment {
    EditText source,destination,ticket,bclass,dtime,atime;
    Button departTime,arriveTime,update;
    Cursor cursor;
    DBhelper myDB;
    String sr="",ds="",price="",bc="",dt="",at="",id="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public company_bus_update() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment company_bus_update.
     */
    // TODO: Rename and change types and number of parameters
    public static company_bus_update newInstance(String param1, String param2) {
        company_bus_update fragment = new company_bus_update();
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

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_bus_update, container, false);
        source=view.findViewById(R.id.companySource);
        destination=view.findViewById(R.id.companyDestination);
        ticket=view.findViewById(R.id.companyPrice);
        bclass=view.findViewById(R.id.companyClass);
        dtime=view.findViewById(R.id.departureTime);
        atime=view.findViewById(R.id.arrivalTime);
        departTime=view.findViewById(R.id.departTime);
        arriveTime=view.findViewById(R.id.arriveTime);
        update=view.findViewById(R.id.busRegister);

        myDB = new DBhelper(getActivity().getApplicationContext());

        Bundle bundle = this.getArguments();
        id = bundle.getString("id");


        cursor = myDB.getBus(id);
        if (cursor.moveToFirst()) {
            sr=cursor.getString(cursor.getColumnIndex("source"));
            ds=cursor.getString(cursor.getColumnIndex("destination"));
            price=cursor.getString(cursor.getColumnIndex("price"));
            bc=cursor.getString(cursor.getColumnIndex("class"));
            dt=cursor.getString(cursor.getColumnIndex("departTime"));
            at=cursor.getString(cursor.getColumnIndex("arriveTime"));
        }
        cursor.close();

        source.setText(sr);
        destination.setText(ds);
        ticket.setText(price);
        bclass.setText(bc);
        dtime.setText(dt);
        atime.setText(at);

        departTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                dtime.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        arriveTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                atime.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price=ticket.getText().toString();
                dt=dtime.getText().toString();
                at=atime.getText().toString();

                if (price.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Price should not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    myDB.updateBus(id,sr,ds,price,bc,dt,at);
                    Toast.makeText(getActivity().getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.company_fragment_container, new company_view_buses()).commit();
                }
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