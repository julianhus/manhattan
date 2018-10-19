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
    Button profileButton;
    Button storeButton;
    Button productButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        // get the reference of Button
        profileButton = (Button) view.findViewById(R.id.bProfile);
        // perform setOnClickListener on first Button
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display a message by using a Toast
                Toast.makeText(getActivity(), R.string.editprofile, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                getActivity().startActivity(intent);
            }
        });
        storeButton = (Button) view.findViewById(R.id.bStore);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.store, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                getActivity().startActivity(intent);
            }
        });
        productButton = (Button) view.findViewById(R.id.bProduct);
        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.product, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}