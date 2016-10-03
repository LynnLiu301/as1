package com.example.xizi_habittracker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xizi on 9/30/16.
 */
public class Habit {

    private String HabitName;
    private Date   CreateDate;
    private Integer TotalCount;
    private ArrayList<Integer> WeekPlan;
    private ArrayList<Completion> CompletionRecord;



    public Habit(String HabitName){

        this.HabitName = HabitName;
        this.CreateDate = new Date();
        this.WeekPlan  = new ArrayList<Integer>();
        this.CompletionRecord = new ArrayList<Completion>();

    }

    public String getHabitName(){
        return this.HabitName;
    }

    public Integer getTotalCount(){
        return CompletionRecord.size();
    }

    public ArrayList<Integer> getWeekPlan(){
        return WeekPlan;
    }


    public ArrayList<Completion> getCompletionRecord(){
        return CompletionRecord;
    }

    public void deleteTotalCount(){
        CompletionRecord.clear();
    }


    public void addCompletionRecord(){
        Completion completion = new Completion();
        CompletionRecord.add(completion);
    }

    public void setWeekPlan(ArrayList<Integer> plan){
        this.WeekPlan = plan;
    }

    @Override
    public String toString(){
        return this.HabitName;
    }

}
