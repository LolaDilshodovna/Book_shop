package com.jeff_skillrill.book_shop_application.fragments

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.adapters.BookAdapter
import com.jeff_skillrill.book_shop_application.adapters.GenreAdapter
import com.jeff_skillrill.book_shop_application.books.BookApi
import com.jeff_skillrill.book_shop_application.databinding.FragmentHomeBinding
import com.jeff_skillrill.book_shop_application.model.Book
import com.jeff_skillrill.book_shop_application.model.Genre

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var books: ArrayList<Book>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()

        if (shared.getString("books", null) == null) {
            BookApi(requireContext()).saveAllBooksToShared()
        }

        var booksJson = shared.getString("books", null)
        books = gson.fromJson(booksJson, object : TypeToken<ArrayList<Book>>() {}.type)

        setGenresUi()
        setMainDefaultRvUI()

        val mainColor = ContextCompat.getColor(requireContext(), R.color.mainColor)
        val blackColor = ContextCompat.getColor(requireContext(), R.color.black)

        changeSavedBooksVisibility()
        changeWishBooksVisibility()

        binding.savedRecycler.adapter = BookAdapter(books.filter { it.isSaved } as ArrayList<Book>, R.layout.book_item, requireContext())
        binding.savedRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.wishlistRecycler.adapter = BookAdapter(books.filter { it.isWish } as ArrayList<Book>, R.layout.book_item, requireContext())
        binding.wishlistRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.editText.addTextChangedListener {
            if (binding.editText.text.toString().isNotEmpty()) {
                binding.others.visibility = View.GONE
                var filterBooks: ArrayList<Book> = ArrayList()
                for (i in books) {
                    if (i.name.lowercase().trim()
                            .contains(binding.editText.text.toString().lowercase().trim())
                    ) {
                        filterBooks.add(i)
                    }
                }

                binding.mainRecycler.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                changeListOfMainRvAdapterUI(filterBooks, R.layout.book_item2)
            } else {
                setMainDefaultRvUI()
                binding.others.visibility = View.VISIBLE
            }
        }

        binding.editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.editText.clearFocus()
            }
            false
        }



        var checkedString = shared.getString("radioCheckedText", null)
        var isFilter = shared.getBoolean("isFilter", false)

        var romance = shared.getBoolean("romance", false)
        var thriller = shared.getBoolean("thriller", false)
        var action = shared.getBoolean("action", false)


        if (isFilter) {
            var filterBooks = books
            if (checkedString == "4.5+") filterBooks = books.filter { it.rating >= 4.5 } as ArrayList<Book>
            if (checkedString == "4.0+") filterBooks = books.filter { it.rating >= 4.0 } as ArrayList<Book>
            if (romance || thriller || action) {
                if (!romance) filterBooks.removeAll { it.genreName == "Romance" }
                if (!thriller) filterBooks.removeAll { it.genreName == "Thriller" }
                if (!action) filterBooks.removeAll { it.genreName == "Action" }
            }

            binding.mainRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            changeListOfMainRvAdapterUI(filterBooks, R.layout.book_item2)
            binding.others.visibility = View.GONE
            Toast.makeText(requireContext(), "Filter results!", Toast.LENGTH_SHORT).show()
        } else {
            setMainDefaultRvUI()
            binding.others.visibility = View.VISIBLE
        }

        return binding.root
    }

    private fun getGenres(): ArrayList<Genre> {
        var genres = ArrayList<Genre>()
        genres.add(Genre("Romance", R.drawable.heart_image_romance))
        genres.add(Genre("Thriller", R.drawable.thriller))
        genres.add(Genre("Action", R.drawable.action))
        return genres
    }

    private fun setGenresUi() {
        binding.genreRecycler.adapter =
            GenreAdapter(getGenres(), object : GenreAdapter.MyInterface {
                override fun onItemTap(index: Int) {
                    val bundle = bundleOf("index" to index)
                    findNavController().navigate(R.id.genreBooksFragment, bundle)
                }
            })
        binding.genreRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setMainDefaultRvUI() {
        binding.mainRecycler.adapter =
            BookAdapter(books, R.layout.book_item, requireContext(), object : BookAdapter.MyInterface {
                override fun onItemTap(book: Book) {
                    var bundle = bundleOf("book" to book)
                    findNavController().navigate(R.id.bookDetailFragment, bundle)
                }
            })
        binding.mainRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun changeListOfMainRvAdapterUI(newList: ArrayList<Book>, newLayout: Int) {
        binding.mainRecycler.adapter =
            BookAdapter(newList, newLayout, requireContext(), object : BookAdapter.MyInterface {
                override fun onItemTap(book: Book) {
                    var bundle = bundleOf("book" to book)
                    findNavController().navigate(R.id.bookDetailFragment, bundle)
                }
            })
    }

    private fun changeSavedBooksVisibility() {
        if ((books.filter { it.isSaved } as ArrayList<Book>).isEmpty()) {
            binding.savedRecycler.visibility = View.GONE
        } else {
            binding.savedRecycler.visibility = View.VISIBLE
        }
    }

    fun changeWishBooksVisibility() {
        if ((books.filter { it.isWish } as ArrayList<Book>).isEmpty()) {
            binding.wishlistRecycler.visibility = View.GONE
        } else {
            binding.wishlistRecycler.visibility = View.VISIBLE
        }
    }
}