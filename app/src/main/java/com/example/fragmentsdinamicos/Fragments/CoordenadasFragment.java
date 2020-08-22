package com.example.fragmentsdinamicos.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentsdinamicos.R;


public class CoordenadasFragment extends Fragment {


    TextView txtVista, txtLatitud;
    TextView txtLongitud;
    private LocationManager ubicacion;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordenadas, container, false);




        if (getArguments() != null) {
            String dato = getArguments().getString("clave", "jiji");
            Log.e("Prueba", "Si llega");

            txtVista.setText(dato);
        }

        localizacion();
        return v;
    }


    private void localizacion() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            },1000) ;

        }


        txtLatitud = getActivity().findViewById(R.id.txtLati);
        txtLongitud = getActivity().findViewById(R.id.txtLon);


        ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        if( ubicacion != null){



            Log.e("coordenadas","Longitud:"+loc.getLongitude());

        txtLatitud.setText(String.valueOf(loc.getLatitude()));
        txtLongitud.setText(String.valueOf(loc.getLongitude()));

        }
    }
}