package com.abhinav.bookie.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.bookie.R
import com.abhinav.bookie.data.Book

class PopularBookAdapter(
    private var books: List<Book>
) : RecyclerView.Adapter<PopularBookAdapter.PopularBookViewHolder>() {

    class PopularBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookCoverImageView: ImageView = itemView.findViewById(R.id.bookCoverImageView)
        val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
        val bookTitleTextView: TextView = itemView.findViewById(R.id.bookTitleTextView)
        val bookAuthorTextView: TextView = itemView.findViewById(R.id.bookAuthorTextView)
        val pageCountTextView: TextView = itemView.findViewById(R.id.pageCountTextView)
        val bookmarkImageView: ImageView = itemView.findViewById(R.id.bookmarkImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularBookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_book_card, parent, false)
        return PopularBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularBookViewHolder, position: Int) {
        val book = books[position]

        holder.bookCoverImageView.setImageResource(book.coverResId)
        holder.categoryTextView.text = book.category
        holder.ratingTextView.text = book.rating.toString()
        holder.bookTitleTextView.text = book.title
        holder.bookAuthorTextView.text = book.author
        holder.pageCountTextView.text = "${book.pageCount} pages"

        if (book.isFavorite) {
            holder.bookmarkImageView.setImageResource(R.drawable.ic_fav_selected)
        } else {
            holder.bookmarkImageView.setImageResource(R.drawable.ic_fav)
        }

        holder.bookmarkImageView.setOnClickListener {
            book.isFavorite = !book.isFavorite
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun updateBooks(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
