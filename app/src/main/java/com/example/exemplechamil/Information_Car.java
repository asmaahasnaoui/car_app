package com.example.exemplechamil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Information_Car extends AppCompatActivity {
    private static final int PICK_IMG_REQ_CODE = 1;
    public static final int ADD_CAR_RES_CODE = 2;
    public static final int EDIT_CAR_RES_CODE = 3;
    private Toolbar toolbar;
    private TextInputEditText et_color,et_dpl,et_description,et_model;
    private ImageView img;
    private int carId=-1;
     private DatabaseAccess dba;
     private Uri imgUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information__car);
        toolbar=findViewById(R.id.tb);
        setSupportActionBar(toolbar);
        img=findViewById(R.id.img_car);
        et_color=findViewById(R.id.et_color);
        et_model=findViewById(R.id.et_model);
        et_description=findViewById(R.id.et_description);
        et_dpl=findViewById(R.id.et_dpl);
        dba=DatabaseAccess.getInstance(this);

        Intent intent=getIntent();
       carId= intent.getIntExtra(MainActivity.CAR_KEY,-1);
       if(carId==-1){
           //3amaliyat idafa
           Enablefield();
           clearfild();

       }
       else {
           //3amaliyat Ta3dil
           disablefield();
           dba.open();
          Car c= dba.getCar(carId);
           dba.close();
           if(c!=null){
               fillcartofields(c);

           }

       }
       img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(in,PICK_IMG_REQ_CODE);
           }
       });



    }
    private  void fillcartofields(Car c){
        if(c.getImage()!=null && !c.getImage().equals(""))
              img.setImageURI(Uri.parse(c.getImage()));
         et_model.setText(c.getModel());
        et_color.setText(c.getColor());
        et_description.setText(c.getDsecription());
        et_dpl.setText(c.getDbl()+"");

    }
    private void disablefield(){
        img.setEnabled(false);
        et_model.setEnabled(false);
        et_color.setEnabled(false);
        et_description.setEnabled(false);
        et_dpl.setEnabled(false);

    }
    private void Enablefield(){
        img.setEnabled(true);
        et_model.setEnabled(true);
        et_color.setEnabled(true);
        et_description.setEnabled(true);
        et_dpl.setEnabled(true);
    }
    private void clearfild(){
        img.setImageURI(null);
        et_model.setText("");
        et_color.setText("");
        et_description.setText("");
        et_dpl.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        MenuItem save=menu.findItem(R.id.save);
        MenuItem delete=menu.findItem(R.id.delet);
        MenuItem edit=menu.findItem(R.id.edit);
        if(carId==-1){
            //3amaliyat idafa
            save.setVisible(true);
            delete.setVisible(false);
            edit.setVisible(false);
        }
        else {
            //3amaliyat Ta3dil
            save.setVisible(false);
            edit.setVisible(true);
            delete.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String model,color,desc,image="";
        int dpl;
        dba.open();
        switch (item.getItemId()){
            case R.id.save:
                model=et_model.getText().toString();
                color=et_color.getText().toString();
                desc=et_description.getText().toString();
                if(imgUri!=null)
                image=imgUri.toString();
                dpl=Integer.parseInt(et_dpl.getText().toString());
                boolean res;
                Car c=new Car(carId,model,color,dpl,image,desc);


                if(carId==-1){
                    res= dba.insertCar(c);
                    if(res){
                        Toast.makeText(this,"car added succefully",Toast.LENGTH_LONG).show();
                        setResult(ADD_CAR_RES_CODE,null);
                        finish();
                    }
                }
                else {
                    res=dba.updateCar(c);
                    if(res){
                        Toast.makeText(this,"car modify succefully",Toast.LENGTH_LONG).show();
                        setResult(EDIT_CAR_RES_CODE,null);
                        finish();
                    }


                }



                return true;
            case R.id.delet:
                model=et_model.getText().toString();
                color=et_color.getText().toString();
                desc=et_description.getText().toString();
                dpl=Integer.parseInt(et_dpl.getText().toString());


                c=new Car(carId,model,color,dpl,null,desc);



                    res= dba.deleteCar(c);
                   if(res){
                   Toast.makeText(this,"car delete succesfully",Toast.LENGTH_LONG).show();
                   setResult(EDIT_CAR_RES_CODE,null);
                   finish();}

                    return true;
            case R.id.edit:
                Enablefield();
                MenuItem save=toolbar.getMenu().findItem(R.id.save);
                MenuItem delete=toolbar.getMenu().findItem(R.id.delet);
                MenuItem edit=toolbar.getMenu().findItem(R.id.edit);
                delete.setVisible(false);
                edit.setVisible(false);
                save.setVisible(true);

                return true;



        }
        dba.close();
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMG_REQ_CODE && resultCode==RESULT_OK){
            if(data!=null){
                imgUri=data.getData();
                img.setImageURI(imgUri);
            }

        }
    }
}
