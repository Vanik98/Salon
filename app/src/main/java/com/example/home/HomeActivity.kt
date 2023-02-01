package com.example.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.map.MapsFragment
import com.example.salon.R
import com.example.salon.Salon
import com.google.android.gms.maps.model.LatLng

class HomeActivity : AppCompatActivity() {
    private val salonsList = arrayListOf<Salon>()
    private lateinit var saveLocationBtn: Button
    private lateinit var location: LatLng

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        imitationAddList()
        val fragment = MapsFragment {
            it.setOnMapClickListener { latLan ->
                MapsFragment.addSalonMarker(it, latLan)
                location = latLan
            }
            MapsFragment.showAllSalons(it, salonsList)
        }
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, fragment)
        ft.commit()
        saveLocationBtn = findViewById(R.id.saveLocationTextView)
        saveLocationBtn.setOnClickListener {
            if (this::location.isInitialized) {
                addSalon(location)
            } else {
                Toast.makeText(this, "please chose location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addSalon(latLng: LatLng) {
        val salon = Salon("gyumri", latLng)
        salonsList.add(salon)
        println("---------------------------------------  $salonsList")
    }

    private fun imitationAddList() {
        val salon = Salon("yerevan", LatLng(-35.0, 152.0))
        salonsList.add(salon)
        val salon2 = Salon("sydney", LatLng(-34.0, 151.0))
        salonsList.add(salon2)
    }
}