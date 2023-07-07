package com.example.busticketbooking;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

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
 * Use the {@link addBuses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addBuses extends Fragment {
    EditText departureTime,arrivalTime,source,destination,ticketPrice,busClass;
    Button departTime,arriveTime,busSubmit;
    Cursor cursor;
    DBhelper myDB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addBuses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addBuses.
     */
    // TODO: Rename and change types and number of parameters
    public static addBuses newInstance(String param1, String param2) {
        addBuses fragment = new addBuses();
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
        View view = inflater.inflate(R.layout.fragment_add_buses, container, false);

        departureTime = view.findViewById(R.id.departureTime);
        arrivalTime = view.findViewById(R.id.arrivalTime);
        departTime=view.findViewById(R.id.departTime);
        arriveTime=view.findViewById(R.id.arriveTime);
        source=view.findViewById(R.id.companySource);
        destination=view.findViewById(R.id.companyDestination);
        ticketPrice=view.findViewById(R.id.companyPrice);
        busClass=view.findViewById(R.id.companyClass);
        busSubmit=view.findViewById(R.id.busRegister);

        myDB=new DBhelper(getActivity().getApplicationContext());

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
                                departureTime.setText(hourOfDay + ":" + minute);
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
                                arrivalTime.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        busSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String depart=source.getText().toString();
                String arrival=destination.getText().toString();
                String ticket=ticketPrice.getText().toString();
                String bclass=busClass.getText().toString();
                String dtime=departureTime.getText().toString();
                String atime=arrivalTime.getText().toString();

                if (depart.equals("")||arrival.equals("")||ticket.equals("")||bclass.equals("")||dtime.equals("")||atime.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Fill all the Field", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=getActivity().getIntent();
                    String company_name=intent.getStringExtra("uname");
                    int id = 0;
                    cursor = myDB.getUserId(company_name);
                    if (cursor.moveToFirst()) {
                        id = cursor.getShort(0);
                    }
                    cursor.close();

                    Boolean addBus = myDB.insertBus(id,depart,arrival,ticket,bclass,dtime,atime);

                    if (addBus==true){
                        Toast.makeText(getActivity().getApplicationContext(), "Bus added Successfully", Toast.LENGTH_SHORT).show();
                        departureTime.setText("");
                        arrivalTime.setText("");
                        source.setText("");
                        destination.setText("");
                        ticketPrice.setText("");
                        busClass.setText("");
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "An Error Occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}