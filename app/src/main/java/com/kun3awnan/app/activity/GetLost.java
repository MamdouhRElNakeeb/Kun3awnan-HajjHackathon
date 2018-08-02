package com.kun3awnan.app.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kun3awnan.app.R;
import com.kun3awnan.app.helper.PermissionUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetLost extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        LocationListener{

    Toolbar mToolbar;
    TextView toolbar_title ;
    ImageView iv_background ;
    ImageView iv_logo ;
    ImageView iv_ic ;
    EditText et_phone ;
    EditText et_name ;


    // LogCat tag
    private static final String TAG = GetLost.class.getSimpleName();

    private final static int PLAY_SERVICES_REQUEST = 1000;
    private final static int REQUEST_CHECK_SETTINGS = 2000;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;

    private Location mLastLocation, mCurrentLocation;

    // Google client to interact with Google API

    private GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    double latitude;
    double longitude;

    // list of permissions

    ArrayList<String> permissions=new ArrayList<>();
    PermissionUtils permissionUtils;

    boolean isPermissionGranted;

    private GoogleMap mMap;
    double lat,lon ;

    Marker myMarker, campMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getlost_activity);
        initToolbar();
        toolbar_title = findViewById(R.id.toolbar);

        et_name.setText(getSharedPreferences("User", MODE_PRIVATE).getString("name", ""));
        et_phone.setText(getSharedPreferences("User", MODE_PRIVATE).getString("mobile", ""));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getCampLocation();


//        btn_request_chair.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                getLostRequest();
//
//            }
//        });
    }

    private void initToolbar() {
        toolbar_title = findViewById(R.id.toolbarText);
        toolbar_title.setText("ضللت الطريق");

        findViewById(R.id.toolbarBackIV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void getCampLocation(){

//        ANRequest.GetRequestBuilder getRequestBuilder = AndroidNetworking.get(Url.CAMP_INFO + getSharedPreferences("User", MODE_PRIVATE).getString("campaign",""));
//
//        getRequestBuilder.build().getAsOkHttpResponseAndJSONObject(new OkHttpResponseAndJSONObjectRequestListener() {
//
//            @Override
//            public void onResponse(Response okHttpResponse, JSONObject response) {
//
//                Log.d("response", response.toString());
//
//                try {
//                    if (response.has("success") && response.getBoolean("success")){
//
//                        JSONObject jsonObject = response.getJSONObject("campaign");
//
//                        String title = "", address = "";
//                        double lat = 0, lng = 0;
//                        if (jsonObject.has("lat"))
//                            lat = jsonObject.getDouble("lat");
//                        if (jsonObject.has("lng"))
//                            lng = jsonObject.getDouble("lng");
//
//                        if (jsonObject.has("locationName"))
//                            title = jsonObject.getString("locationName");
//                        if (jsonObject.has("address"))
//                            address = jsonObject.getString("address");
//
//                        LatLng loc = new LatLng(lat, lng);
//                        MarkerOptions locationMarker=new MarkerOptions();
//
//                        locationMarker.position(loc)
//                                .title(title)
//                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon))
//                                .snippet(address);
//
//                        campMarker = mMap.addMarker(locationMarker);
//                        campMarker.showInfoWindow();
//
//                        if (myMarker != null){
//                            LatLngBounds.Builder builder = new LatLngBounds.Builder();
//                            builder.include(myMarker.getPosition());
//                            builder.include(campMarker.getPosition());
//                            LatLngBounds bounds = builder.build();
//                            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);
//                            mMap.animateCamera(cu);
//                        }
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(ANError anError) {
//
//            }
//        });
    }

    private void getLostRequest(){

        if (mCurrentLocation == null)
            return;

        String userID = getSharedPreferences("User", MODE_PRIVATE).getString("id", "");
        String campID = getSharedPreferences("User", MODE_PRIVATE).getString("campaign", "");

//        ANRequest.PostRequestBuilder postRequestBuilder = AndroidNetworking.post(Url.HAGG_TAYEH_REQUEST);
//
//        postRequestBuilder.addHeaders("Content-Type", "application/json")
//                .addBodyParameter("campaign", campID)
//                .addBodyParameter("user", userID)
//                .addBodyParameter("lat", String.valueOf(mCurrentLocation.getLatitude()))
//                .addBodyParameter("lng", String.valueOf(mCurrentLocation.getLongitude()))
//                .addBodyParameter("insertedAt", String.valueOf(System.currentTimeMillis()))
//                .build().
//                getAsOkHttpResponseAndJSONObject(new OkHttpResponseAndJSONObjectRequestListener() {
//            @Override
//            public void onResponse(Response okHttpResponse, JSONObject response) {
//
//                Log.d("response", response.toString());
//
//                try {
//                    if (response.has("success") && response.getBoolean("success")){
//                        Toast.makeText(HaggTayehActivity.this, "تم إرسال الطلب بنجاح", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(ANError anError) {
//
//            }
//        });

    }



    private void enableMyLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

        } else {
            Toast.makeText(this, "Location permission is not enabled", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    1);
        }

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private void initListeners() {
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(this.getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onPause() {
        super.onPause();
        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        mCurrentLocation = location;
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12f));

        if (mCurrentLocation != null){
            LatLng latLng1 = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(12f));
        }

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (mCurrentLocation == null)
                return;

            LatLng loc = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            MarkerOptions locationMarker=new MarkerOptions();

            locationMarker.position(loc)
                    .title("الموقع الحالى")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_red));

            myMarker = mMap.addMarker(locationMarker);
            myMarker.showInfoWindow();

            if (campMarker != null){
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(myMarker.getPosition());
                builder.include(campMarker.getPosition());
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);
                mMap.animateCamera(cu);
            }
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        enableMyLocation();

        initListeners();


        if (mCurrentLocation != null){

            LatLng loc = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            MarkerOptions locationMarker=new MarkerOptions();
            locationMarker.position(loc)
                    .title("الموقع الحالى")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_red));

            myMarker = mMap.addMarker(locationMarker);
            myMarker.showInfoWindow();

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc,12f));

            if (campMarker != null){
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(myMarker.getPosition());
                builder.include(campMarker.getPosition());
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);
                mMap.animateCamera(cu);
            }

        }

    }
}