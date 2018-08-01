package com.example.abdulhakimbashir.pokemonadroid

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermission()

    }

var ACCESSLOCATION = 123
    fun checkPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.
                    checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), ACCESSLOCATION)
                return
            }
        }
        GetUserLocation()
    }
    @SuppressLint("MissingPermission")
    fun GetUserLocation(){
        Toast.makeText(this, "User location access on ", Toast.LENGTH_LONG).show()
        //TODO: Will implement later

        var myLocation = MyLocationListener()

        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)
        var mythread=myThread()
        mythread.start()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            ACCESSLOCATION->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    GetUserLocation()
                }else{
                    Toast.makeText(this, "We cannot access your location ", Toast.LENGTH_LONG).show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions()
//                .position(sydney)
//                .title("Me")
//                .snippet(" Here is my location")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.boss)))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f))
    }

    var location:Location?=null
    inner class MyLocationListener:LocationListener {

        constructor(){
            location=Location("Start")
            location!!.longitude=0.0
            location!!.longitude=0.0
        }
        override fun onLocationChanged(p0: Location?) {
            location=p0
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(p0: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(p0: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    inner class myThread:Thread{
        constructor():super(){

        }

        override fun run(){
            while (true){
                try {
                    runOnUiThread {
                        mMap!!.clear()
                        mMap?.mapType = GoogleMap.MAP_TYPE_SATELLITE
                        val sydney = LatLng(location!!.latitude, location!!.longitude)
                        var accuracy: Float = location!!.accuracy
                        var myLongitude: Double = location!!.longitude
                        var myLatitude: Double = location!!.latitude
                        mMap.addMarker(MarkerOptions()
                                .position(sydney)
                                .title("Me")
                                .snippet("Latitude $myLatitude , Longitude $myLongitude, Accuracy $accuracy")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.boss)))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 21f))
                    }
                    Thread.sleep(1000)

                }catch (ex:Exception){}
            }
        }
    }

//    var listPokemons = ArrayList<Pokemon>()
//    fun LoadPokemons(){
//        listPokemons.add(Pokemon(R.drawable.dungeon,
//                "Duengoen", "Here is from Japan", 55.0, 37.7789994893035, -122.401846647263))
//        listPokemons.add(Pokemon(R.drawable.dungeon,
//                "Duengoen", "Here is from Japan", 55.0, 37.7789994893035, -122.401846647263))
//    }
}
