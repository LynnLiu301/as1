package com.example.xizi_habittracker;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TodayHabitDetail extends Activity {
    private Habit habit = null;
    private Bundle bundle1;
    private Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_habit_detail);
    }

    @Override
    public void onStart(){
        super.onStart();

        bundle1 = getIntent().getExtras();

        if(bundle1!= null)
        {
            //get the information from previous activity
            int i = bundle1.getInt("HABIT_1");
            habit = MainActivity.todayHabits.getHabits().get(i);
        }

        TextView habitName = (TextView) findViewById(R.id.textView10);
        habitName.setText(habit.getHabitName());


        //get today's completion count
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

        //show the today's compltion count
        TextView todayHabitCount = (TextView) findViewById(R.id.textView13);
        todayHabitCount.setText(count.toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_today_habit_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //here is the completion function which can add one completion record
    public void addCompletionOnce(View view){
        habit.addCompletionRecord();

        count = 0;

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

       TextView todayHabitCount = (TextView) findViewById(R.id.textView13);
       todayHabitCount.setText(count.toString());
    }
}
