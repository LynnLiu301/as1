package com.example.xizi_habittracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private CustomerAdapter adapter1;
    public static HabitList todayHabits = new HabitList();
    private static final String FILENAME = "file.sav";

    private Date weekday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromFile();

    }

    @Override
    protected void onStart() {
        super.onStart();


        todayHabits.getHabits().clear();

        //adapter1 is to set new controller notifyDataSetChanger
        adapter1 = new CustomerAdapter(this,todayHabits.getHabits());

        //
        weekday = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(weekday);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        ArrayList<Habit> all = new ArrayList<Habit>();

        all = AddNewHabit.habits.getHabits();


        for (Habit habit : all){
            if (habit.getWeekPlan().contains(dayOfWeek)){
                if ( !todayHabits.getHabits().contains(habit)) {
                    todayHabits.getHabits().add(habit);
                    adapter1.notifyDataSetChanged();

                }
            }
        }



        saveInFile();

        adapter1 = new CustomerAdapter(this,todayHabits.getHabits());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, TodayHabitDetail.class);
                intent.putExtra("HABIT_1", i);


                startActivity(intent);

            }
        });





    }

    private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(AddNewHabit.habits.getHabits(),writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            // throw new RuntimeException
            throw  new RuntimeException();
        } catch (IOException e) {
            // throw new RuntimeException
            throw new RuntimeException();
        }
    }

    private void loadFromFile() {
        ArrayList<Habit> oldhabits = null;
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sep 22nd

            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            oldhabits = gson.fromJson(in, listType);
            if (oldhabits == null)
            {
                oldhabits = new ArrayList<Habit>();
            }
            AddNewHabit.habits.setHabits(oldhabits);
        } catch (FileNotFoundException e) {
            // Throw new RuntimeException();
        } catch (IOException e) {
            // throw new RuntimeException
            throw new RuntimeException();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    //Here is the button function to turn to AddNewHabit activity
    public void addNewHabit(View view){
        Intent intent = new Intent(this, AddNewHabit.class);
        startActivity(intent);

    }

    //Here is the button function to turn to ViewRecord activity
    public void viewRecord(View view){
        Intent intent = new Intent(this, ViewRecord.class);
        startActivity(intent);
    }
}
