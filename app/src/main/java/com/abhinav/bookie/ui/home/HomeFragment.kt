package com.abhinav.bookie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhinav.bookie.R
import com.abhinav.bookie.data.BookRepo
import com.abhinav.bookie.data.MockBookData
import com.abhinav.bookie.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val bookRepo = BookRepo()
    private lateinit var popularBookAdapter: BookAdapter

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

        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        val featuredBookAdapter = BookAdapter(bookRepo.getFeaturedBooks())
        popularBookAdapter = BookAdapter(bookRepo.getPopularBooks())

        binding.featuredRecyclerView.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        binding.featuredRecyclerView.adapter = featuredBookAdapter




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
