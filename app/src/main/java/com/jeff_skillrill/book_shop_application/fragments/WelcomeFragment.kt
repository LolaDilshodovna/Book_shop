package com.jeff_skillrill.book_shop_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding.getStartedBtn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_signUpFragment3)
        }
        binding.alreadyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_signInFragment)
        }
        return binding.root
    }
}