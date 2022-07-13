package com.example.retrofitassignment.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.retrofitassignment.databinding.ItemLayoutBinding;
import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.repository.MyRepository;
import com.example.retrofitassignment.viewmodel.MyViewModel;

import java.util.List;

/**
 * Created by Nguyen Tuan Anh on 13/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<MarsProperty> marsPropertyList;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(OnItemClickListener onItemClickListener, List<MarsProperty> marsPropertyList) {
        this.marsPropertyList = marsPropertyList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding itemBinding = ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setModel(marsPropertyList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if(marsPropertyList != null) return marsPropertyList.size();
        return 0;
    }

    public void setMarsPropertyList(List<MarsProperty> marsPropertyList) {
        this.marsPropertyList = marsPropertyList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemLayoutBinding binding;
        public MyViewHolder(@NonNull ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getBindingAdapterPosition(), false);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, boolean isLongClick);
    }
}
