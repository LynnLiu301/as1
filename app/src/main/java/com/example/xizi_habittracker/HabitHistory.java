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
    //private ArrayAdapter<Completion> adapter_history;
    //private Adapter adapter_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_history);
        //Habit habit = null;

        Bundle bundle = getIntent().getExtras();

        if(bundle!= null)
        {
            //TODO here get the string stored in the string variable and do
            int i = bundle.getInt("HABIT_NAME");
            habit = AddNewHabit.habits.getHabits().get(i);
        }

        TextView habitName = (TextView) findViewById(R.id.textView6);
        habitName.setText(habit.getHabitName());

        TextView habitCount = (TextView) findViewById(R.id.textView9);
        habitCount.setText(habit.getTotalCount().toString());

        //here is the new try
        /*
        Button resetCompletion = (Button) findViewById(R.id.button4);
        resetCompletion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                habit.deleteTotalCount();
                adapter_history.notify();
                //saveInFile();

            }
        });*/

        /*Button deleteHabit = (Button) findViewById(R.id.button3);
        deleteHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddNewHabit.habits.removeHabit(habit);


                finish();


            }


        });;*/

    }

    @Override
    protected void onStart() {
        super.onStart();

        /*ArrayList<Completion> completion = new ArrayList<>();
        completion = habit.getCompletionRecord();
        //adapter_history = new ArrayAdapter<Completion>(this, R.layout.list_item, completion);
        adapter_history = new Adapter() {

        }
        completion.setAdapter(adapter_history);*/

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

    public void resetCompletion(View v){
        habit.deleteTotalCount();
        TextView habitCount = (TextView) findViewById(R.id.textView9);
        habitCount.setText(habit.getTotalCount().toString());

    }



}
