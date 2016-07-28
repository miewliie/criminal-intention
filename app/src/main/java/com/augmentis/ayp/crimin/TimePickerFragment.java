package com.augmentis.ayp.crimin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Apinya on 7/28/2016.
 */
public class TimePickerFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String EXTRA_TIME = "TimePickerFragment.EXTRA_TIME";

    public static TimePickerFragment newInstance (Date datetime){
        TimePickerFragment tf = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable("ARG_DATE", datetime);
        tf.setArguments(args);
        return tf;
    }

    TimePicker _timePicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        Date date = (Date) getArguments().getSerializable("ARG_DATE");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        View v =  LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
        _timePicker = (TimePicker) v.findViewById(R.id.time_picker_in_dialog);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            _timePicker.setHour(hour);
            _timePicker.setMinute(min);
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(v);
        builder.setTitle(R.string.time_picker_title);
        builder.setPositiveButton(android.R.string.ok, this);

        return builder.create();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

        int hour = _timePicker.getHour();
        int minute = _timePicker.getMinute();
//
//
//        Date date = new GregorianCalendar(hour, minute).getTime();
//        sendResult(Activity.RESULT_OK, date);
    }

    private void sendResult(int resultCode, Date date) {

        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
};

