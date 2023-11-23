package com.aks.shagra.ui.auth


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aks.shagra.data.NetworkRepository
import com.aks.shagra.data.models.DefaultRes
import com.aks.shagra.data.models.LoginRes
import com.aks.shagra.data.models.ProfileRes
import com.aks.shagra.utils.handleErrorBody
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val mainRepository: NetworkRepository) :
    ViewModel() {

    private val disposables = CompositeDisposable()


    fun login(
        code: String,
        phone: String,
        player_id: String,
        if_demo_account_name: String?
    ): MutableLiveData<LoginRes?> {
        val data = MutableLiveData<LoginRes?>()
        disposables.add(
            mainRepository.login(code, phone, player_id, if_demo_account_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, { throwable ->
                    data.value = handleErrorBody(throwable, LoginRes::class.java)
                })
        )
        return data
    }

    fun register(
        code: String,
        name: String,
        phone: String,
        player_id: String,
        if_demo_account_name: String?
    ): MutableLiveData<LoginRes?> {
        val data = MutableLiveData<LoginRes?>()
        disposables.add(
            mainRepository.register(
                code,
                name,
                phone,
                player_id,
                if_demo_account_name
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, { throwable ->
                    data.value = handleErrorBody(throwable, LoginRes::class.java)
                })
        )
        return data
    }

    fun getProfile(

    ): MutableLiveData<ProfileRes?> {
        val data = MutableLiveData<ProfileRes?>()
        disposables.add(
            mainRepository.getProfile(
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, { throwable ->
                    data.value = handleErrorBody(throwable, ProfileRes::class.java)
                })
        )
        return data
    }

    fun sendOtp(phone: String, if_demo: String,): MutableLiveData<DefaultRes?> {
        val data = MutableLiveData<DefaultRes?>()
        disposables.add(
            mainRepository.sendOTP(phone, if_demo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, { throwable ->
                    data.value = handleErrorBody(throwable, DefaultRes::class.java)
                })
        )
        return data
    }

    fun deleteAccount(): MutableLiveData<DefaultRes?> {
        val data = MutableLiveData<DefaultRes?>()
        disposables.add(
            mainRepository.deleteAccount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, { throwable ->
                    data.value = handleErrorBody(throwable, DefaultRes::class.java)
                })
        )
        return data
    }


    fun updateProfile(
        name: String,
        job: String,
        twitter: String,
        fb: String,
        wa: String,
        insta: String,
        avatar: String?,
        aboutme: String,
        address: String,
        mother_name: String,
    ): MutableLiveData<ProfileRes?> {
        val data = MutableLiveData<ProfileRes?>()
        disposables.add(
            mainRepository.updateProfile(
                name, job, twitter, fb, wa, insta, avatar, aboutme,
                address,
                mother_name,
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, { throwable ->
                    data.value = handleErrorBody(throwable, ProfileRes::class.java)
                })
        )
        return data
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


}