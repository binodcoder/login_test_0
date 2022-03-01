package com.example.logindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String LOGIN_TABLE="LOGIN_TABLE";
    public static final String USER_ID="ID";
    public static final String USER_NAME="USERNAME";
    public static final String USER_PASSWORD="PASSWORD";
    public static final String REMEMBER="REMEMBER";


    SQLiteDatabase db;


    public DataBaseHelper(Context context){
        super(context, "login.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery="CREATE TABLE "+LOGIN_TABLE+"("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_NAME+" TEXT, "+USER_PASSWORD+" INTEGER, "+ REMEMBER +" BOOL);";
        db.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addOne(LoginModel loginModel){
        db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(USER_NAME, loginModel.getUsername());
        cv.put(USER_PASSWORD, loginModel.getPassword());
        cv.put(REMEMBER, loginModel.isRemember());
        long insert=db.insert(LOGIN_TABLE, null, cv);

        if(insert==-1){
            return true;
        }else{
            return false;
        }
    }
    public List<LoginModel> getEveryOne(){
        db=this.getReadableDatabase();
        String select_query="SELECT * FROM "+ LOGIN_TABLE;
        List<LoginModel> returnList=new ArrayList<>();
        Cursor cursor=db.rawQuery(select_query, null);

        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String username=cursor.getString(1);
                int password=cursor.getInt(2);
                boolean remember=cursor.getInt(3)==1?true:false;
                LoginModel newLogin=new LoginModel(id, username, password, remember);
                returnList.add(newLogin);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
