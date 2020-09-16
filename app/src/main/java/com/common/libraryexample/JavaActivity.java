package com.common.libraryexample;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sibin.commonlib.JavaViewHelper;

public class JavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.test_image);
        JavaViewHelper.loadImageFromUrl(imageView, "https://picsum.photos/200/300",
                R.drawable.default_placeholder,
                R.drawable.default_placeholder,
                null,
                null,
                null,
                null,
                null, (value) -> {
                    Log.d("Is Image loaded", "" + value);
                    return null;
                });

    }

}
