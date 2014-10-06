package com.comtrade.sounddemo.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * <p>A placeholder fragment containing the list view.</p>
 * <p>Created by <a href="mailto:rok.koncina@comtrade.com">Rok Koncina</a> on 3.10.2014.</p>
 */

public class SoundListFragment extends Fragment {
    public static final int TYPE_MEDIA_PLAYER = 0;
    public static final int TYPE_SOUND_POOL = 1;

    private int type;

    public SoundListFragment(int type) {
        this.type = type;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // get the list of sounds from activity
        List<Sound> sounds = ((SoundsActivity) getActivity()).sounds;

        // initializing one of the list views
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(type == TYPE_MEDIA_PLAYER
                ? new MediaPlayerAdapter(getActivity(), sounds)
                : new SoundPoolAdapter(getActivity(), sounds));

        return rootView;
    }

}
