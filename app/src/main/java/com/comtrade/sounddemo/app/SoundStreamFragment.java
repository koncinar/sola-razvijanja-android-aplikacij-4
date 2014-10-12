package com.comtrade.sounddemo.app;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * <p>A fragment where you can enter and URL of a sound and play it.</p>
 * <p>Created by <a href="mailto:rok.koncina@comtrade.com">Rok Koncina</a> on 12.10.2014.</p>
 */

public class SoundStreamFragment extends Fragment implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    private EditText soundUrlEditText;

    private MediaPlayer mediaPlayer;


    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stream, container, false);

        // find sound URL input field for future reference
        soundUrlEditText = (EditText) rootView.findViewById(R.id.sound_url);

        // add the click listener to the button
        Button playButton = (Button) rootView.findViewById(R.id.btn_play);
        playButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Editable soundUrlText = soundUrlEditText.getText();
        if (TextUtils.isEmpty(soundUrlText)) {
            Toast.makeText(getActivity(), R.string.msg_empty_url, Toast.LENGTH_LONG).show();
            return;
        }

        String url = soundUrlText.toString();

        // if media player is already active, stop it first
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            onCompletion(mediaPlayer);
        }

        // create the URI from the URL
        Uri uri = Uri.parse(url);

        // load the sound from the URI
        mediaPlayer = MediaPlayer.create(getActivity(), uri);

        // if media could not be loaded, the media player will be null
        if (mediaPlayer == null) {
            Toast.makeText(getActivity(), R.string.msg_wrong_url, Toast.LENGTH_LONG).show();
            return;
        }

        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.start();

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // when media playing stopped we need to release the resources so that we free up the memory
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
