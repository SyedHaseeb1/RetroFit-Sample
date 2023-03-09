package com.hsb.syedhaseebtaks_janbark.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.hsb.syedhaseebtaks_janbark.R
import com.hsb.syedhaseebtaks_janbark.databinding.ActivityMainBinding
import com.hsb.syedhaseebtaks_janbark.utils.showInterstitial
import com.hsb.syedhaseebtaks_janbark.utils.toast

class MainActivity : AppCompatActivity() {
    private lateinit var navHost: NavHostFragment
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mytoolbar)
        navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.moviesNavigation))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        showInterstitial {
            navHost.navController.navigateUp(appBarConfiguration)
        }
        return true
    }
}