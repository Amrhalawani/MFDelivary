package com.aks.shagra.ui.choosePlaceOnMap


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import com.aks.shagra.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_get_location.*
import kotlinx.android.synthetic.main.dialog_location.view.*
import java.io.IOException
import java.util.*


val CHOOSE_LOCATION_FROM_MAP_REQUEST_CODE = 852
val EXTRA_KEY_Location = "locationkey"

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraIdleListener{

    private var lastKnownLocation: Location? = null
    private var locationPermissionGranted: Boolean = false
    private lateinit var mMap: GoogleMap
    // The entry point to the Fused Location Provider.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val defaultLocation = LatLng(30.2523341, 31.2106085)
    private  val DEFAULT_ZOOM = 15
   val  PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

    var lastSelectedLatLongFromMark:LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_get_location)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

       // getLocationPermission()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        listeners()
        if (!isLocationEnabled(this)) {
            showLocationIsDisabledAlert()
        }



    }

    private fun listeners() {

        btn_location_done.setOnClickListener {
            closeActivity()
        }

    }
    private fun closeActivity() {
        val intent = Intent()

        val latLngAsString:String ="${lastSelectedLatLongFromMark?.latitude},${ lastSelectedLatLongFromMark?.longitude}"
        intent.putExtra(EXTRA_KEY_Location, latLngAsString)
        setResult(
            CHOOSE_LOCATION_FROM_MAP_REQUEST_CODE,
            intent
        ) // You can also send result without any data using setResult(int resultCode)
        finish()
    }

    private fun showLocationIsDisabledAlert() {
//        alert("We can't show your position because you generally disabled the location service for your device.") {
//            yesButton {
//            }
//            neutralPressed("Settings") {
//                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
//            }
//        }.show()

       // Toast.makeText(this, getString(R.string.u_have_to_enable_location),Toast.LENGTH_LONG).show()

        val mDialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_location, null)
        val alertDialogBuilder = AlertDialog.Builder(this, R.style.CustomAlertDialog).setView(mDialogView)
        val mAlertDialog = alertDialogBuilder.show()
        mDialogView.btn_no.setOnClickListener {
            finish()
            mAlertDialog.dismiss()}
        mDialogView.btn_yes.setOnClickListener {
             startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            finish()
            mAlertDialog.dismiss()
        }



    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Turn on the My Location layer and the related control on the map.
       updateLocationUI()

        // Get the current location of the device and set the position of the map.
    //    getDeviceLocation()




    }



    override fun onCameraMove() {
        mMap.clear()
        // display imageView
        imgLocationPinUp?.visibility = View.VISIBLE
    }
    override fun onCameraIdle() {
        // hiding imageView
        imgLocationPinUp?.visibility = View.GONE
        // customizing map marker with a custom icon
        // and place it on the current map camera position

        lastSelectedLatLongFromMark = mMap.cameraPosition.target
        val markerOptions = MarkerOptions().position(mMap.cameraPosition.target)
            .icon(bitmapDescriptorFromVector(this, R.drawable.ic_set_location))
        mMap.addMarker(markerOptions)


    }



    private fun bitmapDescriptorFromVector(
        context: Context,
        vectorResId: Int
    ): BitmapDescriptor? {
        val vectorDrawable: Drawable? =
            ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable?.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable?.getIntrinsicWidth()!!,
            vectorDrawable?.getIntrinsicHeight()!!,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
    private fun getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
            getDeviceLocation()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }
    /**
     * Handles the result of the request for location permissions.
     */
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        locationPermissionGranted = false

        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true
                    updateLocationUI()
                    getDeviceLocation()
                }else {
                    finish()
                }
            }
            else -> {
                finish()
            }
        }

    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private fun updateLocationUI() {
        if (mMap == null) {
            return
        }
        try {
            if (locationPermissionGranted) {
                mMap?.isMyLocationEnabled = true
                mMap.uiSettings?.isMyLocationButtonEnabled = true
                mMap.uiSettings.isZoomControlsEnabled = true
                getDeviceLocation()
            } else {
                mMap?.isMyLocationEnabled = false
                mMap?.uiSettings?.isMyLocationButtonEnabled = false
                lastKnownLocation = null
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }
    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private fun getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            val lastLatLng = LatLng(lastKnownLocation!!.latitude, lastKnownLocation!!.longitude)
                            Log.e("TAG", "getDeviceLocation: lastLatLong = ${lastLatLng}" )
                            mMap.apply {
                                // just a random location our map will point to when its launched


                                addMarker(MarkerOptions().apply {
                                    position(lastLatLng)
                                    title("Marker pointed")
                                    draggable(false)
                                })
                                // setup zoom level
                                animateCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng,18f))

                                // maps events we need to respond to
                                setOnCameraMoveListener(this@MapsActivity)
                                setOnCameraIdleListener(this@MapsActivity)

                            }
                        }



                    } else {
                        Log.d("TAG", "Current location is null. Using defaults.")
                        Log.e("TAG", "Exception: %s", task.exception)
                        mMap?.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat()))
                        mMap?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            }
            else {
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return LocationManagerCompat.isLocationEnabled(locationManager)
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.lastLocation
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && task.result != null) {
                    val mLastLocation = task.result

                    var address = "No known address"

                    val gcd = Geocoder(this, Locale.getDefault())
                    val addresses: List<Address>
                    try {
                        addresses = gcd.getFromLocation(mLastLocation!!.latitude, mLastLocation.longitude, 1)
                        if (addresses.isNotEmpty()) {
                            address = addresses[0].getAddressLine(0)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    val icon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.resources, R.drawable.ic_set_location))
                    mMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(mLastLocation!!.latitude, mLastLocation.longitude))
                            .title("Current Location")
                            .snippet(address)
                            .icon(icon)
                    )

                    val cameraPosition = CameraPosition.Builder()
                        .target(LatLng(mLastLocation.latitude, mLastLocation.longitude))
                        .zoom(17f)
                        .build()
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                } else {
                    Toast.makeText(this, "No current location found", Toast.LENGTH_LONG).show()
                }
            }
    }
}