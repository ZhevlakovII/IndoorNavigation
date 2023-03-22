package com.izhxx.navui.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.izhxx.navui.R
import com.izhxx.navui.databinding.NavUiMainActivityBinding
import com.izhxx.navui.presentation.base.BaseActivity

internal class MainActivity : BaseActivity(R.layout.nav_ui_main_activity) {

    private var _binding: NavUiMainActivityBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = NavUiMainActivityBinding.inflate(LayoutInflater.from(this))

        setTheme(com.izhxx.navuikit.R.style.IndoorNavigation)
        setContentView(R.layout.nav_ui_main_activity)

        initNavigation()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initNavigation() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.navUiFragmentContainer) as NavHostFragment
        val navController = navHost.navController

        binding.navUiBottomNavigation.setupWithNavController(navController = navController)
    }

}