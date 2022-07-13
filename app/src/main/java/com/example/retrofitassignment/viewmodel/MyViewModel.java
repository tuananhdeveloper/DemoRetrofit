package com.example.retrofitassignment.viewmodel;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.repository.MyRepository;

import java.util.List;

/**
 * Created by Nguyen Tuan Anh on 13/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class MyViewModel extends ViewModel {
    private MyRepository repository;
    public MyViewModel(MyRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<MarsProperty>> getMarsPropertyList(String key) {
        return repository.getMarsPropertyList(key);
    }

}
