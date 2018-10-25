package com.traffico.manhattan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class DashboardFragment extends Fragment {

    View view;
    Button bShopping;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        bShopping = view.findViewById(R.id.bshopping);
        bShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.shopping, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ShoppingStoreActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}
