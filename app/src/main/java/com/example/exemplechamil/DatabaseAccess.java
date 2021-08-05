package com.example.exemplechamil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;
    private  static DatabaseAccess instance;
    private DatabaseAccess(Context context){
        this.openHelper= new MyDatabase
                (context);
    }
    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }
    public void open(){
        this.database=this.openHelper.getWritableDatabase();
    }
    public void close(){
        if (this.database!=null){
            this.database.close();
        }
    }
    public boolean insertCar(Car car){
        //moachir data base

        ContentValues values=new ContentValues();
        values.put(MyDatabase.CAR_CLN_MODEL,car.getModel());
        values.put(MyDatabase.CAR_CLN_COLOR,car.getColor());
        values.put(MyDatabase.CAR_CLN_DPL,car.getDbl());
        values.put(MyDatabase.CAR_CLN_IMAGE,car.getImage());
        values.put(MyDatabase.CAR_CLN_DESCRRIPRION,car.getDsecription());

        long result=database.insert(MyDatabase.CAR_TB_NAME,null,values);
        return result !=-1;



    }
    // ta3dil data base
    //retourne le nombre le ligne li t3adlo
    public boolean updateCar(Car car){
        //moachir data base

        ContentValues values=new ContentValues();
        values.put(MyDatabase.CAR_CLN_MODEL,car.getModel());
        values.put(MyDatabase.CAR_CLN_COLOR,car.getColor());
        values.put(MyDatabase.CAR_CLN_DPL,car.getDbl());
        values.put(MyDatabase.CAR_CLN_IMAGE,car.getImage());
        values.put(MyDatabase.CAR_CLN_DESCRRIPRION,car.getDsecription());

        String args[]={car.getId()+""};
        int result=database.update(MyDatabase.CAR_TB_NAME,values,"id=?",args);
        return result >0;
    }
    //retourne le nombre de ligne dans un table
    public long getCarsCount(){

        return DatabaseUtils.queryNumEntries(database,MyDatabase.CAR_TB_NAME);
    }
    public boolean deleteCar(Car car){

        String args[]={String.valueOf(car.getId())};
        int result=database.delete(MyDatabase.CAR_TB_NAME,"id=?",args);
        return result > 0;

    }
    //istirja3 lbayanat mina ljadwal
    public ArrayList<Car> getAllCar(){
        ArrayList<Car> cars=new ArrayList<>();

        //treja3 object mn naw3 cursor
        //la valeur de cursor in first -1
        Cursor cursor=database.rawQuery("SELECT * FROM " +MyDatabase.CAR_TB_NAME,null);
        if(cursor !=null && cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(MyDatabase.CAR_CLN_ID));
                String model=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_MODEL));
                String color=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_COLOR));
                int dpl=cursor.getInt(cursor.getColumnIndex(MyDatabase.CAR_CLN_DPL));
                String image=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_IMAGE));
                String description=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_DESCRRIPRION));

                Car c=new Car(id,model,color,dpl,image,description);
                cars.add(c);
            }
            while(cursor.moveToNext());
            cursor.close();
        }
        return cars;

    }
    public Car getCar(int carId){


        //treja3 object mn naw3 cursor
        //la valeur de cursor in first -1
        Cursor cursor=database.rawQuery("SELECT * FROM " +MyDatabase.CAR_TB_NAME+" WHERE "+MyDatabase.CAR_CLN_ID+"=?",new String[]{String.valueOf(carId)});
        if(cursor !=null && cursor.moveToFirst()){

                int id=cursor.getInt(cursor.getColumnIndex(MyDatabase.CAR_CLN_ID));
                String model=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_MODEL));
                String color=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_COLOR));
                int dpl=cursor.getInt(cursor.getColumnIndex(MyDatabase.CAR_CLN_DPL));
                String image=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_IMAGE));
                String description=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_DESCRRIPRION));

                Car c=new Car(id,model,color,dpl,image,description);



            cursor.close();
            return c;
        }
        return null;

    }
    //dalt lbahth
    public ArrayList<Car> getCars(String modelSearch){
        ArrayList<Car> cars=new ArrayList<>();

        //treja3 object mn naw3 cursor
        //la valeur de cursor in first -1
        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.CAR_TB_NAME+" WHERE "+MyDatabase.CAR_CLN_MODEL+" LIKE ?",new String[] {modelSearch+ "%"});
        if(cursor !=null && cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(MyDatabase.CAR_CLN_ID));
                String model=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_MODEL));
                String color=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_COLOR));
                int dpl=cursor.getInt(cursor.getColumnIndex(MyDatabase.CAR_CLN_DPL));
                String image=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_IMAGE));
                String description=cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_DESCRRIPRION));

                Car c=new Car(id,model,color,dpl,image,description);
                cars.add(c);
            }
            while(cursor.moveToNext());
            cursor.close();
        }
        return cars;

    }

}


