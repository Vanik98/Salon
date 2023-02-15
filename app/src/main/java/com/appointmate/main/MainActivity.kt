package com.appointmate.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.appointmate.BaseActivity
import com.appointmate.barber.net.BarberItemDto
import com.appointmate.base.utils.SizeUtils
import com.appointmate.base.utils.helper.helper.VerticalSpacesItemDecoration
import com.appointmate.home.HomeActivity
import com.example.salon.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    private val adapter = BarberAdapter {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
//        val ft = supportFragmentManager.beginTransaction()
//        ft.replace(com.example.salon.R.id.main_ConstraintLayout, MapsFragment{})
//        ft.commit()
    }

    private fun setupViews() {
        binding = DataBindingUtil.setContentView(this, com.example.salon.R.layout.activity_main)
        val verticalDecoration = VerticalSpacesItemDecoration(SizeUtils.dp2px(this, 3f))
        binding.barbersRecyclerView.addItemDecoration(verticalDecoration)
        binding.barbersRecyclerView.adapter = adapter
        adapter.updateData(
            arrayListOf(
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
                BarberItemDto("Vanik Dallakyan"),
            )
        )
    }

}