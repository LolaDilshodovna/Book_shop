package com.jeff_skillrill.book_shop_application.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.databinding.FragmentAccountBinding
import com.jeff_skillrill.book_shop_application.model.User

class AccountFragment : Fragment() {
    lateinit var shared: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAccountBinding.inflate(inflater, container, false)

        shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val userJson = shared.getString("active_user", null)
        val user: User = gson.fromJson(userJson, User::class.java)

        binding.personName.text = user.username
        binding.personEmail.text = user.email

        binding.logOutBtn.setOnClickListener {
            showBottomSheet()
        }

        binding.personalInfo.setOnClickListener {
            findNavController().navigate(R.id.personalInfoFragment)
        }

        return binding.root
    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.logout_bottom_sheet, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(bottomSheetView)

        val cancelButton: MaterialButton = bottomSheetView.findViewById(R.id.cancel_button)
        val logoutButton: MaterialButton = bottomSheetView.findViewById(R.id.logout_button)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        logoutButton.setOnClickListener {
            shared.edit().putBoolean("isLoggedOut", true).apply()

            findNavController().popBackStack(R.id.mainFragment, true)
            findNavController().navigate(R.id.welcomeFragment)
            dialog.dismiss()
        }

        dialog.show()
    }

}