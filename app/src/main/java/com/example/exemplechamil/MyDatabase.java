package com.example.exemplechamil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME="car.db";
     public static final int DB_VERSION=7;
    public static final String CAR_TB_NAME="carres";
    public static final String CAR_CLN_ID="id";
    public static final String CAR_CLN_MODEL="model";
    public static final String CAR_CLN_COLOR="color";
    public static final String CAR_CLN_DESCRRIPRION="description";

    public static final String CAR_CLN_IMAGE="image";


    public static final String CAR_CLN_DPL="distanceperlettre";





    public MyDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //يتم استدعاءها عند انشاء الداتا بايز
        db.execSQL("CREATE TABLE "+CAR_TB_NAME+" ("+CAR_CLN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAR_CLN_MODEL+" TEXT,"+CAR_CLN_COLOR+" TEXT, "+CAR_CLN_DPL+" REAL, "+CAR_CLN_DESCRRIPRION+" TEXT, "+CAR_CLN_IMAGE+" TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //يتم استدعاءها عند كل تحديث للداتا بايز اي تغيير الفراسيو الا اعلا
        db.execSQL("DROP TABLE IF EXISTS "+CAR_TB_NAME+" ");
        onCreate(db);

    }
    /*
    //inserer les donné dans data base
    //retourne -1 if l'isertion fail
   /* public boolean insertCar(Car car){
        //moachir data base
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CAR_CLN_MODEL,car.getModel());
        values.put(CAR_CLN_COLOR,car.getColor());
        values.put(CAR_CLN_DPL,car.getDbl());
        values.put(CAR_CLN_IMAGE,car.getImage());
        values.put(CAR_CLN_DESCRRIPRION,car.getDsecription());

        long result=db.insert(CAR_TB_NAME,null,values);
        return result !=-1;



    }
    // ta3dil data base
    //retourne le nombre le ligne li t3adlo
    public boolean updateCar(Car car){
        //moachir data base
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CAR_CLN_MODEL,car.getModel());
        values.put(CAR_CLN_COLOR,car.getColor());
        values.put(CAR_CLN_DPL,car.getDbl());
        values.put(CAR_CLN_IMAGE,car.getImage());
        values.put(CAR_CLN_DESCRRIPRION,car.getDsecription());

        String args[]={car.getId()+""};
        int result=db.update(CAR_TB_NAME,values,"id=?",args);
        return result <0;
    }
    //retourne le nombre de ligne dans un table
    public long getCarsCount(){
        SQLiteDatabase db=getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,CAR_TB_NAME);
    }
    public boolean deleteCar(Car car){
        SQLiteDatabase db=getWritableDatabase();
        String args[]={String.valueOf(car.getId())};
        int result=db.delete(CAR_TB_NAME,"id=?",args);
        return result > 0;

    }
    //istirja3 lbayanat mina ljadwal
    public ArrayList<Car> getAllCar(){
        ArrayList<Car> cars=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        //treja3 object mn naw3 cursor
        //la valeur de cursor in first -1
        Cursor cursor=db.rawQuery("SELECT * FROM " +CAR_TB_NAME,null);
        if(cursor !=null && cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(CAR_CLN_ID));
                String model=cursor.getString(cursor.getColumnIndex(CAR_CLN_MODEL));
            String color=cursor.getString(cursor.getColumnIndex(CAR_CLN_COLOR));
                int dpl=cursor.getInt(cursor.getColumnIndex(CAR_CLN_DPL));
                String image=cursor.getString(cursor.getColumnIndex(CAR_CLN_IMAGE));
                String description=cursor.getString(cursor.getColumnIndex(CAR_CLN_DESCRRIPRION));

                Car c=new Car(id,model,color,dpl,image,description);
                cars.add(c);
            }
            while(cursor.moveToNext());
            cursor.close();
        }
        return cars;

    }
  //dalt lbahth
    public ArrayList<Car> getCars(String modelSearch){
        ArrayList<Car> cars=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        //treja3 object mn naw3 cursor
        //la valeur de cursor in first -1
        Cursor cursor=db.rawQuery("SELECT * FROM "+MyDatabase.CAR_TB_NAME+" WHERE "+MyDatabase.CAR_CLN_MODEL+" LIKE ?",new String[] {modelSearch+ "%"});
        if(cursor !=null && cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(CAR_CLN_ID));
                String model=cursor.getString(cursor.getColumnIndex(CAR_CLN_MODEL));
                String color=cursor.getString(cursor.getColumnIndex(CAR_CLN_COLOR));
                int dpl=cursor.getInt(cursor.getColumnIndex(CAR_CLN_DPL));
                String image=cursor.getString(cursor.getColumnIndex(CAR_CLN_IMAGE));
                String description=cursor.getString(cursor.getColumnIndex(CAR_CLN_DESCRRIPRION));

                Car c=new Car(id,model,color,dpl,image,description);
                cars.add(c);
            }
            while(cursor.moveToNext());
            cursor.close();
        }
        return cars;

    }*/

}
