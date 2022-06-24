package com.example.appsaludmi

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appsaludmi.databinding.FragmentMapDomicilioBinding
import com.example.appsaludmi.viewModels.PerfilUsuarioViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapDomicilioFragment : Fragment() {

    private val SOLICITUD_PERMISO_UBICACION = 100
    private lateinit var mMap: GoogleMap

    private var _binding: FragmentMapDomicilioBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataViewModel: PerfilUsuarioViewModel

    private var marker : Marker? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        /*val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
        mMap = googleMap

        var latitud = -42.7692
        var longitud = -65.03851

        val ll = dataViewModel.latlng.value.toString()
        if (!ll.isEmpty()) {
            val llarray = ll.split(",")
            if (llarray.size == 2) {
                latitud = llarray[0].toDouble()
                longitud = llarray[1].toDouble()
            }
        }
        Log.i("Mapa","latitud: " + latitud + " , longitud: " + longitud )
        val pm = LatLng( latitud, longitud)
        val zoom = 15f

        mMap.addMarker(
            MarkerOptions()
                .position(pm)
                .draggable(true)
                .title("Marker in Puerto Madryn")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.home_icon)))

        mMap.setOnMarkerDragListener( object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragEnd(p0: Marker) {
                if (p0 != null) {
                    val newlat = p0.position.latitude
                    val newlng = p0.position.longitude
                    Log.i("Map position", "Latitude: "  + newlat + ", longitude: " + newlng )
                    dataViewModel.setLatlng( newlat.toString() + "," + newlng.toString() )
                }
            }
            override fun onMarkerDragStart(p0: Marker) {
                Log.i("Map onMarkerDragStart", "p0: "  + p0.position.toString() )
            }
            override fun onMarkerDrag(p0: Marker) {
                Log.i("Map onMarkerDrag", "p0: "  + p0.position.toString() )
            }
        })
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pm,zoom))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapDomicilioBinding.inflate(inflater, container, false)
        dataViewModel = ViewModelProvider(requireActivity()).get(PerfilUsuarioViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == SOLICITUD_PERMISO_UBICACION) {
            if (grantResults.contains( PackageManager.PERMISSION_GRANTED)) {
                activarUbicacion()
            }
        }
    }

    private fun isPermisoHabilitado(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun activarUbicacion() {
        if (isPermisoHabilitado())
            mMap.isMyLocationEnabled = true
        else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                SOLICITUD_PERMISO_UBICACION
            )
        }
    }


}


