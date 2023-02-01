package com.example.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.salon.R
import com.example.salon.Salon
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment(val onMapReady: (googleMap: GoogleMap) -> Unit) : Fragment() {
    private var mapFragment: SupportMapFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            onMapReady(googleMap)
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.custom_mup_style))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    companion object {
        private var cameraMarker: Marker? = null
        fun addSalonMarker(googleMap: GoogleMap,latLng: LatLng){
                cameraMarker?.remove()
                cameraMarker = addMarker(googleMap,latLng)
        }

        private fun addMarker(googleMap:GoogleMap, latLng:LatLng) = googleMap.addMarker(MarkerOptions().position(latLng).title(""))

        fun showSalon(googleMap: GoogleMap, salon: Salon) = addMarker(googleMap,salon.latLng)

        fun showAllSalons(googleMap: GoogleMap, salonsList: List<Salon>) = salonsList.forEach { showSalon(googleMap, it) }

        private fun moveCamera(googleMap: GoogleMap, latLng: LatLng) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }
}