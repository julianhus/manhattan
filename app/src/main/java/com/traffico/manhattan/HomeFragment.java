package com.traffico.manhattan;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class HomeFragment extends Fragment {


    View view;
    Button firstButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        // get the reference of Button
        firstButton = (Button) view.findViewById(R.id.bProfile);
        // perform setOnClickListener on first Button
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display a message by using a Toast
                Toast.makeText(getActivity(), "Home Fragment", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}