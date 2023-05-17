package com.nazanin.harrypotter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nazanin.harrypotter.databinding.FragmentDetailsBinding
import com.nazanin.harrypotter.viewmodels.DetailViewModel
import com.nazanin.harrypotter.viewmodels.DetailViewModelFactory

class DetailsFragment : Fragment() {

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val character = DetailsFragmentArgs.fromBundle(requireArguments()).selectedItem

        binding.backIv.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.actionShowOverview())
        }

        val viewModelFactory = DetailViewModelFactory(character, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root
    }
}