package com.example.busticketbooking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link seat_layout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class seat_layout extends Fragment implements View.OnClickListener{
    int count = 0,flag = 0;
    String uid = "",un="";
    double ticketPrice = 0;
    String seat_no="",trip_date="",bid="";
    DBhelper myDB;
    Cursor cursor;

    Button btn1, btn2 ,btn3, btn4, btn5 ,btn6, btn7, btn8, btn9, btn10;
    Button btn11, btn12 ,btn13, btn14, btn15 ,btn16, btn17, btn18, btn19, btn20;
    Button btn21, btn22 ,btn23, btn24, btn25 ,btn26, btn27, btn28, btn29, btn30;
    Button btn31, btn32 ,btn33, btn34, btn35 ,btn36, btn37, btn38, btn39, btn40;

    TextView seat,price;
    Button proceed;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public seat_layout() {
        // Required empty public constructor
    }
    public static seat_layout newInstance(String param1, String param2) {
        seat_layout fragment = new seat_layout();
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
        View view = inflater.inflate(R.layout.fragment_seat_layout, container, false);
        btn1=view.findViewById(R.id.btn1);
        btn2=view.findViewById(R.id.btn2);
        btn3=view.findViewById(R.id.btn3);
        btn4=view.findViewById(R.id.btn4);
        btn5=view.findViewById(R.id.btn5);
        btn6=view.findViewById(R.id.btn6);
        btn7=view.findViewById(R.id.btn7);
        btn8=view.findViewById(R.id.btn8);
        btn9=view.findViewById(R.id.btn9);
        btn10=view.findViewById(R.id.btn10);

        btn11=view.findViewById(R.id.btn11);
        btn12=view.findViewById(R.id.btn12);
        btn13=view.findViewById(R.id.btn13);
        btn14=view.findViewById(R.id.btn14);
        btn15=view.findViewById(R.id.btn15);
        btn16=view.findViewById(R.id.btn16);
        btn17=view.findViewById(R.id.btn17);
        btn18=view.findViewById(R.id.btn18);
        btn19=view.findViewById(R.id.btn19);
        btn20=view.findViewById(R.id.btn20);

        btn21=view.findViewById(R.id.btn21);
        btn22=view.findViewById(R.id.btn22);
        btn23=view.findViewById(R.id.btn23);
        btn24=view.findViewById(R.id.btn24);
        btn25=view.findViewById(R.id.btn25);
        btn26=view.findViewById(R.id.btn26);
        btn27=view.findViewById(R.id.btn27);
        btn28=view.findViewById(R.id.btn28);
        btn29=view.findViewById(R.id.btn29);
        btn30=view.findViewById(R.id.btn30);

        btn31=view.findViewById(R.id.btn31);
        btn32=view.findViewById(R.id.btn32);
        btn33=view.findViewById(R.id.btn33);
        btn34=view.findViewById(R.id.btn34);
        btn35=view.findViewById(R.id.btn35);
        btn36=view.findViewById(R.id.btn36);
        btn37=view.findViewById(R.id.btn37);
        btn38=view.findViewById(R.id.btn38);
        btn39=view.findViewById(R.id.btn39);
        btn40=view.findViewById(R.id.btn40);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);

        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);
        btn18.setOnClickListener(this);
        btn19.setOnClickListener(this);
        btn20.setOnClickListener(this);

        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);
        btn23.setOnClickListener(this);
        btn24.setOnClickListener(this);
        btn25.setOnClickListener(this);
        btn26.setOnClickListener(this);
        btn27.setOnClickListener(this);
        btn28.setOnClickListener(this);
        btn29.setOnClickListener(this);
        btn30.setOnClickListener(this);

        btn31.setOnClickListener(this);
        btn32.setOnClickListener(this);
        btn33.setOnClickListener(this);
        btn34.setOnClickListener(this);
        btn35.setOnClickListener(this);
        btn36.setOnClickListener(this);
        btn37.setOnClickListener(this);
        btn38.setOnClickListener(this);
        btn39.setOnClickListener(this);
        btn40.setOnClickListener(this);

        seat=view.findViewById(R.id.seatSelection);
        price=view.findViewById(R.id.seatPrice);
        proceed=view.findViewById(R.id.proceed);

        Intent in=getActivity().getIntent();
        un=in.getStringExtra("uname");

        myDB = new DBhelper(getActivity().getApplicationContext());

        Bundle bundle = this.getArguments();
        ticketPrice = Double.parseDouble(bundle.getString("price"));
        trip_date = bundle.getString("date");
        bid = bundle.getString("B_ID");

        Intent i=getActivity().getIntent();
        String user=i.getStringExtra("uname");
        cursor = myDB.getUserId(user);
        if (cursor.moveToFirst()) {
            uid = Integer.toString(cursor.getShort(0));
        }
        cursor.close();

        String seatDate = "";
        cursor = myDB.getSeatDate(bid,trip_date);
        if (cursor.moveToFirst()) {
            seatDate = cursor.getString(0);
        }
        cursor.close();
        if (seatDate.equals(trip_date)){
            if (myDB.checkSeat("A1",seatDate,bid)!=0){
                btn1.setEnabled(false);
            }
            if (myDB.checkSeat("A2",seatDate,bid)!=0){
                btn2.setEnabled(false);
            }
            if (myDB.checkSeat("A3",seatDate,bid)!=0){
                btn3.setEnabled(false);
            }
            if (myDB.checkSeat("A4",seatDate,bid)!=0){
                btn4.setEnabled(false);
            }
            if (myDB.checkSeat("A5",seatDate,bid)!=0){
                btn5.setEnabled(false);
            }
            if (myDB.checkSeat("A6",seatDate,bid)!=0){
                btn6.setEnabled(false);
            }
            if (myDB.checkSeat("A7",seatDate,bid)!=0){
                btn7.setEnabled(false);
            }
            if (myDB.checkSeat("A8",seatDate,bid)!=0){
                btn8.setEnabled(false);
            }
            if (myDB.checkSeat("A9",seatDate,bid)!=0){
                btn9.setEnabled(false);
            }
            if (myDB.checkSeat("A0",seatDate,bid)!=0){
                btn10.setEnabled(false);
            }
            if (myDB.checkSeat("B1",seatDate,bid)!=0){
                btn11.setEnabled(false);
            }
            if (myDB.checkSeat("B2",seatDate,bid)!=0){
                btn12.setEnabled(false);
            }
            if (myDB.checkSeat("B3",seatDate,bid)!=0){
                btn13.setEnabled(false);
            }
            if (myDB.checkSeat("B4",seatDate,bid)!=0){
                btn14.setEnabled(false);
            }
            if (myDB.checkSeat("B5",seatDate,bid)!=0){
                btn15.setEnabled(false);
            }
            if (myDB.checkSeat("B6",seatDate,bid)!=0){
                btn16.setEnabled(false);
            }
            if (myDB.checkSeat("B7",seatDate,bid)!=0){
                btn17.setEnabled(false);
            }
            if (myDB.checkSeat("B8",seatDate,bid)!=0){
                btn18.setEnabled(false);
            }
            if (myDB.checkSeat("B9",seatDate,bid)!=0){
                btn19.setEnabled(false);
            }
            if (myDB.checkSeat("B0",seatDate,bid)!=0){
                btn20.setEnabled(false);
            }
            if (myDB.checkSeat("C1",seatDate,bid)!=0){
                btn21.setEnabled(false);
            }
            if (myDB.checkSeat("C2",seatDate,bid)!=0){
                btn22.setEnabled(false);
            }
            if (myDB.checkSeat("C3",seatDate,bid)!=0){
                btn23.setEnabled(false);
            }
            if (myDB.checkSeat("C4",seatDate,bid)!=0){
                btn24.setEnabled(false);
            }
            if (myDB.checkSeat("C5",seatDate,bid)!=0){
                btn25.setEnabled(false);
            }
            if (myDB.checkSeat("C6",seatDate,bid)!=0){
                btn26.setEnabled(false);
            }
            if (myDB.checkSeat("C7",seatDate,bid)!=0){
                btn27.setEnabled(false);
            }
            if (myDB.checkSeat("C8",seatDate,bid)!=0){
                btn28.setEnabled(false);
            }
            if (myDB.checkSeat("C9",seatDate,bid)!=0){
                btn29.setEnabled(false);
            }
            if (myDB.checkSeat("C0",seatDate,bid)!=0){
                btn30.setEnabled(false);
            }
            if (myDB.checkSeat("D1",seatDate,bid)!=0){
                btn31.setEnabled(false);
            }
            if (myDB.checkSeat("D2",seatDate,bid)!=0){
                btn32.setEnabled(false);
            }
            if (myDB.checkSeat("D3",seatDate,bid)!=0){
                btn33.setEnabled(false);
            }
            if (myDB.checkSeat("D4",seatDate,bid)!=0){
                btn34.setEnabled(false);
            }
            if (myDB.checkSeat("D5",seatDate,bid)!=0){
                btn35.setEnabled(false);
            }
            if (myDB.checkSeat("D6",seatDate,bid)!=0){
                btn36.setEnabled(false);
            }
            if (myDB.checkSeat("D7",seatDate,bid)!=0){
                btn37.setEnabled(false);
            }
            if (myDB.checkSeat("D8",seatDate,bid)!=0){
                btn38.setEnabled(false);
            }
            if (myDB.checkSeat("D9",seatDate,bid)!=0){
                btn39.setEnabled(false);
            }
            if (myDB.checkSeat("D0",seatDate,bid)!=0){
                btn40.setEnabled(false);
            }
        }

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==0){
                    Toast.makeText(getActivity().getApplicationContext(), "Please select At least one seat", Toast.LENGTH_SHORT).show();
                }else{
//                    Bundle bundle = new Bundle();
//                    bundle.putString("price",Double.toString(count*ticketPrice));
//                    bundle.putString("seat_no",seat_no);
//                    bundle.putString("date",trip_date);
//                    bundle.putString("B_ID",bid);
                    Intent intent = new Intent(getActivity(),paymentModule.class);
                    intent.putExtra("price",Double.toString(count*ticketPrice));
                    intent.putExtra("seat_no",seat_no);
                    intent.putExtra("date",trip_date);
                    intent.putExtra("B_ID",bid);
                    intent.putExtra("uid",uid);
                    intent.putExtra("uname",un);
                    startActivity(intent);

//                    paymentModule fragment2 = new paymentModule();
//                    fragment2.setArguments(bundle);
//                    FragmentManager fm = getParentFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
//                    ft.addToBackStack("fragment2");
//                    ft.replace(R.id.user_fragment_container, fragment2,"fragment2");
//                    ft.commit();
                }
            }
        });

        setupOnBackPressed();
        return view;
    }
    @Override
    public void onClick(View v) {
        if (flag>3){
            Toast.makeText(getActivity().getApplicationContext(), "You Can Only Book Maximum 4 Seats", Toast.LENGTH_LONG).show();
        }else{
            flag++;
            switch (v.getId()){
                case R.id.btn1:
                    count++;
                    btn1.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A1,";
                    Toast.makeText(getActivity().getApplicationContext(), seat_no, Toast.LENGTH_LONG).show();
                    break;

                case R.id.btn2:
                    count++;
                    btn2.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A2,";
                    break;

                case R.id.btn3:
                    count++;
                    btn3.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A3,";
                    break;

                case R.id.btn4:
                    count++;
                    btn4.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A4,";
                    break;

                case R.id.btn5:
                    count++;
                    btn5.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A5,";
                    break;

                case R.id.btn6:
                    count++;
                    btn6.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A6,";
                    break;

                case R.id.btn7:
                    count++;
                    btn7.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A7,";
                    break;

                case R.id.btn8:
                    count++;
                    btn8.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A8,";
                    break;

                case R.id.btn9:
                    count++;
                    btn9.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A9,";
                    break;

                case R.id.btn10:
                    count++;
                    btn10.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"A0,";
                    break;

                case R.id.btn11:
                    count++;
                    btn11.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B1,";
                    break;

                case R.id.btn12:
                    count++;
                    btn12.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B2,";
                    break;

                case R.id.btn13:
                    count++;
                    btn13.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B3,";
                    break;

                case R.id.btn14:
                    count++;
                    btn14.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B4,";
                    break;

                case R.id.btn15:
                    count++;
                    btn15.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B5,";
                    break;

                case R.id.btn16:
                    count++;
                    btn16.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B6,";
                    break;

                case R.id.btn17:
                    count++;
                    btn17.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B7,";
                    break;

                case R.id.btn18:
                    count++;
                    btn18.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B8,";
                    break;

                case R.id.btn19:
                    count++;
                    btn19.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B9,";
                    break;

                case R.id.btn20:
                    count++;
                    btn20.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"B0,";
                    break;

                case R.id.btn21:
                    count++;
                    btn21.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C1,";
                    break;

                case R.id.btn22:
                    count++;
                    btn22.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C2,";
                    break;

                case R.id.btn23:
                    count++;
                    btn23.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C3,";
                    break;

                case R.id.btn24:
                    count++;
                    btn24.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C4,";
                    break;

                case R.id.btn25:
                    count++;
                    btn25.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C5,";
                    break;

                case R.id.btn26:
                    count++;
                    btn26.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C6,";
                    break;

                case R.id.btn27:
                    count++;
                    btn27.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C7,";
                    break;

                case R.id.btn28:
                    count++;
                    btn28.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C8,";
                    break;

                case R.id.btn29:
                    count++;
                    btn29.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C9,";
                    break;

                case R.id.btn30:
                    count++;
                    btn30.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"C0,";
                    break;

                case R.id.btn31:
                    count++;
                    btn31.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D1,";
                    break;

                case R.id.btn32:
                    count++;
                    btn32.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D2,";
                    break;

                case R.id.btn33:
                    count++;
                    btn33.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D3,";
                    break;

                case R.id.btn34:
                    count++;
                    btn34.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D4,";
                    break;

                case R.id.btn35:
                    count++;
                    btn35.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D5,";
                    break;

                case R.id.btn36:
                    count++;
                    btn36.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D6,";
                    break;

                case R.id.btn37:
                    count++;
                    btn37.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D7,";
                    break;

                case R.id.btn38:
                    count++;
                    btn38.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D8,";
                    break;

                case R.id.btn39:
                    count++;
                    btn39.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D9,";
                    break;

                case R.id.btn40:
                    count++;
                    btn40.setEnabled(false);
                    seat.setText("Seat:"+count);
                    price.setText("Price:"+count*ticketPrice);
                    seat_no=seat_no+"D0,";
                    break;
            }
        }
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