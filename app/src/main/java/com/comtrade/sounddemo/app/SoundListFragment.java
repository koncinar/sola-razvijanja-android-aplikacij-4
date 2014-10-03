package com.comtrade.sounddemo.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>A placeholder fragment containing the list view.</p>
 * <p>Created by <a href="mailto:rok.koncina@comtrade.com">Rok Koncina</a> on 3.10.2014.</p>
 */

public class SoundListFragment extends Fragment {

    private final List<Sound> sounds;

    public SoundListFragment() {
        sounds = new ArrayList<>();
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // creating the list of sounds in the resources
        sounds.add(new Sound(R.raw.appear, getActivity().getString(R.string.appear)));
        sounds.add(new Sound(R.raw.can_you_hear_me_now, getActivity().getString(R.string.can_you_hear_me_now)));
        sounds.add(new Sound(R.raw.chomp, getActivity().getString(R.string.chomp)));
        sounds.add(new Sound(R.raw.contact_type, getActivity().getString(R.string.contact_type)));
        sounds.add(new Sound(R.raw.dragonwheeze, getActivity().getString(R.string.dragon_wheeze)));
        sounds.add(new Sound(R.raw.electronic_interference_28, getActivity().getString(R.string.electronic_interference)));
        sounds.add(new Sound(R.raw.elfs_laughing, getActivity().getString(R.string.elfs_laughing)));
        sounds.add(new Sound(R.raw.euh_2, getActivity().getString(R.string.euh)));
        sounds.add(new Sound(R.raw.firebreath, getActivity().getString(R.string.firebreath)));
        sounds.add(new Sound(R.raw.frustration, getActivity().getString(R.string.frustration)));
        sounds.add(new Sound(R.raw.grunt, getActivity().getString(R.string.grunt)));
        sounds.add(new Sound(R.raw.gulp, getActivity().getString(R.string.gulp)));
        sounds.add(new Sound(R.raw.moan, getActivity().getString(R.string.moan)));
        sounds.add(new Sound(R.raw.pump_im, getActivity().getString(R.string.pump)));
        sounds.add(new Sound(R.raw.scaringcataway, getActivity().getString(R.string.scaring_cat_away)));
        sounds.add(new Sound(R.raw.shots_8, getActivity().getString(R.string.shots)));
        sounds.add(new Sound(R.raw.sneezing, getActivity().getString(R.string.sneezing)));
        sounds.add(new Sound(R.raw.thud, getActivity().getString(R.string.thud)));
        sounds.add(new Sound(R.raw.vacuumandtrumpet, getActivity().getString(R.string.vacuum_and_trumpet)));
        sounds.add(new Sound(R.raw.yeah_realy, getActivity().getString(R.string.yeah_really)));
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // initializing the list view
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(new SoundsListAdapter(getActivity(), sounds));

        return rootView;
    }

}
