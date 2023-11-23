package com.aks.shagra.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.size
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.aks.shagra.R
import com.aks.shagra.databinding.ActivityMainBinding
import com.aks.shagra.utils.gone
import com.aks.shagra.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
     var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

//        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment_main)
        setupSmoothBottomMenu()
        //   appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

    }


//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, binding!!.bottombarHome)
        popupMenu.inflate(R.menu.home_nav)
        val menu = popupMenu.menu
       Log.e("TAG", "setupSmoothBottomMenu: ${menu.size}")
        binding!!.bottombarHome.setupWithNavController(menu, navController)
    }

    fun hideBottomNavBar(flag: Boolean) {
        if ( binding?.bottombarHome != null) {
            if (flag) {
                binding!!.bottombarHome.gone()
            } else {
                binding!!.bottombarHome.visible()
            }
        }
    }

    fun showProgressBar(flag: Boolean) {
        if ( binding?.cardMainProgress != null) {
            if (flag) {
                binding!!.cardMainProgress.visible()
            } else {
                binding!!.cardMainProgress.gone()
            }
        }
    }
}