package com.aks.shagra.ui.auth

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aks.shagra.Auth
import com.aks.shagra.BuildConfig
import com.aks.shagra.R
import com.aks.shagra.data.local.SharedPref
import com.aks.shagra.ui.main.MainActivity
import com.aks.shagra.utils.gone
import com.aks.shagra.utils.showLongToast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_otp_m.*

@AndroidEntryPoint
class OtpFragment : Fragment() {

    private val args by navArgs<OtpFragmentArgs>()
    private val viewModel by viewModels<AuthViewModel>()
    var firebaseToken = "empty"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp_m, container, false)
    }

    fun getFCMRegistrationToken() {

        FirebaseApp.initializeApp(requireContext())

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("tag", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            firebaseToken = task.result
            //sendFirebaseTokenToOurServer(token!!)
            val msg = "fcm: $firebaseToken"
            Log.e("tag", msg)

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).hideBottomNavBar(true)

        getFCMRegistrationToken()


        listeners()

        if (BuildConfig.IS_DEMO_APP) {
            otp_page.gone()
            (activity as MainActivity).showProgressBar(true)

            Handler().postDelayed({
                if (args.authType == Auth.SIGNIN) {
                    login(
                        "1511",
                        args.phone,
                        firebaseToken ?: "",
                        args.ifDemoAccount
                    )

                } else {
                    signUp(
                        "1511",
                        args.name,
                        args.phone,
                        firebaseToken,
                        args.ifDemoAccount
                    )
                }

            }, 3000)

        }

    }

    private fun listeners() {
        text_confirm_otp.setOnClickListener {
            if (pin_view.text.toString().length == 4) {
                if (args.authType == Auth.SIGNIN) {
                    login(
                        pin_view.text.toString(),
                        args.phone,
                        firebaseToken ?: "",
                        args.ifDemoAccount
                    )

                } else {
                    signUp(
                        pin_view.text.toString(),
                        args.name,
                        args.phone,
                        firebaseToken,
                        args.ifDemoAccount
                    )
                }

            } else {
                Toast.makeText(requireContext(), "أدخل الرقم السري بشكل صحيح", Toast.LENGTH_LONG)
            }
        }
    }

    private fun login(code: String, phone: String, playerId: String, if_demo_account_name: String) {
        (activity as MainActivity).showProgressBar(true)
        viewModel.login(code, phone, playerId, if_demo_account_name)
            .observe(viewLifecycleOwner) { result ->
                (activity as MainActivity).showProgressBar(false)
                if (result != null) {
                    if (result.statusCode == 200) {

                        SharedPref.setAuthState(requireContext(), true)
                        SharedPref.setAccessToken(requireContext(), result.authorization?.token!!)
                        SharedPref.setUserName(requireContext(), result.user?.name ?: "")
                        SharedPref.setPhone(requireContext(), phone)
                        SharedPref.setUserDemoAccount(requireContext(), if_demo_account_name)
                        val action = OtpFragmentDirections.actionOtpFragmentToHomeFragment()
                        findNavController().navigate(action)

                    } else {
                        context?.showLongToast(result.message)
                        (activity as MainActivity).onBackPressed()

                    }
                } else {
                    Toast.makeText(
                        activity, getString(R.string.server_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun signUp(
        code: String,
        name: String,
        phone: String,
        playerId: String,
        if_demo_account_name: String
    ) {
        (activity as MainActivity).showProgressBar(true)
        viewModel.register(code, name, phone, playerId, if_demo_account_name)
            .observe(viewLifecycleOwner) { result ->
                (activity as MainActivity).showProgressBar(false)
                if (result != null) {
                    if (result.statusCode == 200) {

                        SharedPref.setAuthState(requireContext(), true)
                        SharedPref.setAccessToken(requireContext(), result.authorization?.token!!)
                        SharedPref.setUserName(requireContext(), result.user?.name ?: "")
                        SharedPref.setPhone(requireContext(), phone)
                        SharedPref.setUserDemoAccount(requireContext(), if_demo_account_name)
                        val action = OtpFragmentDirections.actionOtpFragmentToHomeFragment()
                        findNavController().navigate(action)

                    } else {
                        context?.showLongToast(result.message)

                    }
                } else {
                    Toast.makeText(
                        activity, getString(R.string.server_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }


}