package com.jeff_skillrill.book_shop_application.fragments

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.adapters.BookAdapter
import com.jeff_skillrill.book_shop_application.databinding.FragmentSavedBinding
import com.jeff_skillrill.book_shop_application.model.Book

class SavedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSavedBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
        val booksJson = shared.getString("books", null)
        val books = gson.fromJson<ArrayList<Book>>(booksJson, object : TypeToken<ArrayList<Book>>() {}.type)

        binding.savedRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.savedRv.adapter =
            BookAdapter(books.filter { it.isSaved } as ArrayList<Book>,
                R.layout.book_item2,
                requireContext(),
                object : BookAdapter.MyInterface {
                    override fun onItemTap(book: Book) {
                        var bundle = bundleOf("book" to book)
                        findNavController().navigate(R.id.bookDetailFragment, bundle)
                    }
                })

        if ((books.filter { it.isSaved }).isEmpty()) {
            binding.savedRv.visibility = View.GONE
        }
        else {
            binding.savedRv.visibility = View.VISIBLE
        }

        val mainColor = ContextCompat.getColor(requireContext(), R.color.mainColor)
        val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
        binding.linearGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)

        binding.myGrid.setOnClickListener {
            binding.savedRv.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.savedRv.adapter =
                BookAdapter(books.filter { it.isSaved } as ArrayList<Book>,
                    R.layout.book_item,
                    requireContext(),
                    object : BookAdapter.MyInterface {
                        override fun onItemTap(book: Book) {
                            var bundle = bundleOf("book" to book)
                            findNavController().navigate(R.id.bookDetailFragment, bundle)
                        }
                    })

            binding.myGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)
            binding.linearGrid.setColorFilter(blackColor, PorterDuff.Mode.SRC_ATOP)
        }

        binding.linearGrid.setOnClickListener {
            binding.savedRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.savedRv.adapter =
                BookAdapter(books.filter { it.isSaved } as ArrayList<Book>,
                    R.layout.book_item2,
                    requireContext(),
                    object : BookAdapter.MyInterface {
                        override fun onItemTap(book: Book) {
                            var bundle = bundleOf("book" to book)
                            findNavController().navigate(R.id.bookDetailFragment, bundle)
                        }
                    })

            binding.linearGrid.setColorFilter(mainColor, PorterDuff.Mode.SRC_ATOP)
            binding.myGrid.setColorFilter(blackColor, PorterDuff.Mode.SRC_ATOP)
        }


        return binding.root
    }

}