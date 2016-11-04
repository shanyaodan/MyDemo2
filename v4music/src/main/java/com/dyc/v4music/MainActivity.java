package com.dyc.v4music;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMediaSession();
//        MediaControllerCompat controllerCompat = new MediaControllerCompat(this,mediaSession);
//        controllerCompat.registerCallback(mMediaControllerCallback);
    }
    // Receive callbacks from the MediaController. Here we update our state such as which queue
    // is being shown, the current title and description and the PlaybackState.


    public static class A extends MediaButtonReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            System.out.println("======================:has data");

        }
    }

    private final MediaControllerCompat.Callback mMediaControllerCallback =
            new MediaControllerCompat.Callback() {
                @Override
                public void onMetadataChanged(MediaMetadataCompat metadata) {
                    super.onMetadataChanged(metadata);


                }

                @Override
                public void onPlaybackStateChanged(@NonNull PlaybackStateCompat state) {
                    super.onPlaybackStateChanged(state);

                    if (state == null) {
                        return;
                    }
                    System.out.println("===========================:"+PlaybackStateCompat.toKeyCode(state.getActions()));
                }
            };


    /**
     * Initiate a MediaSession to allow the Android system to interact with the player
     */
    MediaSessionCompat mediaSession;
    private void initMediaSession() {
        mediaSession = new MediaSessionCompat(this, this.getClass().getSimpleName(), null, null);

        mediaSession.setCallback(new MediaSessionCompat.Callback() {

            @Override
            public void onPlay() {

            }

            @Override
            public void onSkipToQueueItem(long id) {

            }

            @Override
            public void onPause() {

            }

            @Override
            public void onSkipToNext() {

            }

            @Override
            public void onSkipToPrevious() {

            }

            @Override
            public void onStop() {

            }

            @Override
            public void onSeekTo(long pos) {

            }
        });
//        mediaSession.setSessionActivity(
//                PendingIntent.getActivity(
//                        this, 0,
//                        new Intent(this, Main2Activity.class)
//                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP),
//                        PendingIntent.FLAG_CANCEL_CURRENT));
//
//        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS
//                | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
//
//        PlaybackStateCompat.Builder state = new PlaybackStateCompat.Builder()
//                .setActions(PlaybackStateCompat.ACTION_PLAY
//                        | PlaybackStateCompat.ACTION_PLAY_PAUSE
//                        | PlaybackStateCompat.ACTION_SEEK_TO
//                        | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID
//                        | PlaybackStateCompat.ACTION_PAUSE
//                        | PlaybackStateCompat.ACTION_SKIP_TO_NEXT
//                        | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS)
//                .setState(PlaybackStateCompat.STATE_NONE, 0, 0f);
//
//        mediaSession.setPlaybackState(state.build());
//        mediaSession.setActive(true);
    }
}
