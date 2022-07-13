package com.example.retrofitassignment.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

/**
 * Created by Nguyen Tuan Anh on 13/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class MyBindingUtils {
    @BindingAdapter("bind:imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            Picasso.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        }
    }
}
