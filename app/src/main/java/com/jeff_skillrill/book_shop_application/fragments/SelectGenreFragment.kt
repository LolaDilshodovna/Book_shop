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
import com.jeff_skillrill.book_shop_application.databinding.FragmentSelectGenreBinding

class SelectGenreFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSelectGenreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectGenreBinding.inflate(inflater, container, false)

        binding.romance.setOnClickListener(this)
        binding.fantasy.setOnClickListener(this)
        binding.scientific.setOnClickListener(this)
        binding.comedy.setOnClickListener(this)
        binding.thriller.setOnClickListener(this)
        binding.adventure.setOnClickListener(this)
        binding.childrens.setOnClickListener(this)
        binding.biography.setOnClickListener(this)
        binding.travel.setOnClickListener(this)


        return binding.root
    }

    private fun changeButtonStyle(button: View) {
        if ((button as MaterialButton).currentTextColor != Color.WHITE) {
            button.setTextColor(Color.WHITE)
            button.setBackgroundColor(Color.parseColor("#FCA82E"))
        }
        else {
            button.setTextColor(Color.parseColor("#FCA82E"))
            button.setBackgroundColor(Color.WHITE)
            button.setStrokeColorResource(R.color.mainColor)
            button.setStrokeWidthResource(R.dimen.stroke_width)
        }
    }

    override fun onClick(p0: View?) {
        changeButtonStyle(p0!!)
    }
}