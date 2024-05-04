package com.thekids1002.catchbank.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thekids1002.catchbank.DTO.Vietcombank;
import com.thekids1002.catchbank.R;

import java.util.ArrayList;

public class VCBAdapter extends RecyclerView.Adapter<VCBAdapter.ViewHolder> {
    private ArrayList<Vietcombank> vcbList;

    public VCBAdapter(ArrayList<Vietcombank> vcbList) {
        this.vcbList = vcbList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vcb, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vietcombank vcb = vcbList.get(position);
        holder.txtTransactionID.setText(String.valueOf(vcb.getReferenceNumber()));
        holder.txtTime.setText(vcb.getTransactionTime());
        holder.txtDepositAmount.setText(String.valueOf(vcb.getDepositAmount()));
        holder.txtContent.setText(vcb.getTransactionContent());
    }

    @Override
    public int getItemCount() {
        return vcbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTransactionID;
        TextView txtDepositAmount;
        TextView txtContent;
        TextView txtTime;
        // Các trường thông tin khác của Vietcombank

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTransactionID = itemView.findViewById(R.id.txtTransactionID);
            txtDepositAmount = itemView.findViewById(R.id.txtDepositAmount);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}