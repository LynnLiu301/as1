package com.example.xizi_habittracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yfan2 on 10/2/16.
 */
public class CustomerAdapter extends ArrayAdapter<Habit> {
    public CustomerAdapter(Context context, ArrayList<Habit> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int i, View v, ViewGroup parent) {
        Integer count = 0;
        Habit habit = getItem(i);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Date todayDate= new Date();
        Calendar today    = Calendar.getInstance();
        Calendar habitDay = Calendar.getInstance();
        today.setTime(todayDate);
        for (Completion completion: habit.getCompletionRecord()){
            habitDay.setTime(completion.getDate());
            if (today.get(Calendar.YEAR) == habitDay.get(Calendar.YEAR)){
                if (today.get(Calendar.DAY_OF_YEAR) == habitDay.get(Calendar.DAY_OF_YEAR)){
                    count = count + 1;
                }
            }
        }

        TextView myHabit = (TextView) v.findViewById(R.id.Main_textView);

        if ( count > 0) {
            myHabit.setBackgroundResource(R.color.greyred);
        }
        myHabit.setText(habit.toString());

        return v;


    }
}
