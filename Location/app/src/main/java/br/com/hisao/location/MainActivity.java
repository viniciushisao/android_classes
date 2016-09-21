package br.com.hisao.location;

/**
 * Live template
 * Log.d(Log.LOG_TAG, "$CLASS$:$METHOD$:$LINE$ "$END$ );
 *
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_REFRESH_TIME = 10;
    private static final int LOCATION_REFRESH_DISTANCE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txvLocation = (TextView) findViewById(R.id.txv_location);


        final LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                txvLocation.setText("Longitude: " + location.getLongitude() + " Latitude: " + location.getLatitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {


            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }

        };

        

        Button btnGetLocation = (Button) findViewById(R.id.btn_getlocation);
        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Log.d("MainActivity", "MainActivity:onClick:74 ");
                    return;

                }
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                        LOCATION_REFRESH_DISTANCE, mLocationListener);
            }
        });

    }
}
