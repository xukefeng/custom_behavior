package com.app.behavior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void first(View view) {
        Intent intent = new Intent(this, UCBehaviorActivity.class);
        startActivity(intent);
    }

    public void second(View view) {
        Intent intent = new Intent(this, OppoBehaviorActivity.class);
        startActivity(intent);
    }

    public void third(View view) {
        Intent intent = new Intent(this, LaGouBehaviorActivity.class);
        startActivity(intent);
    }
}
