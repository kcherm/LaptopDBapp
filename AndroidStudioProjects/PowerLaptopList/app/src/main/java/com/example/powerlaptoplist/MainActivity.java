package com.example.powerlaptoplist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Laptop currentLaptop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLaptop = new Laptop();

        initListButton();
        initSaveButton();
        initTextChangeEvents();

    }

    public void onResume(){
        super.onResume();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        if(position != -1){
            LaptopDataSource ds = new LaptopDataSource(this);
            try{
                ds.open();
                currentLaptop = ds.getLaptop(position+1);
                ds.close();
                EditText nameEdit = findViewById(R.id.editLaptop);
                nameEdit.setText(currentLaptop.getName());
                EditText winversionEdit = findViewById(R.id.editwinVersion);
                winversionEdit.setText(currentLaptop.getwinVersion());
                EditText makemodelEdit = findViewById(R.id.editmakeModel);
                makemodelEdit.setText(currentLaptop.getmakeModel());
            }catch (Exception e){
                Toast.makeText(this, "Something went wrong oh no", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initListButton(){
        Button listButton = findViewById(R.id.buttonList);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, laptoplistActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


    private void initTextChangeEvents(){
        EditText nameEdit = findViewById(R.id.editLaptop);
        nameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLaptop.setName(nameEdit.getText().toString());
                currentLaptop.setLaptopID(-1);
            }
        });

        EditText winversionEdit = findViewById(R.id.editwinVersion);
        winversionEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLaptop.setwinVersion(winversionEdit.getText().toString());
            }
        });

        EditText makemodelEdit = findViewById(R.id.editmakeModel);
        makemodelEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLaptop.setmakeModel(makemodelEdit.getText().toString());
            }
        });

        EditText processorEdit = findViewById(R.id.editProcessor);
        processorEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentLaptop.setProcessor(processorEdit.getText().toString());
            }
        });

    }

    private void initSaveButton(){
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                LaptopDataSource ds = new LaptopDataSource(MainActivity.this);
                try {
                    ds.open();
                    if(currentLaptop.getLaptopID() == -1){
                        wasSuccessful = ds.insertLaptop(currentLaptop);
                        if(wasSuccessful){
                            int newID = ds.getLastLaptopID();
                            currentLaptop.setLaptopID(newID);
                        }
                    }else{
                        wasSuccessful = ds.updateLaptop(currentLaptop);
                    }
                    ds.close();
                }catch (Exception e){
                    wasSuccessful = false;
                    Toast.makeText(MainActivity.this, "oh no", Toast.LENGTH_LONG).show();
                }
                if(wasSuccessful){
                    Toast.makeText(MainActivity.this, "something happened, don't know what :)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}