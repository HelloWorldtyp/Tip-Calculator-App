package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekbar;
    TextView seekLabel;
    EditText group;
    EditText Input;
    TextView Tip;
    TextView members;
    TextView Final;
    RadioGroup radio;
    RadioButton inGroup;
    RadioButton noGroup;

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


        group.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE){
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
                return false;
            }
        });

        Input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE){
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
                return false;
            }
        });




    }

}