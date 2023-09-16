package com.jeff_skillrill.book_shop_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.jeff_skillrill.book_shop_application.databinding.FragmentPersonalInfoBinding
import com.jeff_skillrill.book_shop_application.model.User

class PersonalInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val userJson = shared.getString("active_user", null)
        val user: User = gson.fromJson(userJson, User::class.java)

        println(user.toString())

        binding.userNameInfo.setText(user.username)
        binding.passwordInfo.setText(user.password)
        binding.emailInfo.setText(user.email)

        return binding.root
    }
}