package com.example.retrofitassignment.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.ActivityMainBinding;
import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.repository.MyRepository;
import com.example.retrofitassignment.view.adapter.MyAdapter;
import com.example.retrofitassignment.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener{
    private final List<MarsProperty> propertyList = new ArrayList<>();
    private static final String BUY_FILTER = "buy";
    private static final String RENT_FILTER = "rent";
    private static final String DEFAULT_FILTER = BUY_FILTER;
    private static final String TAG = "MainActivity.class";

    private MyViewModel viewModel;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MyRepository myRepository = MyRepository.getInstance();
        viewModel = new MyViewModel(myRepository);

        mainBinding.setViewModel(viewModel);
        mainBinding.executePendingBindings();

        myAdapter = new MyAdapter(this, propertyList);
        mainBinding.recyclerView.setAdapter(myAdapter);
        mainBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        viewModel.getMarsPropertyList(DEFAULT_FILTER).observe(this, marsProperties -> {
            Toast.makeText(this, "" + marsProperties.get(0).imgSrcUrl, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "size: " + marsProperties.size());
            myAdapter.setMarsPropertyList(marsProperties);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_rent:
                loadRentItems();
                break;
            case R.id.menu_item_buy:
                loadBuyItems();
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(View view, int position, boolean isLongClick) {

    }

    private void loadRentItems() {
        viewModel.getMarsPropertyList(RENT_FILTER).observe(this, myAdapter::setMarsPropertyList);
    }

    private void  loadBuyItems() {
        viewModel.getMarsPropertyList(BUY_FILTER).observe(this, myAdapter::setMarsPropertyList);
    }
}