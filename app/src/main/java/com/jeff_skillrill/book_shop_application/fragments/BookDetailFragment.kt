package com.jeff_skillrill.book_shop_application.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.databinding.FragmentBookDetailBinding
import com.jeff_skillrill.book_shop_application.model.Book

class BookDetailFragment : Fragment() {
    lateinit var binding: FragmentBookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
        val booksJson = shared.getString("books", null)
        val books = gson.fromJson<ArrayList<Book>>(booksJson, object : TypeToken<ArrayList<Book>>() {}.type)

        var book = arguments?.getSerializable("book") as Book

        var updatableBooks = books

        if (book.isSaved) {
            binding.saved.setImageResource(R.drawable.saved_selected)
        }
        else {
            binding.saved.setImageResource(R.drawable.saved)
        }

        if (book.isWish) {
            binding.wishlist.setImageResource(R.drawable.star_selected)
        }
        else {
            binding.wishlist.setImageResource(R.drawable.star)
        }

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.bookImage.setImageResource(book.img)
        binding.mb.text = book.size
        binding.pageCount.text = book.pages.toString()

        binding.saved.setOnClickListener {
            changeBookStatus(true, updatableBooks, book)
            val newBooksJson = gson.toJson(updatableBooks)
            shared.edit().putString("books", newBooksJson).apply()
            updatableBooks = gson.fromJson(newBooksJson, object : TypeToken<ArrayList<Book>>() {}.type)
        }

        binding.wishlist.setOnClickListener {
            changeBookStatus(false, updatableBooks, book)
            val newBooksJson = gson.toJson(updatableBooks)
            shared.edit().putString("books", newBooksJson).apply()
            updatableBooks = gson.fromJson(newBooksJson, object : TypeToken<ArrayList<Book>>() {}.type)
        }

        binding.share.setOnClickListener {
            showBottomSheet()
        }

        return binding.root
    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.share_bottom_sheet, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(bottomSheetView)
        dialog.show()
    }

    private fun changeBookStatus(forSaved: Boolean, updatableBooks: ArrayList<Book>, book: Book) {
        for (i in updatableBooks) {
            if (i == book) {
                if (forSaved) {
                    if (i.isSaved) {
                        binding.saved.setImageResource(R.drawable.saved)
                        i.isSaved = false
                        book.isSaved = false
                    }
                    else {
                        binding.saved.setImageResource(R.drawable.saved_selected)
                        i.isSaved = true
                        book.isSaved = true
                        Toast.makeText(requireContext(), "Added to saved", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    if (i.isWish) {
                        binding.wishlist.setImageResource(R.drawable.star)
                        i.isWish = false
                        book.isWish = false
                    }
                    else {
                        binding.wishlist.setImageResource(R.drawable.star_selected)
                        i.isWish = true
                        book.isWish = true
                        Toast.makeText(requireContext(), "Added to wishlist", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}