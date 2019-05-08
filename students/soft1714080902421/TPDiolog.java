package edu.hzuapps.androidlabs.soft1714080902421;


import android.app.TimePickerDialog;
import android.content.Context;

public class TPDiolog extends TimePickerDialog {
    public TPDiolog(Context context, OnTimeSetListener callBack, int hourOfDay,
                    int minute, boolean is24HourView) {
        super(context, callBack, hourOfDay, minute, is24HourView);
    }

    //重写该方法是为了避免调用两次onTimeSet

    @Override
    protected void onStop() {
        //super.onStop();
    }
}
