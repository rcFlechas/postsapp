package com.example.postsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

class UserViewModel (private val userRepository: UserRepository) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _getAll = MutableLiveData<Event<UIState<List<UserBind>>>>()
    val getAll: LiveData<Event<UIState<List<UserBind>>>> = _getAll

    fun getAll(reload: Boolean = false) {
        if (reload) {
            refresh()
        }
        getAll()
    }

    private fun getAll() {

        subscriptions.add(
            userRepository.getAll()
                .doOnSubscribe {
                    _getAll.postValue(Event(UIState.OnLoading(true)))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        _getAll.postValue(Event(UIState.OnLoading(false)))
                    },
                    onNext = {
                        _getAll.postValue(Event(UIState.OnSuccess(it.map(UserEntity::toBind))))
                    },
                    onError = {
                        _getAll.postValue(Event( UIState.OnError(it.message ?: "Error" )))
                    }
                )
        )
    }

    private fun refresh() {

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