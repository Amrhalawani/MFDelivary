package com.aks.shagra.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aks.shagra.data.NetworkRepository
import com.aks.shagra.data.models.AppSettingsRes
import com.aks.shagra.utils.handleErrorBody
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainRepository: NetworkRepository) :
    ViewModel() {

    private val disposables = CompositeDisposable()


    fun getSettings(): MutableLiveData<AppSettingsRes> {
        val data = MutableLiveData<AppSettingsRes>()
        disposables.add(
            mainRepository.getAppSettings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, { throwable ->
                    handleErrorBody(throwable, AppSettingsRes::class.java)
                })
        )
        return data
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


}