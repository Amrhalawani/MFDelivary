package com.aks.shagra.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aks.shagra.R
import com.aks.shagra.data.local.SharedPref
import com.aks.shagra.ui.main.BaseFragment
import com.aks.shagra.ui.main.MainActivity
import com.aks.shagra.utils.load
import com.aks.shagra.utils.visible
import com.aks.shagra.utils.watchYoutubeVideo
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_m.*

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_m, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text_welcome_user.text =
            "${SharedPref.getUserName(requireContext())}, ${getString(R.string.how_r_u)}"
        loadSettings()
        getFCMRegistrationToken()

    }


    private fun loadSettings() {
        (activity as MainActivity).showProgressBar(true)
        viewModel.getSettings()
            .observe(viewLifecycleOwner) { result ->
                (activity as MainActivity).showProgressBar(false)
                if (result != null) {

                    if (result.statusCode == 200) {

                        result.data?.let {

                            SharedPref.setTreeThumbnail(requireContext(), it.image!!)
                            imageView.load(it.image)
                            SharedPref.setAppDesc(requireContext(), it.origin ?: "")

                            result.data.meeting?.let {
                                text_meeting_time.visible()
                                text_meeting_time.text = "$it ${result.data.meeting_date}"
                            }


                            result.data.live_streaming?.let { link ->
                                ll_live_stream.visible()
                                ll_live_stream.setOnClickListener {
                                    watchYoutubeVideo(requireContext(), link)
                                }
                            }

                        }

                    } else {


                    }
                } else {
                    Toast.makeText(
                        activity, getString(R.string.server_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }


    fun getFCMRegistrationToken() {

        FirebaseApp.initializeApp(requireContext())

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("tag", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            sendFirebaseTokenToOurServer(token!!)
            val msg = "fcm: $token"
            Log.e("tag", msg)

        })
    }


    private fun sendFirebaseTokenToOurServer(player_id: String) {

//        viewModel.sendFirebaseToken(player_id)
//            .observe(this) { result ->
//                if (result != null) {
//
//                } else {
//                    Log.e("TAG", "Error sendFirebaseToken: $result")
//                }
//            }
    }

}