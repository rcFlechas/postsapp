package com.example.postsapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.postsapp.core.Constants
import com.example.postsapp.core.isConnect
import com.example.postsapp.core.subscribe
import com.example.postsapp.models.data.local.entities.UserEntity
import com.example.postsapp.models.data.remote.responses.UserResponse
import com.example.postsapp.models.repositories.UserRepository
import com.example.postsapp.utilities.Event
import com.example.postsapp.utilities.UIState
import com.example.postsapp.views.binds.UserBind
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UserViewModel (application: Application, private val userRepository: UserRepository) : AndroidViewModel(application) {

    private val subscriptions = CompositeDisposable()

    private val _getAll = MutableLiveData<Event<UIState<List<UserBind>>>>()
    val getAll: LiveData<Event<UIState<List<UserBind>>>> = _getAll

    fun getAll(reload: Boolean = false) {
        if (reload) {
            refresh()
        }
        getUsers()
    }

    private fun getUsers() {

        subscriptions.add(
            userRepository.getAll()
                .doOnSubscribe {
                    _getAll.postValue(Event(UIState.OnLoading(true)))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = { users ->

                        if (users.isNotEmpty()) {
                            _getAll.postValue(Event(UIState.OnSuccess(users.map(UserEntity::toBind))))
                        } else {
                            refresh()
                        }
                    },
                    onError = {
                        _getAll.postValue(Event( UIState.OnError(it.message ?: "Error" )))
                    }
                )
        )
    }

    private fun refresh() {

        if (isConnect()) {
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
                            saveAll(it.map(UserResponse::toEntity))
                        },
                        onError = {
                            _getAll.postValue(Event( UIState.OnError( it.message ?: "Error" ) ))
                        }
                    )
            )
        } else {
            _getAll.postValue(Event( UIState.OnError( Constants.ERROR_NETWORK ) ))
        }
    }

    private fun saveAll(users: List<UserEntity>) {
        subscriptions.add(
            userRepository.insertAll(users).subscribe(_getAll)
        )
    }

    fun closeSubscriptions() {
        subscriptions.dispose()
    }

}