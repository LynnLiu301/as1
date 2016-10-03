package com.example.xizi_habittracker;

import java.util.Date;

/**
 * Created by yfan2 on 9/30/16.
 */

public class Completion {

    private Date date;

    //set up the new date
    public Completion(){
        this.date = new Date();
    }

    //function return date
    public Date getDate(){
        return date;
    }


}
