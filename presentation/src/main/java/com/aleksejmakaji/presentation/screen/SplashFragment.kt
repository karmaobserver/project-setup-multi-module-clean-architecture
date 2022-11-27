package com.aleksejmakaji.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aleksejmakaji.presentation.R
import com.aleksejmakaji.presentation.common.BaseFragment
import com.aleksejmakaji.presentation.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToSplashFragment()
    }

    private fun navigateToSplashFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }
}
