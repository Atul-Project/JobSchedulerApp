package com.example.jobschedulerapp;

import android.Manifest;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.util.Log;

//extend with JobService
//implements to method onStartJob() and onStopJob()
public class MyJobClass extends JobService {
    //here is an error on JobService for this change your  minSdkVersion 15 in minSdkVersion 21
    private boolean cancelJob=false;
    private String MY_TAG="output";
    @Override
    public boolean onStartJob(JobParameters params) {
        //it works in MainThread so for asynchronus task return true....
        doWork(params);
        return true;
    }

    private void doWork(JobParameters params) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i=1;i<8;i++)
                {
                    Log.d(MY_TAG,"i="+i);
                    if(cancelJob)
                    {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                Log.d(MY_TAG,"job finished");

            }
        }).start();
        jobFinished(params,false);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(MY_TAG,"job cancel before completed");
        cancelJob=true;
        return true;
    }
}
