package com.jeff_skillrill.book_shop_application.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)


        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        var users = shared.getString("users", "")
        var isLoggedOut = shared.getBoolean("isLoggedOut", false)

        Handler(Looper.getMainLooper()).postDelayed({
            if (users == "") {
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment2)
            }
            else if (isLoggedOut) {
                findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
            }
            else {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
        }, 3000)
        return binding.root
    }
}