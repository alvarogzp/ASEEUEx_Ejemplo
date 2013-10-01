package com.example.asee_002.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.asee_002.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

}