package com.aks.shagra.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aks.shagra.Auth
import com.aks.shagra.BuildConfig
import com.aks.shagra.R
import com.aks.shagra.ui.WebActivity
import com.aks.shagra.ui.main.MainActivity
import com.aks.shagra.utils.showLongToast
import com.aks.shagra.utils.visible
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.android.synthetic.main.fragment_sign_in_m.*
//
//@AndroidEntryPoint
//class SignInFragmen : Fragment() {
//
//
//    private val viewModel by viewModels<AuthViewModel>()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sign_in_m, container, false)
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        (activity as MainActivity).hideBottomNavBar(true)
//
//
//
//        if (BuildConfig.BASE_URL.contains("demo")) {
//            et_demo_account.visible()
//            et_demo_account.setHintName("اسم النسخة التجريبية")
//        }
//
//
//
//        listeners()
//
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        et_phone.setText("")
//    }
//
//    private fun listeners() {
//        text_signin_to_otp.setOnClickListener {
//
//
//            if (et_phone.validate()) {
//                if (check_terms.isChecked) {
//
//                    if (BuildConfig.IS_DEMO_APP){
//                        val action =
//                            SignInFragmenDirections.actionSignInFragmenToOtpFragment(
//                                et_phone.getText(),
//                                Auth.SIGNIN,
//                                "",
//                                et_demo_account.getText()
//                            )
//                        findNavController().navigate(action)
//                    }else {
//                        sendOtp(
//                            et_phone.getText(),
//                            et_demo_account.getText()
//                        )}
//                } else {
//                    context?.showLongToast(getString(R.string.u_have_to_accept_to_continue))
//                }
//            }
//
//            et_demo_account.getText()
//        }
//
//        text_to_terms_conditions.setOnClickListener {
//            openWebSite("file:///android_asset/terms_ar.html")
//        }
//
//    }
//
//    fun openWebSite(value: String) {
//        val intent = Intent(requireContext(), WebActivity::class.java)
//        intent.putExtra(WebActivity::class.java.simpleName, value)
//        startActivity(intent)
//    }
//
//    private fun sendOtp(phone: String, if_demo: String) {
//        (activity as MainActivity).showProgressBar(true)
//        viewModel.sendOtp(phone, if_demo)
//            .observe(viewLifecycleOwner) { result ->
//                (activity as MainActivity).showProgressBar(false)
//                if (result != null) {
//                    if (result.statusCode == 200) {
//
//                        val action =
//                            SignInFragmenDirections.actionSignInFragmenToOtpFragment(
//                                phone,
//                                Auth.SIGNIN,
//                                "",
//                                if_demo
//                            )
//                        findNavController().navigate(action)
//
//                    } else {
//                        context?.showLongToast(result.message)
//
//                    }
//                } else {
//                    Toast.makeText(
//                        activity, getString(R.string.server_error),
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//    }
//
//
//}