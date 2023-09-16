package com.jeff_skillrill.book_shop_application.fragments

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.adapters.BookAdapter
import com.jeff_skillrill.book_shop_application.databinding.FragmentGenreBooksBinding
import com.jeff_skillrill.book_shop_application.model.Book
import com.jeff_skillrill.book_shop_application.model.Genre

class GenreBooksFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGenreBooksBinding.inflate(inflater, container, false)

        fun getGenres() : ArrayList<Genre> {
            var genres = ArrayList<Genre>()
            genres.add(Genre("Romance", R.drawable.heart_image_romance))
            genres.add(Genre("Thriller", R.drawable.thriller))
            genres.add(Genre("Action", R.drawable.action))
            return genres
        }

        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val booksJson = shared.getString("books", null)
        val gson = Gson()
        val books = gson.fromJson<ArrayList<Book>>(booksJson, object : TypeToken<ArrayList<Book>>() {}.type)

        var index = arguments?.getInt("index") ?: 0

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.genreName.text = getGenres()[index].name

        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.adapter = BookAdapter(books.filter { it.genreName == getGenres()[index].name } as ArrayList<Book>, R.layout.book_item, requireContext())

        val mainColor = ContextCompat.getColor(requireContext(), R.color.mainColor)
        val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
        binding.myGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)

        binding.myGrid.setOnClickListener {
            binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rv.adapter = BookAdapter(books.filter { it.genreName == getGenres()[index].name } as ArrayList<Book>, R.layout.book_item, requireContext())

            binding.myGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)
            binding.linearGrid.setColorFilter(blackColor, PorterDuff.Mode.SRC_ATOP)
        }

        binding.linearGrid.setOnClickListener {
            binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rv.adapter = BookAdapter(books.filter { it.genreName == getGenres()[index].name } as ArrayList<Book>, R.layout.book_item2, requireContext())

            binding.linearGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)
            binding.myGrid.setColorFilter(blackColor, PorterDuff.Mode.SRC_ATOP)
        }

        if ((books.filter { it.genreName == getGenres()[index].name } as ArrayList<Book>).isEmpty()) {
            binding.notFound.visibility = View.VISIBLE
            binding.rv.visibility = View.GONE
        } else {
            binding.rv.visibility = View.VISIBLE
            binding.notFound.visibility = View.GONE
        }


        return binding.root
    }
}