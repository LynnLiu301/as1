package com.example.xizi_habittracker;

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

public class TodayHabitDetail extends ActionBarActivity {
    private Habit habit = null;
    private ArrayAdapter<Date> adapterToday;
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
            //TODO here get the string stored in the string variable and do
            int i = bundle1.getInt("HABIT_1");
            //habit = AddNewHabit.habits.getHabits().get(i);
            habit = MainActivity.todayHabits.getHabits().get(i);
        }

        TextView habitName = (TextView) findViewById(R.id.textView10);
        habitName.setText(habit.getHabitName());

        /*Integer size = habit.getCompletionRecord().size();
        Date todaydate= new Date();
        Integer i = 0;
        while (size > i){
            if ( habit.getCompletionRecord().contains(todaydate)){
                count = count +1;
            }
            i = i+1;
        }
        //habitCount.setText(i.toString());
        habitCount.setText(habit.getCompletionRecord().get(i-1).toString());*/

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
