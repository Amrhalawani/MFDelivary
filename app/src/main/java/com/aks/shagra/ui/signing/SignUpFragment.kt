package com.aks.shagra.ui.signing

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
import com.aks.shagra.ui.main.MainActivity
import com.aks.shagra.ui.auth.AuthViewModel
import com.aks.shagra.utils.showLongToast
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.android.synthetic.main.fragment_sign_in_m.*
//import kotlinx.android.synthetic.main.fragment_sign_up_m.*
//import kotlinx.android.synthetic.main.fragment_sign_up_m.et_demo_account
//import kotlinx.android.synthetic.main.fragment_sign_up_m.et_phone
//import kotlinx.android.synthetic.main.fragment_sign_up_m.text_signin_to_otp
//
//@AndroidEntryPoint
//class SignUpFragment : Fragment() {
//
//
//    private val viewModel by viewModels<AuthViewModel>()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sign_up_m, container, false)
//    }
//
//    @Deprecated("Deprecated in Java")
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        (activity as MainActivity).hideBottomNavBar(true)
//
//
//
//        text_signin_to_otp.setOnClickListener {
//
//            if (et_name.getText().contains(" ") && et_name.getText().length >= 10) {
//                if (et_phone.validate()) {
//                    sendOtp(et_phone.getText(), et_name.getText(), et_demo_account.getText()?:"")
//                }
//            } else {
//                et_name.validateActions(false)
//            }
//
//        }
//
//    }
//
//    private fun sendOtp(phone: String, name: String, ifdemo:String) {
//        (activity as MainActivity).showProgressBar(true)
//        viewModel.sendOtp(phone, ifdemo, )
//            .observe(viewLifecycleOwner) { result ->
//                (activity as MainActivity).showProgressBar(false)
//                if (result != null) {
//                    if (result.statusCode == 200) {
//                        val action =
//                            SignUpFragmentDirections.actionSignUpFragmentToOtpFragment(
//                                phone,
//                                Auth.SIGNUP, name, ifdemo
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
//}