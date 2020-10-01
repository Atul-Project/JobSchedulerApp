package com.example.jobschedulerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // firstly we get JobScheduler in our MainActivity
    private JobScheduler myJobScheduler;
    private String MY_TAG="output";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myJobScheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
    }

    public void startJob(View view) {
        //create ComponentName object it works like Intent
        ComponentName componentName=new ComponentName(this,MyJobClass.class);
        //create JoBInfo object which encapsulate our job and set condition
        JobInfo jobInfo=new JobInfo.Builder(123,componentName)
                .setMinimumLatency(1000)//for three second delay
                .build();

        int resultcode=myJobScheduler.schedule(jobInfo);
        if(resultcode==JobScheduler.RESULT_SUCCESS)
        {
            Log.d(MY_TAG,"Job scheduled");

        }
        else
        {
            Log.d(MY_TAG,"Job not scheduled");
        }

    }

    public void stopJob(View view) {
        myJobScheduler.cancel(123);
        Log.d(MY_TAG,"job canceld");
    }
}