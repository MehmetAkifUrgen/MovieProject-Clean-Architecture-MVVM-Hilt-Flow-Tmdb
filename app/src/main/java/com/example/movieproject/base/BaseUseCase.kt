package com.example.movieproject.base

import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*

abstract class BaseUseCase<T : Any> {

    private var disposable: Flow<T>? = null

    internal abstract suspend fun buildUseCaseFlow(): Flow<T>

    suspend fun execute(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onFinished: () -> Unit = {}
    ): Job {
        disposeLast()
        return buildUseCaseFlow()
            .flowOn(IO)
            .onCompletion { onFinished() }
            .catch { e -> onError(e) }
            .onEach { onSuccess(it) }
            .launchIn(GlobalScope)
    }

    private suspend fun disposeLast() {
        disposable?.let {
            if (it is Flow<*>) {
                it.collect()
            }
        }
    }

    private val disposables = CompositeDisposable()

    fun dispose() {
        disposables.dispose()
    }


}
