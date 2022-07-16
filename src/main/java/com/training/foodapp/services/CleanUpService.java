package com.training.foodapp.services;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.training.foodapp.services.util.Constants.PDF_DIR;

@Service
public class CleanUpService {

    public static boolean schedulerStarted = false;

    public void scheduleCleanPdfDir() {
        ScheduledExecutorService scheduler
                = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new CleanupTask(), 5, 30, TimeUnit.MINUTES);
        CleanUpService.schedulerStarted = true;
        System.out.println("Cleaning scheduled");
    }

    private static class CleanupTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Started cleaning at " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE));
            try {
                FileUtils.cleanDirectory(new File(PDF_DIR));
                System.out.println("Files erased");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
