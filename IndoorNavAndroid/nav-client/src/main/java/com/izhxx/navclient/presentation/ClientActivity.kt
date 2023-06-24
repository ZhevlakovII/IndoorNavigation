package com.izhxx.navclient.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.izhxx.navclient.R
import com.izhxx.navclient.databinding.NavClientActivityBinding
import com.izhxx.navshared.base.NavActivity
import com.izhxx.navclient.domain.model.error.ErrorType
import com.izhxx.navclient.utils.ext.daggerComponent
import com.izhxx.navuikit.NavUiKitToast
import com.izhxx.navuikit.ext.gone
import com.izhxx.navuikit.ext.visible
import javax.inject.Inject

internal class ClientActivity : NavActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val sharedViewModel: SharedViewModel by viewModels<SharedViewModelImpl> { viewModelFactory }

    private var _binding: NavClientActivityBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerComponent.inject(clientActivity = this)

        _binding = NavClientActivityBinding.inflate(layoutInflater)

        setTheme(com.izhxx.navuikit.R.style.IndoorNavigation)
        setContentView(binding.root)

        initNavigation()
        initObserver()
    }

    override fun onStart() {
        super.onStart()
        sharedViewModel.fetchLocations()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initNavigation() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.navClientFragmentContainer) as NavHostFragment
        val navController = navHost.navController

        binding.navClientBottomNavigation.setupWithNavController(navController = navController)
        setupNavController(navController = navController)
    }

    private fun setupNavController(navController: NavController) {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.navClientSearchFragment -> { binding.navClientBottomNavigation.gone() }
                else -> { binding.navClientBottomNavigation.visible() }
            }
        }
    }

    private fun initObserver() {
        sharedViewModel.error.observe {
            when (it.errorType) {
                ErrorType.TOAST_SHOWS -> {
                    val message = it.errorMessage
                        ?: it.errorMessageResource?.let { messageId -> getString(messageId) }
                        ?: getString(R.string.nav_client_ui_error_unknown)

                    NavUiKitToast.Builder(binding.navClientMainContainer)
                        .error()
                        .duration(duration = NavUiKitToast.DURATION_MEDIUM)
                        .message(message = message)
                        .show()
                }
                else -> {}
            }
        }
    }

}