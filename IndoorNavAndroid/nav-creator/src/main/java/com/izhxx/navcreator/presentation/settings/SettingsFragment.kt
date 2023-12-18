package com.izhxx.navcreator.presentation.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.izhxx.navcreator.databinding.NavCreatorFragmentSettingsBinding
import com.izhxx.navcreator.utlis.ext.daggerComponent
import com.izhxx.navshared.base.NavFragment

internal class SettingsFragment : NavFragment() {

    private var _binding: NavCreatorFragmentSettingsBinding? = null
    private val binding
        get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.daggerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NavCreatorFragmentSettingsBinding.inflate(inflater)

        return binding.root
    }

}