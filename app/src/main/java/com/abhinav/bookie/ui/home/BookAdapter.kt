package com.abhinav.bookie.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.bookie.R
import com.abhinav.bookie.data.Book

class BookAdapter(
    private var books: List<Book>
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookCoverImageView: ImageView = itemView.findViewById(R.id.bookCoverImageView)
        val bookTitleTextView: TextView = itemView.findViewById(R.id.bookTitleTextView)
        val bookAuthorTextView: TextView = itemView.findViewById(R.id.bookAuthorTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_card, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.bookCoverImageView.setImageResource(book.coverResId)
        holder.bookTitleTextView.text = book.title
        holder.bookAuthorTextView.text = book.author
        holder.ratingTextView.text = book.rating.toString()
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun updateBooks(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
