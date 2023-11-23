package com.aks.shagra.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.aks.shagra.*
import com.aks.shagra.ui.home.HomeFragment
import com.aks.shagra.utils.hideKeyboard
import com.yariksoffice.lingver.Lingver

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Lingver.getInstance().setLocale(requireContext(), "ar")
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // activity?.window?.hideSystemUI2()
        (activity as MainActivity).hideKeyboard()
        updateBottomNavBar()
    }

    fun updateBottomNavBar() {
        if (this::class.java == HomeFragment::class.java

        ){
            (requireActivity() as MainActivity).hideBottomNavBar(false)
        } else {
            (requireActivity() as MainActivity).hideBottomNavBar(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).showProgressBar(false)
    }

}