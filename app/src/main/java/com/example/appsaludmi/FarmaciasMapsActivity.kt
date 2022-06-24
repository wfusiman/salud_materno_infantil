package com.example.appsaludmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.appsaludmi.databinding.ActivityFarmaciasMapsBinding
import com.example.appsaludmi.db.model.Farmacia
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FarmaciasMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityFarmaciasMapsBinding

    private val fdb: FirebaseDatabase = FirebaseDatabase.getInstance()

    val latitud = -42.7692
    val longitud = -65.03851

    private val farmacias: ArrayList<Farmacia> = ArrayList<Farmacia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFarmaciasMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.checkboxTurno.isChecked = false
        binding.checkboxTurno.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked)
                viewFarmacias(true)
            else
                viewFarmacias( false)
        }
    }


    private fun viewFarmacias( onlyTurno: Boolean) {
        if (farmacias.size == 0) {
            val ref = fdb.reference
            ref.child("farmacias").get().addOnSuccessListener {
                it.children.forEach { item ->
                    val farm = item.child("nombre").value.toString()
                    val lat = item.child("lat").value.toString()
                    val lng = item.child("lng").value.toString()
                    var turnosArray = ArrayList<String>()
                    item.child("turnos").children.forEach { turno ->
                        val f = turno.child("fecha").value.toString()
                        turnosArray.add( f )
                    }
                    val newFarmacia = Farmacia( nombre=farm, lat = lat, lng = lng, turnos = turnosArray )
                    farmacias.add( newFarmacia )
                }
                addMarkers( onlyTurno)
            }.addOnFailureListener {
                Log.e("SMI Farmacias", "Error recuperando  ")
            }
        }
        else {
            addMarkers( onlyTurno )
        }
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

        val pm = LatLng( latitud, longitud)
        val zoom = 15f

        /*marker = mMap.addMarker(
            MarkerOptions()
                .position(pm)
                .draggable(true)
                .title("Marker in Puerto Madryn"))!!*/

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pm,zoom))
        viewFarmacias(false)

        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }

    fun addMarkers( onlyTurno: Boolean ) {
        mMap.clear()
        val hoy = LocalDate.now()
        Log.e("add Markers","Dia " + hoy.toString() )
        for (item in farmacias) {
            var isturno = false
            if (onlyTurno) {
                for (turno in item.turnos) {
                    val df = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    val fecha = LocalDate.parse( turno, df)
                    val cmp = hoy.compareTo( fecha )
                    if (cmp == 0) {
                        isturno = true
                        break
                    }
                }
            }
            if (!onlyTurno || !isturno)
                mMap.addMarker(MarkerOptions().position( LatLng(item.lat.toDouble(), item.lng.toDouble()) ).title(item.nombre ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))
            else
                mMap.addMarker(MarkerOptions().position( LatLng(item.lat.toDouble(), item.lng.toDouble()) ).title(item.nombre + " TURNO" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_turno_icon)))
        }
/*
        mMap.addMarker(MarkerOptions().position( LatLng(-42.758322, -65.039079) ).title("Farmacia 1" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))

        mMap.addMarker(MarkerOptions().position( LatLng(-42.76716244959386, -65.03891254640735) ).title("Farmacia 2" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))

        mMap.addMarker(MarkerOptions().position( LatLng(-42.76656772407509, -65.03424933106284) ).title("Farmacia 3" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))

        mMap.addMarker(MarkerOptions().position( LatLng(-42.767985445352274, -65.03549387605604) ).title("Farmacia 4" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))

        mMap.addMarker(MarkerOptions().position( LatLng(-42.768713108690775, -65.03645063509335) ).title("Farmacia 5" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))

        mMap.addMarker(MarkerOptions().position( LatLng(-42.76899830466534, -65.04026001504099) ).title("Farmacia 6" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))

        mMap.addMarker(MarkerOptions().position( LatLng(-42.76781651684534, -65.03714666659342) ).title("Farmacia 7" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))

        mMap.addMarker(MarkerOptions().position( LatLng(-42.76475065312489, -65.03605665499896) ).title("Farmacia 8" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))

        mMap.addMarker(MarkerOptions().position( LatLng(-42.76905496411627, -65.03112060772146) ).title("Farmacia 9" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))

        mMap.addMarker(MarkerOptions().position( LatLng(-42.76360219880877, -65.03802708689487) ).title("Farmacia 10" ).icon( BitmapDescriptorFactory.fromResource(R.drawable.pharmacy_icon)))*/

    }
}