package com.example.xizi_habittracker;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by yfan2 on 9/30/16.
 */
public class HabitList {
    private ArrayList<Habit> Habits;

    public HabitList(){
        this.Habits = new ArrayList<Habit>();

    }

    public ArrayList<Habit> getHabits(){

        return this.Habits;
    }

    public void setHabits(ArrayList<Habit> habits){

        this.Habits = habits;


    }

    public void addHabit(Habit habitName){
        this.Habits.add(habitName);
    }

    public void removeHabit(Habit habitName){
        this.Habits.remove(habitName);
    }
}
