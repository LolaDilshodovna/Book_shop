package com.jeff_skillrill.book_shop_application.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.databinding.FragmentAgeBinding

class AgeFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAgeBinding
    private var ageButtons = mutableListOf<MaterialButton>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgeBinding.inflate(inflater, container, false)

        ageButtons = mutableListOf(binding.first, binding.second, binding.third, binding.fourth, binding.fifth, binding.sixth, binding.seventh, binding.eights)

        binding.first.setOnClickListener(this)
        binding.second.setOnClickListener(this)
        binding.third.setOnClickListener(this)
        binding.fourth.setOnClickListener(this)
        binding.fifth.setOnClickListener(this)
        binding.sixth.setOnClickListener(this)
        binding.seventh.setOnClickListener(this)
        binding.eights.setOnClickListener(this)


        return binding.root
    }

    fun changeButtonStyle(button: View) {
        for (i in ageButtons) {
            if (i == button as MaterialButton) {
                i.setTextColor(Color.WHITE)
                i.setBackgroundColor(Color.parseColor("#FCA82E"))
            }
            else {
                i.setTextColor(Color.parseColor("#FCA82E"))
                i.setBackgroundColor(Color.WHITE)
                i.setStrokeColorResource(R.color.mainColor)
                i.setStrokeWidthResource(R.dimen.stroke_width)
            }
        }
    }

    override fun onClick(p0: View?) {
        changeButtonStyle(p0!!)
    }
}