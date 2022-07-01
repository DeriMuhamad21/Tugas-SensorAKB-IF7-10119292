package com.tugas1.myfood_10119292;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

//Nama  : Deri Muhamad Handipa
//Nim   : 10119292
//Kelas : IF-7

public class MapsFragmen extends Fragment {
    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng Dimsum = new LatLng(-6.93259682005675, 107.71737852006233);
            LatLng Baso = new LatLng(-6.8883544005697654, 107.61569992228455);
            LatLng Resto = new LatLng(-6.884790190820445, 107.61889998164105);
            LatLng Kopi = new LatLng(-6.892397239973414, 107.63556998507563);
            LatLng Kawan = new LatLng(-6.88987887225915, 107.61551397940973);
            googleMap.addMarker(new MarkerOptions().position(Dimsum).title("Kedai DIMSUM Mantul!"));
            googleMap.addMarker(new MarkerOptions().position(Baso).title("Baso Aci Akang Dipatiukur "));
            googleMap.addMarker(new MarkerOptions().position(Resto).title("Reveuse Resto"));
            googleMap.addMarker(new MarkerOptions().position(Kopi).title("Kopi Nako Bandung"));
            googleMap.addMarker(new MarkerOptions().position(Kawan).title("Kawan Kopi"));
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            client = LocationServices.getFusedLocationProviderClient(getActivity());
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Dimsum, 13.0F));
            mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
            getCurrentLocation();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void getCurrentLocation() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        client = LocationServices.getFusedLocationProviderClient(getActivity());

//        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
//        Location lastKnownLocation = locationManager.getLastKnownLocation(locationManager);

        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            Log.e("Last Location: ", location.toString());
                            LatLng currentLoc = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(currentLoc).title("Lokasi anda saat ini"));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 17));
                        }
                    });

                }
            }
        });
    }



}