package com.example.retrofitpractice2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitpractice2.model.Contact;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>{

    private ArrayList<Contact> arrayList;

    public DataAdapter(ArrayList<Contact> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_preview,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_email.setText(arrayList.get(position).getEmail());
        holder.tv_address.setText(arrayList.get(position).getAddress());
        holder.tv_gender.setText(arrayList.get(position).getGender());
        holder.tv_home.setText(arrayList.get(position).getPhone().getHome());
        holder.tv_mobile.setText(arrayList.get(position).getPhone().getMobile());
        holder.tv_office.setText(arrayList.get(position).getPhone().getOffice());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name, tv_email, tv_address, tv_mobile, tv_gender, tv_home, tv_office;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_mobile = itemView.findViewById(R.id.tv_mobile);
            tv_gender = itemView.findViewById(R.id.tv_gender);
            tv_home = itemView.findViewById(R.id.tv_home);
            tv_office = itemView.findViewById(R.id.tv_office);
        }
    }
}
