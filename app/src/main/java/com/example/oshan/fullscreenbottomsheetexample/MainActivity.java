package com.example.oshan.fullscreenbottomsheetexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.oshanazbow.fullscreenbottomsheetexample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FullBottomSheetDialogFragment().show(getSupportFragmentManager(), "search_dialog");
            }
        });


    }
}
