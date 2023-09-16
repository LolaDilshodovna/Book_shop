package com.jeff_skillrill.book_shop_application.books

import android.content.Context
import com.google.gson.Gson
import com.jeff_skillrill.book_shop_application.R
import com.jeff_skillrill.book_shop_application.model.Book

class BookApi(context: Context) {
    companion object {
        private var instance: BookApi? = null
        fun newInstance(context: Context): BookApi {
            if (instance == null) {
                instance = BookApi(context)
            }
            return instance!!
        }
    }

    private val shared = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveAllBooksToShared() {
        var books = ArrayList<Book>()

        books.add(
            Book(
                "Iron Flame",
                "Rebecca Yarros",
                640,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.7,
                "10.2 mb",
                "27000",
                R.drawable.iron_flame,
                "Thriller",
                null
            )
        )

        books.add(
            Book(
                "The Final Revival of Opal & Nev",
                "J.K. Rowling",
                237,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.9,
                "10.1 mb",
                "28500",
                R.drawable.hp2,
                "Romance",
                null
            )
        )

        books.add(
            Book(
                "An Equal Music",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.9,
                "11.2  mb",
                "85000",
                R.drawable.hp3,
                "Thriller",
                null
            )
        )

        books.add(
            Book(
                "The Brothers Hawthorne",
                "Jennifer Lynn Barnes",
                245,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.1,
                "3.4 mb",
                "10500",
                R.drawable.hp4,
                "Romance",
                null
            )
        )

        books.add(
            Book(
                "My Roommate Is a Vampire",
                "Jenna Levine ",
                234,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.2,
                "10.7 mb",
                "45000",
                R.drawable.nmadr,
                "Romance",
                null
            )
        )

        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thefault,
                "Action",
                null
            )
        )

        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thebrothers,
                "Romance",
                null
            )
        )

        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thebrothers,
                "Action",
                null
            )
        )

        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thebrothers,
                "Romance",
                null
            )
        )

        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thebrothers,
                "Action",
                null
            )
        )

        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thebrothers,
                "Romance",
                null
            )
        )

        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thebrothers,
                "Romance",
                null
            )
        )
        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thebrothers,
                "Thriller",
                null
            )
        )
        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thebrothers,
                "Thriller",
                null
            )
        )

        books.add(
            Book(
                "Yulduzlar Aybdormi",
                "J.K. Rowling",
                235,
                "\n" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi feugiat ac felis eget condimentum. Nunc fermentum velit et risus accumsan, at elementum metus luctus. Aliquam a nunc non leo placerat cursus. Sed et turpis sit amet libero volutpat luctus.",
                4.3,
                "9.33 mb",
                "31500",
                R.drawable.thebrothers,
                "Thriller",
                null
            )
        )

        val booksJson = gson.toJson(books)
        shared.edit().putString("books", booksJson).apply()
    }
}