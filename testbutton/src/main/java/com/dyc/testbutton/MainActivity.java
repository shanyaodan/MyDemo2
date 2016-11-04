package com.dyc.testbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.testtext)).setMovementMethod(new ScrollingMovementMethod());
//     Spinner   spinner = (Spinner) findViewById(R.id.widgets_spinner_underlined);
//        spinner.setAdapter(new ArrayAdapter<>(this,
//                R.layout.support_simple_spinner_dropdown_item, Cheeses.sCheeseStrings));
    }
}
