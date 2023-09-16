package com.jeff_skillrill.book_shop_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.databinding.FragmentSignInBinding
import com.jeff_skillrill.book_shop_application.model.User

class SignInFragment : Fragment() {
    private var userList = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignInBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val convert = object : TypeToken<List<User>>() {}.type
        val users = shared.getString("users", "")

        binding.signInBtn.setOnClickListener {
            if (users != "") {
                userList = gson.fromJson(users, convert)
            }

            for (user in userList) {
                if ((binding.usernameOrEmail.text.toString() == user.username || binding.usernameOrEmail.text.toString() == user.email) && binding.password.text.toString() == user.password) {
                    Toast.makeText(requireContext(), "Successfully logged in", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_signInFragment_to_mainFragment)

                    shared.edit().putBoolean("isLoggedOut", false).apply()
                    shared.edit().putString("active_user", gson.toJson(user)).apply()

                    return@setOnClickListener
                }
            }

            Toast.makeText(
                requireContext(),
                "Username or password is incorrect",
                Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }
}