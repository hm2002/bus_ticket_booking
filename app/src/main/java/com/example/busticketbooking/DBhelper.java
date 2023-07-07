package com.example.busticketbooking;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBhelper extends SQLiteOpenHelper {

    public DBhelper(Context context) {

        super(context,"busbooking.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table if not exists users(U_ID INTEGER PRIMARY KEY AUTOINCREMENT,username Text,password Text,mobile Text,email Text UNIQUE,role Text)");
        myDB.execSQL("create Table if not exists permissions(P_ID INTEGER PRIMARY KEY AUTOINCREMENT,U_ID INTEGER,permission INTEGER,FOREIGN KEY (U_ID) REFERENCES users (U_ID))");
        myDB.execSQL("create Table if not exists buses(B_ID INTEGER PRIMARY KEY AUTOINCREMENT,U_ID INTEGER,source Text,destination Text,price Text,class Text,departTime Text,arriveTime Text,seats Text,FOREIGN KEY (U_ID) REFERENCES users (U_ID))");
        myDB.execSQL("create table if not exists seats(S_ID INTEGER PRIMARY KEY AUTOINCREMENT, date Text, B_ID INTEGER, seat Text,FOREIGN KEY (B_ID) REFERENCES buses (B_ID))");
        myDB.execSQL("create table if not exists trans(T_ID INTEGER PRIMARY KEY AUTOINCREMENT, date Text, B_ID INTEGER, seat Text, amount Text, U_ID INTEGER, uname Text,FOREIGN KEY (B_ID) REFERENCES buses (B_ID),FOREIGN KEY (U_ID) REFERENCES users (U_ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists users");
        myDB.execSQL("drop Table if exists permissions");
        myDB.execSQL("drop Table if exists buses");
        myDB.execSQL("drop Table if exists seats");
        myDB.execSQL("drop Table if exists trans");
    }


    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getTotalBookings(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> viewBookings = new ArrayList<>();
        String query = "SELECT * FROM trans INNER JOIN buses ON trans.B_ID=buses.B_ID INNER JOIN users ON buses.U_ID=users.U_ID";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> buses = new HashMap<>();
            buses.put("username",cursor.getString(cursor.getColumnIndex("username")));
            buses.put("id",cursor.getString(cursor.getColumnIndex("B_ID")));
            buses.put("source",cursor.getString(cursor.getColumnIndex("source")));
            buses.put("destination",cursor.getString(cursor.getColumnIndex("destination")));
            buses.put("price",cursor.getString(cursor.getColumnIndex("amount")));
            buses.put("class",cursor.getString(cursor.getColumnIndex("class")));
            buses.put("departTime",cursor.getString(cursor.getColumnIndex("departTime")));
            buses.put("arriveTime",cursor.getString(cursor.getColumnIndex("arriveTime")));
            buses.put("seat",cursor.getString(cursor.getColumnIndex("seat")));
            buses.put("date",cursor.getString(cursor.getColumnIndex("date")));
            buses.put("uname",cursor.getString(cursor.getColumnIndex("uname")));
            viewBookings.add(buses);
        }
        return viewBookings;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getUserBookings(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> viewBookings = new ArrayList<>();
        String query = "SELECT * FROM trans INNER JOIN buses ON trans.B_ID=buses.B_ID INNER JOIN users ON buses.U_ID=users.U_ID";
//        String query = "SELECT * FROM trans INNER JOIN users ON trans.U_ID=users.U_ID INNER JOIN buses ON users.U_ID=buses.U_ID";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> buses = new HashMap<>();
            buses.put("username",cursor.getString(cursor.getColumnIndex("username")));
            buses.put("id",cursor.getString(cursor.getColumnIndex("B_ID")));
            buses.put("source",cursor.getString(cursor.getColumnIndex("source")));
            buses.put("destination",cursor.getString(cursor.getColumnIndex("destination")));
            buses.put("price",cursor.getString(cursor.getColumnIndex("amount")));
            buses.put("class",cursor.getString(cursor.getColumnIndex("class")));
            buses.put("departTime",cursor.getString(cursor.getColumnIndex("departTime")));
            buses.put("arriveTime",cursor.getString(cursor.getColumnIndex("arriveTime")));
            buses.put("seat",cursor.getString(cursor.getColumnIndex("seat")));
            buses.put("date",cursor.getString(cursor.getColumnIndex("date")));
            buses.put("uname",cursor.getString(cursor.getColumnIndex("uname")));
            viewBookings.add(buses);
        }
        return viewBookings;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getTotalCompanyBookings(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> viewBookings = new ArrayList<>();
        String query = "SELECT * FROM trans INNER JOIN buses ON trans.B_ID=buses.B_ID";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> buses = new HashMap<>();
            buses.put("id",cursor.getString(cursor.getColumnIndex("B_ID")));
            buses.put("source",cursor.getString(cursor.getColumnIndex("source")));
            buses.put("destination",cursor.getString(cursor.getColumnIndex("destination")));
            buses.put("price",cursor.getString(cursor.getColumnIndex("amount")));
            buses.put("class",cursor.getString(cursor.getColumnIndex("class")));
            buses.put("departTime",cursor.getString(cursor.getColumnIndex("departTime")));
            buses.put("arriveTime",cursor.getString(cursor.getColumnIndex("arriveTime")));
            buses.put("seat",cursor.getString(cursor.getColumnIndex("seat")));
            buses.put("date",cursor.getString(cursor.getColumnIndex("date")));
            buses.put("uname",cursor.getString(cursor.getColumnIndex("uname")));
            viewBookings.add(buses);
        }
        return viewBookings;
    }

    public Boolean insertSeat(String bid,String date,String seat_no){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",date);
        contentValues.put("B_ID",bid);
        contentValues.put("seat",seat_no);
        long result = myDB.insert("seats",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    public void insertTrans(String date,String bid,String seat,String amount,String uid,String un){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",date);
        contentValues.put("B_ID",bid);
        contentValues.put("seat",seat);
        contentValues.put("amount",amount);
        contentValues.put("U_ID",uid);
        contentValues.put("uname",un);
        myDB.insert("trans",null,contentValues);
    }

    public int checkSeat(String seat_no,String date,String bid){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM seats where seat LIKE '%"+seat_no+"%' and date=? and B_ID=?",new String[]{date,bid});
        if(cursor.getCount()>0){
            return cursor.getCount();
        }
        return 0;
    }

    public Boolean insertBus(int id,String source,String destination,String price,String bclass,String departTime,String arriveTime){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("U_ID",id);
        contentValues.put("source",source);
        contentValues.put("destination",destination);
        contentValues.put("price",price);
        contentValues.put("class",bclass);
        contentValues.put("departTime",departTime);
        contentValues.put("arriveTime",arriveTime);
        long result = myDB.insert("buses",null,contentValues);

        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean insertData(String username,String password,String mobile,String email,String urole){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("mobile",mobile);
        contentValues.put("email",email);
        contentValues.put("role",urole);
        long result = myDB.insert("users",null,contentValues);

        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getUserId(String username){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String[] column = {"U_ID"};
        String[] args = {username};
//        Cursor cursor = myDB.rawQuery("select * from users where username=?",new String[]{username});
        Cursor cursor = myDB.query("users",column,"username LIKE ?",args,null,null,null);
        return cursor;
    }

    public Cursor getBusId(int id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String[] column = {"B_ID"};
        String[] args = {String.valueOf(id)};
//        Cursor cursor = myDB.rawQuery("select * from users where username=?",new String[]{username});
        Cursor cursor = myDB.query("buses",column,"U_ID LIKE ?",args,null,null,null);
        return cursor;
    }

    public Cursor getSeatDate(String bid,String date){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String[] column = {"date"};
        String[] args = {date,bid};
        Cursor cursor = myDB.query("seats",column,"date LIKE ? and B_ID LIKE ?",args,null,null,null);
        return cursor;
    }

    public Cursor getPermission(int u_id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String[] column = {"permission"};
        String[] args = {Integer.toString(u_id)};
        Cursor cursor = myDB.query("permissions",column,"U_ID LIKE ?",args,null,null,null);
        return cursor;
    }

    public Cursor getUserRole(String username){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String[] column = {"role"};
        String[] args = {username};
        Cursor cursor = myDB.query("users",column,"username LIKE ?",args,null,null,null);
        return cursor;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username=?",new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkusernamePassword(String username,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username=? and password=?",new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public void insertPermission(int uid,int permission){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("U_ID",uid);
        contentValues.put("permission",permission);
        myDB.insert("permissions",null,contentValues);
    }

    public int getTotalUsers(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users",null);
        if(cursor.getCount()>0){
            return cursor.getCount();
        }
        return 0;
    }

    public int getTotalBooking(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from trans",null);
        if(cursor.getCount()>0){
            return cursor.getCount();
        }
        return 0;
    }

    public int getTotalCompanyRow(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM trans INNER JOIN buses ON trans.B_ID=buses.B_ID",null);
        if(cursor.getCount()>0){
            return cursor.getCount();
        }
        return 0;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getCompanyBuses(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> companyBuses = new ArrayList<>();
        String query = "SELECT * FROM buses WHERE U_ID=?";
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(id)});
        while (cursor.moveToNext()){
            HashMap<String,String> buses = new HashMap<>();
            buses.put("id",cursor.getString(cursor.getColumnIndex("B_ID")));
            buses.put("source",cursor.getString(cursor.getColumnIndex("source")));
            buses.put("destination",cursor.getString(cursor.getColumnIndex("destination")));
            buses.put("price",cursor.getString(cursor.getColumnIndex("price")));
            buses.put("class",cursor.getString(cursor.getColumnIndex("class")));
            buses.put("departTime",cursor.getString(cursor.getColumnIndex("departTime")));
            buses.put("arriveTime",cursor.getString(cursor.getColumnIndex("arriveTime")));
            companyBuses.add(buses);
        }
        return companyBuses;
    }

    public Cursor getBus(String bid){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String[] column = {"source","destination","price","class","departTime","arriveTime"};
        String[] args = {bid};
        Cursor cursor = myDB.query("buses",column,"B_ID LIKE ?",args,null,null,null);
        return cursor;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getTotalCompany(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> companyBuses = new ArrayList<>();
        String query = "SELECT * FROM buses INNER JOIN users ON buses.U_ID=users.U_ID";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> buses = new HashMap<>();
            buses.put("username",cursor.getString(cursor.getColumnIndex("username")));
            buses.put("id",cursor.getString(cursor.getColumnIndex("B_ID")));
            buses.put("source",cursor.getString(cursor.getColumnIndex("source")));
            buses.put("destination",cursor.getString(cursor.getColumnIndex("destination")));
            buses.put("price",cursor.getString(cursor.getColumnIndex("price")));
            buses.put("class",cursor.getString(cursor.getColumnIndex("class")));
            buses.put("departTime",cursor.getString(cursor.getColumnIndex("departTime")));
            buses.put("arriveTime",cursor.getString(cursor.getColumnIndex("arriveTime")));
            companyBuses.add(buses);
        }
        return companyBuses;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getUserBuses(String source,String destination){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userBuses = new ArrayList<>();
        String query = "SELECT * FROM buses INNER JOIN users ON buses.U_ID=users.U_ID where source=? and destination=?";
        Cursor cursor = db.rawQuery(query,new String[]{source,destination});
        while (cursor.moveToNext()){
            HashMap<String,String> buses = new HashMap<>();
            buses.put("username",cursor.getString(cursor.getColumnIndex("username")));
            buses.put("id",cursor.getString(cursor.getColumnIndex("B_ID")));
            buses.put("source",cursor.getString(cursor.getColumnIndex("source")));
            buses.put("destination",cursor.getString(cursor.getColumnIndex("destination")));
            buses.put("price",cursor.getString(cursor.getColumnIndex("price")));
            buses.put("class",cursor.getString(cursor.getColumnIndex("class")));
            buses.put("departTime",cursor.getString(cursor.getColumnIndex("departTime")));
            buses.put("arriveTime",cursor.getString(cursor.getColumnIndex("arriveTime")));
            userBuses.add(buses);
        }
        return userBuses;
    }

    public void updateBus(String id,String source,String destination,String price,String bclass,String departTime,String arriveTime){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sql = "UPDATE buses SET source = '"+source+"',destination = '"+destination+"',price = '"+price+"',class = '"+bclass+"',departTime = '"+departTime+"',arriveTime = '"+arriveTime+"' WHERE B_ID = "+id;
        myDB.execSQL(sql);
    }

    public int getCompanyTotal(int uid){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from buses where U_ID=?",new String[]{String.valueOf(uid)});
        if(cursor.getCount()>0){
            return cursor.getCount();
        }
        return 0;
    }

    public int getBusesTotal(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM buses INNER JOIN users ON buses.U_ID=users.U_ID",null);
        if(cursor.getCount()>0){
            return cursor.getCount();
        }
        return 0;
    }

    public int getNewUser(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from permissions where permission=?",new String[]{String.valueOf(0)});
        if(cursor.getCount()>0){
            return cursor.getCount();
        }
        return 0;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getNewUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM users INNER JOIN permissions ON users.U_ID=permissions.U_ID WHERE permissions.permission=?";
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(0)});
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex("U_ID")));
            user.put("username",cursor.getString(cursor.getColumnIndex("username")));
            user.put("mobile",cursor.getString(cursor.getColumnIndex("mobile")));
            user.put("email",cursor.getString(cursor.getColumnIndex("email")));
            user.put("role",cursor.getString(cursor.getColumnIndex("role")));
            userList.add(user);
        }
        return userList;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM users INNER JOIN permissions ON users.U_ID=permissions.U_ID";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex("U_ID")));
            user.put("username",cursor.getString(cursor.getColumnIndex("username")));
            user.put("mobile",cursor.getString(cursor.getColumnIndex("mobile")));
            user.put("email",cursor.getString(cursor.getColumnIndex("email")));
            user.put("role",cursor.getString(cursor.getColumnIndex("role")));
            userList.add(user);
        }
        return userList;
    }

    public void grantPermission(String userid){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String sql = "UPDATE permissions SET permission = 1 WHERE U_ID = "+userid;
        myDB.execSQL(sql);
    }
}


