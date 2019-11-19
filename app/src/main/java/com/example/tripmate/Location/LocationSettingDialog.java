package com.example.tripmate.Location;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tripmate.R;

public class LocationSettingDialog extends Dialog implements View.OnClickListener {
    private Button submitButton;
    private Button cancelButton;

    private RadioGroup radioGroup1;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private RadioButton radio4;
    private RadioButton radio5;
    private RadioButton radio6;

    private TextView outcomeText;
    private SeekBar seekBar;

    private int seekNumber =0;
    private int typeNumber =0;

    private Context context;

    private CustomDialogListener customDialogListener;

    interface CustomDialogListener{
        void onSubmitClicked(int typeNumber , int rad);
        void onCancelClicked();
    }

    public LocationSettingDialog(Context context){
        super(context);
        this.context = context;
    }

    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_setting_dialog);

        submitButton = (Button)findViewById(R.id.submitButton);
        cancelButton =(Button)findViewById(R.id.cancelButton);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        radio1 =(RadioButton)findViewById(R.id.radio1);
        radio2 =(RadioButton)findViewById(R.id.radio2);
        radio3 =(RadioButton)findViewById(R.id.radio3);
        radio4 =(RadioButton)findViewById(R.id.radio4);
        radio5 =(RadioButton)findViewById(R.id.radio5);
        radio6 =(RadioButton)findViewById(R.id.radio6);

        outcomeText =(TextView)findViewById(R.id.outcomeText);
        seekBar = (SeekBar)findViewById(R.id.seekBar);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio1:
                        typeNumber = 12;
                        break;
                    case R.id.radio2:
                        typeNumber = 39;
                        break;
                    case R.id.radio3:
                        typeNumber = 32;
                        break;
                    case R.id.radio4:
                        typeNumber = 38;
                        break;
                    case R.id.radio5:
                        typeNumber = 15;
                        break;
                    case R.id.radio6:
                        typeNumber = 14;
                        break;

                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekNumber = seekBar.getProgress();
                outcomeText.setText(new StringBuilder().append(seekNumber));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekNumber = seekBar.getProgress(); //식바의 움직임이 시작될 때 실행될 사항
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekNumber = seekBar.getProgress(); //식바의 움직임이 멈출다면 실행될 사항
            }
        });


        submitButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitButton: //확인 버튼을 눌렀을 때
                RadioButton selectedRdo = (RadioButton)findViewById(radioGroup1.getCheckedRadioButtonId());
                String value = selectedRdo.getText().toString();

                //인터페이스의 함수를 호출하여 변수에 저장된 값들을 Activity로 전달
                customDialogListener.onSubmitClicked(changer(value),seekNumber);
                dismiss();
                break;
            case R.id.cancelButton: //취소 버튼을 눌렀을 때
                cancel();
                break;
        }
    }

    public int changer(String value){
        if(value == "관광지"){
            typeNumber = 12;
            return typeNumber;
        }
        else if(value == "음식점"){
            typeNumber = 39;
            return typeNumber;
        }
        else if(value == "숙박"){
            typeNumber = 32;
            return typeNumber;
        }
        else if(value == "쇼핑"){
            typeNumber = 38;
            return typeNumber;
        }
        else if(value == "축제/공연/행사"){
            typeNumber = 15;
            return typeNumber;
        }
        else if(value == "문화시설"){
            typeNumber = 14;
            return typeNumber;
        }
        return 0;
    }

}

