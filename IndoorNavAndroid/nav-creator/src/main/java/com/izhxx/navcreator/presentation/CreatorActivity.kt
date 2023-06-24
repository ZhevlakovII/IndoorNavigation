package com.izhxx.navcreator.presentation

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.izhxx.navcreator.R
import com.izhxx.navcreator.databinding.NavCreatorActivityBinding
import com.izhxx.navcreator.utlis.ext.daggerComponent
import com.izhxx.navshared.base.NavActivity

internal class CreatorActivity : NavActivity() {

    private var _binding: NavCreatorActivityBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerComponent.inject(this)

        _binding = NavCreatorActivityBinding.inflate(layoutInflater)

        setTheme(com.izhxx.navuikit.R.style.IndoorNavigation)
        setContentView(binding.root)

        initNavigation()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initNavigation() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.navCreatorFragmentContainer) as NavHostFragment
        val navController = navHost.navController

        binding.navCreatorBottomNavigation.setupWithNavController(navController = navController)
    }

}