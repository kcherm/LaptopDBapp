package com.example.powerlaptoplist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;

public class laptoplistActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Intent intent = new Intent(laptoplistActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    };


    private class SortByName implements Comparator<Laptop>{

        private boolean isAscending;

        public SortByName(boolean isAscending){
            this.isAscending = isAscending;
        }

        @Override
        public int compare(Laptop laptop, Laptop t1) {
            if(isAscending)
                return laptop.getName().compareTo(t1.getName());
            else
                return t1.getName().compareTo(laptop.getName());

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_list);

        initListButton();

        LaptopDataSource ds = new LaptopDataSource(this);
        ArrayList<Laptop> laptops;
        try {
            ds.open();
            laptops = ds.getLaptops();
            ds.close();


            RecyclerView laptoplist = findViewById(R.id.rvLaptops);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            laptoplist.setLayoutManager(layoutManager);
            LaptopAdapter laptopAdapter = new LaptopAdapter(laptops);
            laptopAdapter.setOnClickListener(onClickListener);
            laptoplist.setAdapter(laptopAdapter);
        }catch (Exception e){
            Toast.makeText(this, "Error retrieving laptops", Toast.LENGTH_LONG).show();
        }
    }

    private void initListButton(){
        Button listButton = findViewById(R.id.buttonList);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(laptoplistActivity.this, laptoplistActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


}