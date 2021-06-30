package com.example.basedagger.ui.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.basedagger.base.BaseFragment
import com.example.basedagger.databinding.FragmentSplashscreenBinding
import dagger.hilt.android.AndroidEntryPoint

class SplashScreenFragment
//    : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val binding = FragmentSplashscreenBinding.inflate(inflater)
//        return binding.root
//    }
//
//    override fun onStart() {
//        super.onStart()
//        findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToExampleFragment())
//    }


    :BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSplashscreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onInitialization() {
    }

    override fun onReadyAction() {
        findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToExampleFragment())
    }
}