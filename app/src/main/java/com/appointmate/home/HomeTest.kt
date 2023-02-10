package com.appointmate.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appointmate.analytic.AnalyticFragment
import com.example.salon.R
import com.appointmate.salon.Salon
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.gms.maps.model.LatLng

class HomeTest : AppCompatActivity() {
    private val salonsList = arrayListOf<Salon>()
    private lateinit var saveLocationBtn: Button
    private lateinit var location: LatLng
    private lateinit var pieChart: PieChart
    val pieEntryList = arrayListOf<PieEntry>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        imitationAddList()
//        val fragment = MapsFragment {
//            it.setOnMapClickListener { latLan ->
//                MapsFragment.addSalonMarker(it, latLan)
//                location = latLan
//            }
//            MapsFragment.showAllSalons(it, salonsList)
//        }
        val fragment = AnalyticFragment()
        pieChart = findViewById(R.id.pieChart)
        addPieChart()
//        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
//        ft.replace(R.id.fragment, fragment)
//        ft.commit()
        saveLocationBtn = findViewById(R.id.saveLocationTextView)
        saveLocationBtn.setOnClickListener {
            if (this::location.isInitialized) {
                addSalon(location)
            } else {
                Toast.makeText(this, "please chose location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun addPieChart() {
        val colorList = arrayListOf<Int>()
        val freeTime = arrayListOf(
            "1", "2", "4", "7"
        )
        val arrTime = arrayListOf(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
        )
        val colors = arrayListOf(Color.rgb(1, 1, 1), Color.rgb(2, 2, 2))
        var isFree = false
        arrTime.forEach { time ->
            pieEntryList.add(PieEntry(0.12f, time))
            freeTime.forEach { freetime ->
                if (time == freetime) {
                    colorList.add(Color.GREEN)
                    isFree = true
                }
            }
            if (!isFree) {
                colorList.add(Color.GRAY)
            }
            isFree = false
        }
        val pieDataSet = PieDataSet(pieEntryList, "adasd")
        val pieData = PieData(pieDataSet)
        pieDataSet.setColors(
            colorList[0], colorList[1], colorList[2], colorList[3], colorList[4], colorList[5],
            colorList[6], colorList[7], colorList[8], colorList[9], colorList[10], colorList[11]
        )
        pieData.setDrawValues(false)
        pieChart.setEntryLabelColor(Color.BLACK)
        //TODO:delete
//        pieChart.setDrawEntryLabels(true)
//        pieChart.setUsePercentValues(true)
        pieChart.centerText = "Time..."
        pieChart.description.text = "Hayer...."
        pieChart.data = pieData
        pieChart.invalidate()
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