package com.abhinav.bookie.ui.favorites

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.abhinav.bookie.R
import com.abhinav.bookie.data.BookRepo
import com.abhinav.bookie.databinding.FragmentFavoritesBinding
import com.abhinav.bookie.ui.home.PopularBookAdapter

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val bookRepo = BookRepo()
    private lateinit var favoriteBookAdapter: PopularBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteBookAdapter = PopularBookAdapter(
            books = bookRepo.getFavoriteBooks(),
            onBookClick = { openBookDetails(it.id) },
            onFavoriteChanged = { updateFavorites() }
        )
        binding.favoritesRecyclerView.adapter = favoriteBookAdapter
        binding.favoritesRecyclerView.post {
            val cardWidth = resources.getDimensionPixelSize(R.dimen.popular_book_card_min_width)
            val columnCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                maxOf(2, binding.favoritesRecyclerView.width / cardWidth)
            } else {
                maxOf(1, binding.favoritesRecyclerView.width / cardWidth)
            }
            binding.favoritesRecyclerView.layoutManager =
                GridLayoutManager(requireContext(), columnCount)
        }
    }

    override fun onResume() {
        super.onResume()
        if (this::favoriteBookAdapter.isInitialized) updateFavorites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateFavorites() {
        val favorites = bookRepo.getFavoriteBooks()
        favoriteBookAdapter.updateBooks(favorites)
        binding.emptyFavoritesText.isVisible = favorites.isEmpty()
        binding.favoritesRecyclerView.isVisible = favorites.isNotEmpty()
    }

    private fun openBookDetails(bookId: Int) {
        findNavController().navigate(
            R.id.action_favoritesFragment_to_bookDetailsFragment,
            Bundle().apply { putInt("bookId", bookId) }
        )
    }
}
