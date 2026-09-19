package com.abhinav.bookie.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abhinav.bookie.R
import com.abhinav.bookie.data.BookRepo
import com.abhinav.bookie.databinding.FragmentBookDetailsBinding

class BookDetailsFragment : Fragment() {

    private var _binding: FragmentBookDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = requireArguments().getInt("bookId")
        val book = BookRepo().getBooksById(bookId)

        if (book == null) {
            findNavController().navigateUp()
            return
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.bookCoverImageView.setImageResource(book.coverResId)
        binding.bookTitleTextView.text = book.title
        binding.bookAuthorTextView.text = book.author
        binding.publisherTextView.text = book.publisher
        binding.ratingTextView.text = book.rating.toString()
        binding.pageCountTextView.text = getString(R.string.pages, book.pageCount)
        binding.audioDurationTextView.text = book.audioDuration
        binding.synopsisTextView.text = book.summary
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
