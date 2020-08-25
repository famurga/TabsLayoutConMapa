package com.example.fragmentsdinamicos.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentsdinamicos.MainActivity;
import com.example.fragmentsdinamicos.R;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class CoordenadasFragment extends Fragment {


    private static final int PERMISSION_REQUEST_CODE =111 ;
    TextView txtVista, txtLatitud;
    TextView txtLongitud;
    private LocationManager ubicacion;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordenadas, container, false);

        txtLatitud = v.findViewById(R.id.txtLati);
        txtLongitud = v.findViewById(R.id.txtLon);


        if (getArguments() != null) {
            String dato = getArguments().getString("clave", "jiji");
            Log.e("Prueba", "Si llega");

            txtVista.setText(dato);
        }

        localizacion();
        return v;
    }


    private void localizacion() {




        //si la API 23 a mas
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Habilitar permisos para la version de API 23 a mas

            int verificarPermisoReadContacts = ContextCompat
                    .checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);

            int verificarPermiso = ContextCompat
                    .checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
            //Verificamos si el permiso no existe
            if(verificarPermisoReadContacts != PackageManager.PERMISSION_GRANTED){
                //verifico si el usuario a rechazado el permiso anteriormente
                if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)){
                    //Si a rechazado el permiso anteriormente muestro un mensaje
                    mostrarExplicacion();
                }else{
                    //De lo contrario carga la ventana para autorizar el permiso
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
                }

            }else{
                //Si el permiso ya fue concedido abrimos en intent de contactos
                localizacion();
            }

        }else{//Si la API es menor a 23 - abrimos en intent de contactos

            Log.e("TAG","holita");


        }




        ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
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

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                            int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.

                    localizacion();
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







}