package com.thekids1002.catchbank.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.thekids1002.catchbank.Adapter.VCBAdapter;
import com.thekids1002.catchbank.DTO.Vietcombank;
import com.thekids1002.catchbank.Database.TransactionVCBHelper;
import com.thekids1002.catchbank.R;

import java.util.ArrayList;

public class VCBFragment extends Fragment {
    private RecyclerView recyclerView;
    private VCBAdapter vcbAdapter;
    private ArrayList<Vietcombank> vcbList;

    public TransactionVCBHelper helper;
    public VCBFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo RecyclerView và Adapter

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_v_c_b, container, false);

        helper = new TransactionVCBHelper(view.getContext());

        recyclerView = view.findViewById(R.id.VCBRecyclerView);

        vcbList = helper.getAllVCBData();

        vcbAdapter = new VCBAdapter(vcbList);

        // Thiết lập LayoutManager và Adapter cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(vcbAdapter);

        return view;
    }
}