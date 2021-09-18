package com.example.postsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postsapp.models.data.remote.responses.UserResponse
import com.example.postsapp.models.repositories.UserRepository
import com.example.postsapp.utilities.Event
import com.example.postsapp.utilities.UIState
import com.example.postsapp.views.binds.UserBind
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UserViewModel (private val userRepository: UserRepository) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _getAll = MutableLiveData<Event<UIState<List<UserBind>>>>()
    val getAll: LiveData<Event<UIState<List<UserBind>>>> = _getAll

    fun getAll() {
        subscriptions.add(
            userRepository.refresh()
                .doOnSubscribe {
                    _getAll.postValue(Event(UIState.OnLoading(true)))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        _getAll.postValue(Event(UIState.OnLoading(false)))
                    },
                    onNext = {
                        _getAll.postValue(Event(UIState.OnSuccess(it.map(UserResponse::toBind))))
                    },
                    onError = {
                        _getAll.postValue(Event( UIState.OnError( it.message ?: "Error" ) ))
                    }
                )
        )
    }
}