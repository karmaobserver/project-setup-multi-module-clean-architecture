package com.aleksejmakaji.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aleksejmakaji.domain.error.FileFinderError
import com.aleksejmakaji.presentation.MainActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

/**
 * Common logic for all Fragments
 */
abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    private val mainActivity
        get() = activity as MainActivity?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showProgress(showProgress: Boolean) {
        mainActivity?.showProgress(showProgress)
    }

    fun showError(error: FileFinderError) {
        mainActivity?.showError(error)
    }

    fun showSnackBar(message: String, view: View? = null, length: Int? = null) {
        mainActivity?.showSnackBar(message, view, length)
    }
}
