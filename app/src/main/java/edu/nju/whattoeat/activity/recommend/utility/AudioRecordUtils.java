package edu.nju.whattoeat.activity.recommend.utility;

import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.TimeUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by WillLester on 2017/3/25.
 */

public class AudioRecordUtils {
    private String filePath;
    private String folderPath;
    private MediaRecorder recorder;
    public static final int MAX_LENGTH = 1000*60*5;
    private OnAudioStatusUpdateListener listener;
    private long start;
    private long end;

    private final Handler handler = new Handler();
    private final int BASE = 1;
    private final int SPACE = 100;
    private Runnable updateMicTimer = new Runnable() {
        @Override
        public void run() {
            updateMicStatus();
        }
    };

    public AudioRecordUtils(String folderPath){
        File file = new File(folderPath);
        if (!file.exists()){
            file.mkdir();
        }
        this.folderPath = folderPath;
    }

    public void startRecord(){
        if (recorder == null){
            recorder = new MediaRecorder();
        }
        try {
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            String time = simpleDateFormat.format(calendar.getTime());
            filePath = folderPath+time+".amr";

            recorder.setOutputFile(filePath);
            recorder.setMaxDuration(MAX_LENGTH);
            recorder.prepare();
            recorder.start();
            start = System.currentTimeMillis();
            updateMicStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long stopRecord(){
        if (recorder == null){
            return 0;
        }
        end = System.currentTimeMillis();
        recorder.stop();
        recorder.reset();
        recorder.release();
        recorder = null;
        listener.onStop(filePath);
        filePath = "";
        return end-start;
    }

    public void cancelRecord(){
        recorder.stop();
        recorder.reset();
        recorder.release();
        recorder = null;
        File file = new File(filePath);
        if (file.exists())
            file.delete();
        filePath = "";
    }

    private void updateMicStatus(){
        if (recorder != null) {
            double ratio = (double) recorder.getMaxAmplitude();
            double db = 0;
            if (ratio > 1) {
                db = 20 * Math.log10(ratio);
                if(null != recorder) {
                    listener.onUpdate(db,System.currentTimeMillis() - start);
                }
            }
            handler.postDelayed(updateMicTimer, SPACE);
        }
    }

    public interface OnAudioStatusUpdateListener {
        void onUpdate(double db, long time);

        void onStop(String filePath);
    }
}
