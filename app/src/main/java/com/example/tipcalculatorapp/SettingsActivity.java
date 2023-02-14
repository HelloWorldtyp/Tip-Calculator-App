package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {


    private int intTip;
    private int intMembers;
    private boolean split;

    RadioButton splitButton;
    RadioButton noSplitButton;
    EditText tip;
    EditText members;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        splitButton = findViewById(R.id.splitButton);
        noSplitButton = findViewById(R.id.noSplitButton);
        tip = findViewById(R.id.tipNumber);
        members = findViewById(R.id.members);


        intTip = Integer.parseInt(tip.getText().toString());
        intMembers = Integer.parseInt(members.getText().toString());

        if(splitButton.isChecked()){
          split = true;

        }else if(noSplitButton.isChecked()){
           split = false;

        }


    }

    @Override
    public void onPause(){
        super.onPause();
        updateSharedPreferences();
    }

    private void updateSharedPreferences(){
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("new tip", intTip);
        editor.putInt("new members", intMembers);
        editor.putBoolean("new split", split);
        editor.commit();
    }

}

