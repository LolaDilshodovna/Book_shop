package com.jeff_skillrill.book_shop_application.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jeff_skillrill.book_shop_application.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {
    lateinit var shared: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilterBinding.inflate(inflater, container, false)

        shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)

        var checkedButtonText = shared.getString("radioCheckedText", "")
        var romance = shared.getBoolean("romance", false)
        var thriller = shared.getBoolean("thriller", false)
        var action = shared.getBoolean("action", false)

        if (romance) binding.romance.isChecked = true
        if (thriller) binding.thriller.isChecked = true
        if (action) binding.action.isChecked = true

        if (checkedButtonText != "") {
            for (i in 0 until binding.myRadioGr.childCount) {
                if (binding.myRadioGr.getChildAt(i) is RadioButton) {
                    if ((binding.myRadioGr.getChildAt(i) as RadioButton).text == checkedButtonText) {
                        (binding.myRadioGr.getChildAt(i) as RadioButton).isChecked = true
                        break
                    }
                }
            }
        }

        binding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.resetBtn.setOnClickListener {
            if (binding.myRadioGr.checkedRadioButtonId != -1) {
                for (i in 0 until binding.myRadioGr.childCount) {
                    if (binding.myRadioGr.getChildAt(i) is RadioButton) {
                        (binding.myRadioGr.getChildAt(i) as RadioButton).isChecked = false
                    }
                }
            }

            shared.edit().putBoolean("isFilter", false).apply()
            shared.edit().putString("radioCheckedText", null).apply()
            shared.edit().putBoolean("romance", false).apply()
            shared.edit().putBoolean("thriller", false).apply()
            shared.edit().putBoolean("action", false).apply()

            Toast.makeText(requireContext(), "Filter reseted!", Toast.LENGTH_SHORT).show()

            findNavController().popBackStack()
        }

        binding.applyBtn.setOnClickListener {
            var checkedRadioButtonId = binding.myRadioGr.checkedRadioButtonId

            if (checkedRadioButtonId != -1) {
                shared.edit().putBoolean("isFilter", true).apply()
                var radioButton: RadioButton = binding.root.findViewById(checkedRadioButtonId)
                shared.edit().putString("radioCheckedText", radioButton.text.toString()).apply()
            }

            changeStatus(binding.romance.isChecked, "romance")
            changeStatus(binding.thriller.isChecked, "thriller")
            changeStatus(binding.action.isChecked, "action")

            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun changeStatus(isChecked: Boolean, name: String) {
        if (isChecked) {
            shared.edit().putBoolean(name, true).apply()
        }
        else {
            shared.edit().putBoolean(name, false).apply()
        }
    }
}