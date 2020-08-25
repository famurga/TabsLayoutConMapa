package com.example.fragmentsdinamicos.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentsdinamicos.MainActivity;
import com.example.fragmentsdinamicos.R;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class CoordenadasFragment extends Fragment {


    private static final int PERMISSION_REQUEST_CODE = 111;
    TextView txtVista, txtLatitud;
    TextView txtLongitud;
    Button gps;
    private LocationManager ubicacion;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordenadas, container, false);


        int permisoUbicacion = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        int permisoUbicacion2 = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permisoUbicacion != PackageManager.PERMISSION_GRANTED || permisoUbicacion2
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {



            } else {
                ActivityCompat.requestPermissions(getActivity(),new String[]{ Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},1);

            }
        }



        txtLatitud = v.findViewById(R.id.txtLati);
        txtLongitud = v.findViewById(R.id.txtLon);
        gps = v.findViewById(R.id.btngps);
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) getActivity().
                        getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                        txtLatitud.setText(String.valueOf(location.getLatitude()));


                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };

                int permision = ContextCompat.checkSelfPermission(getActivity(), ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                        locationListener);
            }
        });


        if (getArguments() != null) {
            String dato = getArguments().getString("clave", "jiji");
            Log.e("Prueba", "Si llega");

            txtVista.setText(dato);
        }





        return v;
    }


    private void localizacion() {

        ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


        }
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        if( ubicacion != null){



            Log.e("coordenadas","Longitud:"+loc.getLongitude());
            Log.e("Coordenadas 2","Latitud:"+loc.getLatitude());



       txtLatitud.setText(String.valueOf(loc.getLatitude()));
       txtLongitud.setText(String.valueOf(loc.getLongitude()));









        }
    }



    private void mostrarExplicacion() {
        new AlertDialog.Builder(getContext())
                .setTitle("Autorización")
                .setMessage("Necesito permiso para acceder a los contactos de tu dispositivo.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
                        }

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Mensaje acción cancelada
                        mensajeAccionCancelada();
                    }
                })
                .show();
    }

    public void mensajeAccionCancelada(){


        Toast.makeText(getContext(),
                "Haz rechazado la petición, por favor considere en aceptarla.",
                Toast.LENGTH_SHORT).show();
    }
    /*

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                            int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.

                    //localizacion();
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }

     */







}