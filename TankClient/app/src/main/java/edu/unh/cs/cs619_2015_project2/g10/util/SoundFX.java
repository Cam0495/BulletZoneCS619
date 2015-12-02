package edu.unh.cs.cs619_2015_project2.g10.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import edu.unh.cs.cs619_2015_project2.g10.R;

/**
 * Created by BBryant12 on 12/1/15.
 *
 * Encapsulates the sound effects functionality
 *
 */
public class SoundFX {
    private MediaPlayer mediaPlayer;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public SoundFX( Context context ){
        mediaPlayer = MediaPlayer.create(context, R.raw.horn);
    }
    public void play(){
        mediaPlayer.start();
    }
}
