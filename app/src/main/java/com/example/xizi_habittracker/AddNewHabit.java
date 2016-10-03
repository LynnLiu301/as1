package com.example.xizi_habittracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddNewHabit extends ActionBarActivity {

    private EditText bodyText;
    private EditText hint;

    private CheckBox Mon;
    private CheckBox Tues;
    private CheckBox Wed;
    private CheckBox Thurs;
    private CheckBox Fri;
    private CheckBox Sat;
    private CheckBox Sun;

    public static HabitList habits = new HabitList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_habit);

        bodyText = (EditText) findViewById(R.id.addHabit);
        hint     = (EditText) findViewById(R.id.editText);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        //TextView currentdate = (TextView) findViewById(R.id.currentDate);

        //show the date
        long date = System.currentTimeMillis();
        SimpleDateFormat current = new SimpleDateFormat("yyyy-MMM-dd");
        String datetime = current.format(date);
        //currentdate.setText(datetime);
        hint.setHint(datetime);

        SimpleDateFormat startDate = new SimpleDateFormat("yyyy-MMM-dd");

        //get check box
        Mon  = (CheckBox) findViewById(R.id.checkBox);
        Tues = (CheckBox) findViewById(R.id.checkBox2);
        Wed  = (CheckBox) findViewById(R.id.checkBox3);
        Thurs= (CheckBox) findViewById(R.id.checkBox4);
        Fri  = (CheckBox) findViewById(R.id.checkBox5);
        Sat  = (CheckBox) findViewById(R.id.checkBox6);
        Sun  = (CheckBox) findViewById(R.id.checkBox7);


        //here is click button listner
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                Habit newHabit = new Habit(text);

                ArrayList<Integer> plan = new ArrayList<Integer>();
                if (Mon.isChecked()){
                    plan.add(2);
                }
                if (Tues.isChecked()){
                    plan.add(3);
                }
                if (Wed.isChecked()){
                    plan.add(4);
                }
                if (Thurs.isChecked()){
                    plan.add(5);
                }
                if (Fri.isChecked()){
                    plan.add(6);
                }
                if (Sat.isChecked()){
                    plan.add(7);
                }
                if (Sun.isChecked()){
                    plan.add(1);
                }
                newHabit.setWeekPlan(plan);

                habits.addHabit(newHabit);

                finish();

        }


        });;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_habit, menu);
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


}
