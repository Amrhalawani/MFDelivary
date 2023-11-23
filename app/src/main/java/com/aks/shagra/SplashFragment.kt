package com.aks.shagra

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import com.aks.shagra.data.local.SharedPref
import com.aks.shagra.ui.main.BaseFragment
import com.aks.shagra.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_m, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        (activity as MainActivity).hideBottomNavBar(true)

        completeTheFlow()
    }

    private fun completeTheFlow() {
        Handler().postDelayed({
            if (SharedPref.isUserAuthentacted(requireContext())) {
                val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                val action = SplashFragmentDirections.actionSplashFragmentToAuthFragment()
                findNavController().navigate(action)
            }

        }, 3000)
    }


}