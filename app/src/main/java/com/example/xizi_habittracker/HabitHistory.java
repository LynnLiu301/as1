package com.example.xizi_habittracker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HabitHistory extends ActionBarActivity {
    private Habit habit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_history);

        //get information from pervious activity
        Bundle bundle = getIntent().getExtras();

        //to check if we get the information from previous activity
        if(bundle!= null)
        {
            //get the bundle
            int i = bundle.getInt("HABIT_NAME");
            habit = AddNewHabit.habits.getHabits().get(i);
        }

        //show the habit name
        TextView habitName = (TextView) findViewById(R.id.textView6);
        habitName.setText(habit.getHabitName());

        //show the total count of the habit completion
        TextView habitCount = (TextView) findViewById(R.id.textView9);
        habitCount.setText(habit.getTotalCount().toString());



    }

    @Override
    protected void onStart() {
        super.onStart();

        //on lick listener for update the viewHistory activity
        Button deleteHabit = (Button) findViewById(R.id.button3);
        deleteHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddNewHabit.habits.removeHabit(habit);
                ViewRecord.adapter.notifyDataSetChanged();
                finish();

            }

        });;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_habit_history, menu);
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


    //button function for clear the completion habit and re-show the textview which new number
    public void resetCompletion(View v){
        habit.deleteTotalCount();
        TextView habitCount = (TextView) findViewById(R.id.textView9);
        habitCount.setText(habit.getTotalCount().toString());

    }



}
