package com.comtrade.sounddemo.app;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>The adapter for handling the list of sounds.</p>
 * <p>Created by <a href="mailto:rok.koncina@comtrade.com">Rok Koncina</a> on 3.10.2014.</p>
 */
class SoundPoolAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;

    /**
     * All the sounds in the list.
     */
    private List<Sound> sounds;

    /**
     * The pool of all the sounds that we'll be playing
     */
    private SoundPool soundPool;

    private List<Integer> soundIds;

    public SoundPoolAdapter(Context context, List<Sound> sounds) {
        this.context = context;
        this.sounds = sounds;
        this.soundIds = new ArrayList<>(sounds.size());

        // init the SoundPool - allow playing maximum of 10 music streams at once
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        for (Sound sound : sounds) {
            // load the sound into the pool
            int soundId = soundPool.load(context, sound.getResourceId(), 1);

            // save the sound id for future reference
            soundIds.add(soundId);
        }
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
        convertView.findViewById(R.id.button_stop).setVisibility(View.GONE);

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

        // get the id of the sound to play/stop
        int soundId = soundIds.get(sounds.indexOf(sound));

        if (v.getId() == R.id.button_play) {
            // start playing the sound
            soundPool.play(soundId, 1, 1, 1, 0, 1);
        }
    }
}
