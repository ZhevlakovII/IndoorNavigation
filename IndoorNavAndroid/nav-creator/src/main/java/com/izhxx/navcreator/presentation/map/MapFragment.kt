package com.izhxx.navcreator.presentation.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.izhxx.navcreator.databinding.NavCreatorFragmentMapBinding
import com.izhxx.navcreator.utlis.ext.daggerComponent
import com.izhxx.navshared.base.NavFragment

class MapFragment : NavFragment() {

    private var _binding: NavCreatorFragmentMapBinding? = null
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
        _binding = NavCreatorFragmentMapBinding.inflate(inflater)

        return binding.root
    }

}