package com.example.rohit.kathaproject.helpers;

/**
 * Created by Rohit on 20-05-2017.
 */

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.RandomAccessFile;

public class AudioRecorder {

    private enum State {INITIALIZING, READY, RECORDING, ERROR, STOPPED};
    private byte[] audioBuffer = null;
    private AudioRecord audioRecorder = null;
    private State state;
    private static final int TIMER_INTERVAL = 120;
    private volatile Thread t = null;


    public AudioRecorder(Context c) {
        int sampleRate = 11025;
        int encoder = AudioFormat.ENCODING_PCM_16BIT;
        int nChannels = AudioFormat.CHANNEL_CONFIGURATION_MONO;
        int preTimeStamp = (int) System.currentTimeMillis();
        Context myApp = c.getApplicationContext();

        try {
            String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/KathaProject/new.wav";
            RandomAccessFile tempAudioFile = new RandomAccessFile(fileName, "rw");
            int framePeriod = sampleRate * TIMER_INTERVAL / 1000;
            short bSamples = 16;
            int bufferSize = framePeriod * 2 * bSamples * nChannels / 8;
            if (bufferSize < AudioRecord.getMinBufferSize(sampleRate, nChannels, encoder)) {
                bufferSize = AudioRecord.getMinBufferSize(sampleRate, nChannels, encoder);
                // Set frame period and timer interval accordingly
                framePeriod = bufferSize / ( 2 * bSamples * nChannels / 8 );
                Log.w(AudioRecorder.class.getName(), "Increasing buffer size to " + Integer.toString(bufferSize));
            }

            int source = MediaRecorder.AudioSource.MIC;
            audioRecorder = new AudioRecord(source, sampleRate, nChannels, encoder, bufferSize);
            audioBuffer = new byte[2048];
            audioRecorder.setRecordPositionUpdateListener(updateListener);
            audioRecorder.setPositionNotificationPeriod(framePeriod);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private AudioRecord.OnRecordPositionUpdateListener updateListener = new AudioRecord.OnRecordPositionUpdateListener()
    {
        @Override
        public void onMarkerReached(AudioRecord recorder) {

        }

        @Override
        public void onPeriodicNotification(AudioRecord recorder) {

        }
    };

    public void start() {
        if (state == State.INITIALIZING) {
            audioRecorder.startRecording();
            state = State.RECORDING;
            t = new Thread(){
                public void run()
                {
                    //Here You can read your Audio Buffers
                    audioRecorder.read(audioBuffer, 0, 2048);
                }
            };
            t.setPriority(Thread.MAX_PRIORITY);
            t.start();
        }
        else {
            Log.e(AudioRecorder.class.getName(), "start() called on illegal state");
            state = State.ERROR;
        }

    }

    public void stop() {
        if (state == State.RECORDING) {
            audioRecorder.stop();
            Thread t1 = t;
            t=null;
            t1.interrupt();
            int count = 0;
            state = State.STOPPED;
        }
        else {
            Log.e(AudioRecorder.class.getName(), "stop() called on illegal state");
            state = State.ERROR;
        }
    }

    public void release() {
        if (state == State.RECORDING) {
            stop();
        }
        if (audioRecorder != null) {
            audioRecorder.release();
        }
    }

    public void reset() {
        try {
            if (state != State.ERROR) {
                release();
            }
        }
        catch (Exception e) {
            Log.e(AudioRecorder.class.getName(), e.getMessage());
            state = State.ERROR;
        }
    }

    public State getState()
    {
        return state;
    }
}
