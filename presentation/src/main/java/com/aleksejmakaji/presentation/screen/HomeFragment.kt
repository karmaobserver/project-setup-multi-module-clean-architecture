package com.aleksejmakaji.presentation.screen

import android.os.Bundle
import android.view.View
import com.aleksejmakaji.domain.model.UserModel
import com.aleksejmakaji.presentation.common.BaseFragment
import com.aleksejmakaji.presentation.databinding.FragmentHomeBinding
import com.aleksejmakaji.presentation.utils.launchAndCollectIn
import com.aleksejmakaji.presentation.utils.launchAndCollectInNullable
import com.aleksejmakaji.presentation.viewmodel.UserViewModel
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val userViewModel by inject<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        observeUser()
        observeSaveUser()
    }

    //    TODO: Remove logic
    private fun initListeners() {
        binding.buttonSaveUser.setOnClickListener {
            userViewModel.saveUser(
                UserModel(
                    "Aleksej",
                    "Makaji"
                )
            )
        }
    }

    //    TODO: Remove logic
    private fun observeSaveUser() {
        launchAndCollectIn(userViewModel.saveUser, viewLifecycleOwner, {
            showSnackBar("Successfully saved user")
        })
    }

    //    TODO: Remove logic
    private fun observeUser() {
        launchAndCollectInNullable(userViewModel.getUser, viewLifecycleOwner, {
            showSnackBar(it?.firstName ?: "No user")
        })
    }
}
