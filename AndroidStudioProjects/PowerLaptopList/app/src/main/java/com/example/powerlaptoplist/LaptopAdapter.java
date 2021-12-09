package com.example.powerlaptoplist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LaptopAdapter extends RecyclerView.Adapter {

    private ArrayList<Laptop> laptopData;
    private View.OnClickListener onClickListener;

    public class LaptopViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewLaptop;
        public TextView textViewmakeModel;

        public LaptopViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLaptop = itemView.findViewById(R.id.textViewName);
            textViewmakeModel = itemView.findViewById(R.id.textViewmakeModel);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        public TextView getLaptopTextView(){
            return textViewLaptop;
        }
        public TextView getmakeModelTextView() {return  textViewmakeModel; }

    }

    public void setOnClickListener(View.OnClickListener listener){
        onClickListener = listener;
    }

    public LaptopAdapter(ArrayList<Laptop> arrayList){
        laptopData = arrayList;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view, parent, false);
        return new LaptopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LaptopViewHolder cvh = (LaptopViewHolder) holder;
        cvh.getLaptopTextView().setText(laptopData.get(position).getName());
        cvh.getmakeModelTextView().setText(laptopData.get(position).getmakeModel());
    }

    @Override
    public int getItemCount() {
        return laptopData.size();
    }
}
