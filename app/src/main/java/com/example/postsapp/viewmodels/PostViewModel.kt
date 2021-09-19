package com.example.postsapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postsapp.core.Constants
import com.example.postsapp.core.isConnect
import com.example.postsapp.core.subscribe
import com.example.postsapp.models.data.local.entities.PostEntity
import com.example.postsapp.models.data.remote.responses.PostResponse
import com.example.postsapp.models.repositories.PostRepository
import com.example.postsapp.utilities.Event
import com.example.postsapp.utilities.UIState
import com.example.postsapp.views.binds.PostBind
import com.example.postsapp.views.binds.UserBind
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PostViewModel (application: Application, private val postRepository: PostRepository) : AndroidViewModel(application) {

    private val subscriptions = CompositeDisposable()

    private val _getByUser = MutableLiveData<Event<UIState<List<PostBind>>>>()
    val getByUser: LiveData<Event<UIState<List<PostBind>>>> = _getByUser

    var userBind: UserBind? = null

    fun getByUser(userId: Long, reload: Boolean = false) {
        if (reload) {
            refreshByUser(userId)
        }
        this.getPostsByUser(userId)
    }

    private fun getPostsByUser(userId: Long) {

        subscriptions.add(
            postRepository.getByUser(userId)
                .doOnSubscribe {
                    _getByUser.postValue(Event(UIState.OnLoading(true)))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = { posts ->

                        if (posts.isNotEmpty()) {
                            _getByUser.postValue(Event(UIState.OnSuccess(posts.map(PostEntity::toBind))))
                        } else {
                            refreshByUser(userId)
                        }
                    },
                    onError = {
                        _getByUser.postValue(Event( UIState.OnError(it.message ?: "Error" )))
                    }
                )
        )
    }

    private fun refreshByUser(userId: Long) {
        if (isConnect()) {
            subscriptions.add(
                postRepository.refreshByUser(userId.toInt())
                    .doOnSubscribe {
                        _getByUser.postValue(Event(UIState.OnLoading(true)))
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                        onComplete = {
                            _getByUser.postValue(Event(UIState.OnLoading(false)))
                        },
                        onNext = {
                            savePosts(it.map(PostResponse::toEntity))
                        },
                        onError = {
                            _getByUser.postValue(Event( UIState.OnError( it.message ?: "Error" ) ))
                        }
                    )
            )
        } else {
            _getByUser.postValue(Event( UIState.OnError( Constants.ERROR_NETWORK ) ))
        }
    }

    private fun savePosts(posts: List<PostEntity>) {
        subscriptions.add(
            postRepository.insertAll(posts).subscribe(_getByUser)
        )
    }

    fun closeSubscriptions() {
        subscriptions.dispose()
    }
}
