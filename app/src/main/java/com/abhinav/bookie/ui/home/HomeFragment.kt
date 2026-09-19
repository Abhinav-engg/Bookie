package com.abhinav.bookie.ui.home

import android.os.Bundle
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhinav.bookie.R
import com.abhinav.bookie.data.BookRepo
import com.abhinav.bookie.data.MockBookData
import com.abhinav.bookie.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val bookRepo = BookRepo()
    private lateinit var popularBookAdapter: PopularBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(MockBookData.categories) { category ->
            val filteredBooks = if (category.name == "All") {
                bookRepo.getPopularBooks()
            } else {
                bookRepo.getPopularBooks().filter { it.category == category.name }
            }
            popularBookAdapter.updateBooks(filteredBooks)
        }

        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        binding.categoryRecyclerView.adapter = categoryAdapter

        val featuredBookAdapter = BookAdapter(bookRepo.getFeaturedBooks())
        popularBookAdapter = PopularBookAdapter(bookRepo.getPopularBooks())

        binding.featuredRecyclerView.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        binding.featuredRecyclerView.adapter = featuredBookAdapter

        binding.popularRecyclerView.adapter = popularBookAdapter

        binding.popularRecyclerView.post {
            val cardWidth = resources.getDimensionPixelSize(R.dimen.popular_book_card_min_width)
            val availableWidth = binding.popularRecyclerView.width
            var columnCount = availableWidth / cardWidth

            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                columnCount = maxOf(2, columnCount)
            } else {
                columnCount = maxOf(1, columnCount)
            }

            binding.popularRecyclerView.layoutManager =
                GridLayoutManager(requireContext(), columnCount)
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
