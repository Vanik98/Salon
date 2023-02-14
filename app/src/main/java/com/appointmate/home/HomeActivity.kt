package com.appointmate.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.appointmate.BaseActivity
import com.appointmate.base.utils.extentions.setupWithNavController
import com.example.salon.R
import com.example.salon.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {
    private var binding: ActivityHomeBinding? = null
    private var currentNavController: LiveData<NavController>? = null
    private val navigationDestinationHandler = Handler(Looper.getMainLooper())
    private val navigationDestinationRunnable = Runnable {
        currentNavController?.value?.currentDestination?.id?.let {
            detectBottomNavigationBarVisibility(
                destinationId = it
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
//        noConnectionTextView = findViewById(R.id.noConnectionTextView)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.nav_graph_1,
            R.navigation.nav_graph_2,
            R.navigation.nav_graph_3,
            R.navigation.nav_graph_menu
        )

        val controller = binding?.bottomNavigationView?.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        currentNavController = controller

        controller?.observe(this) { navController ->
            when (navController.graph.startDestinationId) {
                R.id._1FakeFragment -> setConditionalStartDestination(
                    navController,
                    R.id.fragment1
                )
                R.id._2FakeFragment -> setConditionalStartDestination(
                    navController,
                    R.id.fragment2
                )
                R.id._3FakeFragment -> setConditionalStartDestination(
                    navController,
                    R.id.fragment3
                )
                R.id.menuFakeFragment -> setConditionalStartDestination(
                    navController,
                    R.id.menuFragment
                )
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                detectBottomNavigationBarVisibility(destination.id)

                navigationDestinationHandler.removeCallbacks(navigationDestinationRunnable)
                navigationDestinationHandler.postDelayed(navigationDestinationRunnable, 1000)
            }
        }
    }

    private fun setConditionalStartDestination(navController: NavController, @IdRes startDestId: Int, ) {
        navController.navInflater.inflate(R.navigation.nav_graph_general).apply {
            setStartDestination(startDestId)
            navController.graph = this
        }
    }

    private val bottomViewDestinationsThatShouldBeVisible = arrayListOf(
        R.id.fragment1,
        R.id.fragment2,
        R.id.fragment3,
        R.id.menuFragment
    )
    private fun detectBottomNavigationBarVisibility(destinationId: Int) {
        binding?.bottomNavigationView?.isVisible =
            bottomViewDestinationsThatShouldBeVisible.contains(destinationId)
    }
}