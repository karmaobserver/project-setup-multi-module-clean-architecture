package com.aleksejmakaji.presentation.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.aleksejmakaji.domain.error.FileFinderError
import com.aleksejmakaji.domain.wrapper.ErrorState
import com.aleksejmakaji.domain.wrapper.State
import com.aleksejmakaji.domain.wrapper.SuccessState
import com.aleksejmakaji.presentation.common.BaseFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Launch and collect flow result.
 * @param flow flow instance
 * @param owner lifecycle owner
 * @param onSuccess handle result data object
 * @param onError handle error data object. If not overridden, as default behaviour it will be handled from [BaseFragment] showError method.
 * @param onLoading handle loading state. If not overridden, as default behaviour it will be handled from [BaseFragment] showProgress method.
 * @param onHideLoading handle after loading state is finished. If not overridden, as default behaviour it will be handled from [BaseFragment] showProgress method.
 * @param minActiveState Lifecycle.State in which block runs in a new coroutine. That coroutine will cancel if the lifecycle falls below that state, and will restart if it's in that state again.
 */
fun <T : Any, F : Flow<State<T>>, VB : ViewBinding> BaseFragment<VB>.launchAndCollectIn(
    flow: F,
    owner: LifecycleOwner,
    onSuccess: (data: T?) -> Unit,
    onError: ((error: FileFinderError) -> Unit)? = null,
    onLoading: (() -> Unit)? = null,
    onHideLoading: (() -> Unit)? = null,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
) = owner.lifecycleScope.launch {
    owner.lifecycle.repeatOnLifecycle(minActiveState) {
        flow.collect {
            when (it) {
                is SuccessState -> {
                    handleSuccessStateOfCollection(
                        it,
                        this@launchAndCollectIn,
                        onSuccess,
                        onLoading,
                        onHideLoading
                    )
                }
                is ErrorState -> {
                    handleErrorStateOfCollection(
                        it,
                        this@launchAndCollectIn,
                        onError,
                        onHideLoading
                    )
                }
            }
        }
    }
}

/**
 * Launch and collect nullable flow result.
 * @param flow flow instance
 * @param owner lifecycle owner
 * @param onSuccess handle result data object
 * @param onError handle error data object. If not overridden, as default behaviour it will be handled from [BaseFragment] showError method.
 * @param onLoading handle loading state. If not overridden, as default behaviour it will be handled from [BaseFragment] showProgress method.
 * @param onHideLoading handle after loading state is finished. If not overridden, as default behaviour it will be handled from [BaseFragment] showProgress method.
 * @param minActiveState Lifecycle.State in which block runs in a new coroutine. That coroutine will cancel if the lifecycle falls below that state, and will restart if it's in that state again.
 */
fun <T : Any, F : Flow<State<T?>>, VB : ViewBinding> BaseFragment<VB>.launchAndCollectInNullable(
    flow: F,
    owner: LifecycleOwner,
    onSuccess: (data: T?) -> Unit,
    onError: ((error: FileFinderError) -> Unit)? = null,
    onLoading: (() -> Unit)? = null,
    onHideLoading: (() -> Unit)? = null,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
) = owner.lifecycleScope.launch {
    owner.lifecycle.repeatOnLifecycle(minActiveState) {
        flow.collect {
            when (it) {
                is SuccessState -> {
                    handleSuccessStateOfCollection(
                        it,
                        this@launchAndCollectInNullable,
                        onSuccess,
                        onLoading,
                        onHideLoading
                    )
                }
                is ErrorState -> {
                    handleErrorStateOfCollection(
                        it,
                        this@launchAndCollectInNullable,
                        onError,
                        onHideLoading
                    )
                }
            }
        }
    }
}

private fun <T, VB : ViewBinding> handleSuccessStateOfCollection(
    state: SuccessState<T>,
    baseFragment: BaseFragment<VB>,
    onSuccess: (data: T?) -> Unit,
    onLoading: (() -> Unit)? = null,
    onHideLoading: (() -> Unit)? = null,
) {
    if (state.isLoading) {
        onLoading?.run {
            invoke()
        }.isNull {
            baseFragment.showProgress(true)
        }
        return
    }
    onHideLoading?.invoke()
    onHideLoading.isNull {
        baseFragment.showProgress(false)
    }
    onSuccess(state.data)
}

private fun <T, VB : ViewBinding> handleErrorStateOfCollection(
    state: ErrorState<T>,
    baseFragment: BaseFragment<VB>,
    onError: ((error: FileFinderError) -> Unit)? = null,
    onHideLoading: (() -> Unit)? = null,
) {
    onHideLoading?.invoke()
    onHideLoading.isNull {
        baseFragment.showProgress(false)
    }
    onError?.run {
        invoke(state.error)
    }.isNull {
        baseFragment.showError(state.error)
    }
}
