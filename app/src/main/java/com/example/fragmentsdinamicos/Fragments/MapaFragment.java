package com.example.fragmentsdinamicos.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentsdinamicos.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapaFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mgoogleMap;
    MapView mapView;
    View mview;
    Button btnPasarDato;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_mapa, container, false);

        btnPasarDato = mview.findViewById(R.id.btnPasarDato);

        DireccionFragment dire = DireccionFragment.newInstance("Juana","La Cubana");


        btnPasarDato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sato= "chaucito";
                TextView txtcor = (TextView)getActivity().findViewById(R.id.txtCoordenadas);
                txtcor.setText(sato);
            }
        });


        CoordenadasFragment coordenadasFragment= new CoordenadasFragment();
        DireccionFragment dir = new  DireccionFragment();
        Bundle bundle =new Bundle();
        bundle.putString("ahora","hola");
        dir.setArguments(bundle);


        // Inflate the layout for this fragment



        return mview;


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) mview.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }


        MapsInitializer.initialize(getContext());

        mgoogleMap = googleMap;
        mgoogleMap.setMyLocationEnabled(true);

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689241,-74.0445)).title("MI POSICION ALEATORIA").snippet("ewrwerwerw"));
        CameraPosition Liberty = CameraPosition.builder().target(new LatLng(40.689241,-74.0445)).zoom(18).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));




    }
}