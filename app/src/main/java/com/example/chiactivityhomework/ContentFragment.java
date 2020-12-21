package com.example.chiactivityhomework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.security.PrivateKey;

public class ContentFragment extends Fragment {

    private static final String ARG_TAB_NUMBER = "tabNumber";
    private static final String ARG_TAB_BACKGROUND = "tabBackground";

    private TextView tabNumberTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        tabNumberTextView = v.findViewById(R.id.tabNumberTextView);
        tabNumberTextView.setText(getArguments().getString(ARG_TAB_NUMBER));
        v.setBackgroundColor(getArguments().getInt(ARG_TAB_BACKGROUND));
        return v;
    }

    public static ContentFragment newInstance(String tabNumber, int backgroundColor) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TAB_NUMBER, tabNumber);
        bundle.putInt(ARG_TAB_BACKGROUND, backgroundColor);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
