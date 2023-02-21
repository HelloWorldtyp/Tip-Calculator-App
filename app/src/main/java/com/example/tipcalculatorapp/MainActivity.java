package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int newTip;
    private int newMembers;
    private boolean newSplit;

    SeekBar seekbar;
    TextView seekLabel;
    EditText group;
    EditText Input;
    TextView Tip;
    TextView members;
    TextView Final;
    RadioButton inGroup;
    RadioButton noGroup;
    Button settingsButton;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = findViewById(R.id.seekBar);
        seekLabel = findViewById(R.id.SeekBarNumber);
        group = findViewById(R.id.GroupInput);
        Input = findViewById(R.id.InputPrice);
        inGroup = findViewById(R.id.yesGroup);
        noGroup = findViewById(R.id.noGroup);
        Final = findViewById(R.id.TotalCostDisplay);
        Tip = findViewById(R.id.TipPercentage);
        members = findViewById(R.id.totalMembers);
        settingsButton = findViewById(R.id.button);
        calculateButton = findViewById(R.id.calculateButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);

            }
        });


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekLabel.setText(i + "");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double input = Double.parseDouble(Input.getText().toString());
                double percentage = Double.parseDouble(seekLabel.getText().toString());
                double tip_amount = ((percentage/100) * input);
                double total = input + tip_amount;


                if(inGroup.isChecked()){
                    double group_members = Double.parseDouble(group.getText().toString());

                    double Members = (tip_amount/group_members);
                    total = (input + tip_amount)/group_members;
                    members.setText(String.format("%.3f", Members));
                    Tip.setText(String.format("%.2f", tip_amount));
                    Final.setText(String.format("%.2f", total));

                }else if(noGroup.isChecked()){
                    total = input + tip_amount;
                    Tip.setText(String.format("%.2f", tip_amount));
                    Final.setText(String.format("%.2f", total));

                }
            }
        });

    }

   private void updateTip(){
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        newTip = sp.getInt("new tip", 69);
        newMembers = sp.getInt("new members", 69);
        newSplit = sp.getBoolean("new split", true);
        seekbar.setProgress(newTip);
        seekLabel.setText(newTip + "");
        group.setText(newMembers + "");
//
        if(newSplit == true){
            inGroup.setChecked(true);
        }else {
            noGroup.setChecked(true);
        }
    }
//
    @Override
    public void onResume(){
        super.onResume();
        updateTip();
    }


}