package com.aks.shagra.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aks.shagra.R
import com.aks.shagra.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.android.synthetic.main.fragment_auth_m.*
//
//@AndroidEntryPoint
//class AuthFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_auth_m, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        (activity as MainActivity).hideBottomNavBar(true)
//
//        text_to_signin.setOnClickListener {
//            val action = AuthFragmentDirections.actionAuthFragmentToSignInFragmen()
//            findNavController().navigate(action)
//        }
//        text_to_signup.setOnClickListener {
//            val action = AuthFragmentDirections.actionAuthFragmentToSignUpFragment()
//            findNavController().navigate(action)
//        }
//
//    }
//}