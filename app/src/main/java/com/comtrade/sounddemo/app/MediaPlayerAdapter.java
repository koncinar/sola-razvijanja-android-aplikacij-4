package com.comtrade.sounddemo.app;

import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * <p>The adapter for handling the list of sounds.</p>
 * <p>Created by <a href="mailto:rok.koncina@comtrade.com">Rok Koncina</a> on 3.10.2014.</p>
 */
class MediaPlayerAdapter extends BaseAdapter implements View.OnClickListener, MediaPlayer.OnCompletionListener {
    private Context context;

    /**
     * All the sounds in the list.
     */
    private List<Sound> sounds;

    /**
     * The media player that currently plays the sound. It's non-null only when sound is playing.
     */
    private MediaPlayer mediaPlayer;

    /**
     * This field holds the current playing sounds list item (line) so that we can enable or disable buttons etc.
     */
    private View currentlyPlayingRoot;

    public MediaPlayerAdapter(Context context, List<Sound> sounds) {
        this.context = context;
        this.sounds = sounds;
    }

    @Override
    public int getCount() {
        return sounds.size();
    }

    @Override
    public Object getItem(int position) {
        return sounds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Sound) getItem(position)).getResourceId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // if there is no previous view, create (inflate) a new one
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_sound, parent, false);
            assert convertView != null;

            // add the listeners on the buttons - this must only be done when inflating them
            convertView.findViewById(R.id.button_play).setOnClickListener(this);
            convertView.findViewById(R.id.button_stop).setOnClickListener(this);
        }

        // find the sound of the current line
        Sound sound = (Sound) getItem(position);

        // add the sound to the buttons so they'll have an easy access to it
        convertView.findViewById(R.id.button_play).setTag(sound);
        convertView.findViewById(R.id.button_stop).setEnabled(false);

        // display the name of the sound
        TextView soundNameView = (TextView) convertView.findViewById(R.id.sound_name);
        soundNameView.setText(sound.getName());

        return convertView;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onClick(View v) {
        // this is the sound this button is linked to
        Sound sound = (Sound) v.getTag();

        if (v.getId() == R.id.button_play) {
            // if sound is already playing, don't play another one
            if (mediaPlayer != null) {
                return;
            }

            // start playing the sound
            mediaPlayer = MediaPlayer.create(context, sound.getResourceId());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this);

            // disable play button, enable stop button, mark sound name bold
            markPlaying((View) v.getParent(), true);
        } else if (v.getId() == R.id.button_stop) {
            // can't stop if it's not playing
            if (mediaPlayer == null) {
                return;
            }

            // stop playing the sound
            mediaPlayer.stop();
            onCompletion(mediaPlayer);
        }
    }

    private void markPlaying(View root, boolean playing) {
        // if playing starts remember the root
        if (playing) {
            currentlyPlayingRoot = root;
        }

        // enable/disable buttons
        currentlyPlayingRoot.findViewById(R.id.button_stop).setEnabled(playing);
        currentlyPlayingRoot.findViewById(R.id.button_play).setEnabled(!playing);

        // mark the playing sounds text bold
        ((TextView) currentlyPlayingRoot.findViewById(R.id.sound_name)).setTypeface(null, playing ? Typeface.BOLD : Typeface.NORMAL);

        // if playing stops, remove the remembered root
        if (!playing) {
            currentlyPlayingRoot = null;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // when media playing stopped we need to release the resources so that we free up the memory
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;

        // disable stop button, enable play button, mark sound name normal (remove bold)
        markPlaying(null, false);
    }
}
