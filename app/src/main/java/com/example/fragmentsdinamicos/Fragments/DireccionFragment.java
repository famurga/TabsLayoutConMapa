package com.example.fragmentsdinamicos.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentsdinamicos.R;

public class DireccionFragment extends Fragment {

    TextView txtVista;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // TODO: Rename and change types and number of parameters
    public static DireccionFragment newInstance(String param1, String param2) {
        DireccionFragment fragment = new DireccionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if( getArguments() != null){
            String dato = getArguments().getString(ARG_PARAM1,"jiji");
            String dato2 = getArguments().getString(ARG_PARAM2,"jiji");


            txtVista.setText(dato+dato2);
        }


        return inflater.inflate(R.layout.fragment_direccion, container, false);
    }
}