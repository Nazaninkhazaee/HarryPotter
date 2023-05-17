package com.nazanin.harrypotter.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nazanin.harrypotter.R
import com.nazanin.harrypotter.adapter.CharacterAdapter
import com.nazanin.harrypotter.databinding.FragmentOverviewBinding
import com.nazanin.harrypotter.viewmodels.OverviewViewModel

class OverviewFragment : Fragment() {
    private lateinit var binding: FragmentOverviewBinding

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    private val myAdapter = CharacterAdapter(CharacterAdapter.OnClickListener {
        viewModel.displayDetails(it)
    })

    private var isSearching: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.characterRecyclerView.adapter = myAdapter

        viewModel.navigateToSelectedItems.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it))
                viewModel.displayDetailsComplete()
            }
        })

        binding.searchIv.setOnClickListener {
            searchCharacterView()
        }

        binding.searchEt.addTextChangedListener(textWatcher)

        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            myAdapter.submitList(results)
        }

        binding.moreIv.setOnClickListener { view ->
            val popup = PopupMenu(view.context, view)
            popup.menuInflater.inflate(R.menu.overflow_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.show_staff_menu -> showAllTeachers()
                    R.id.show_students_menu -> showAllStudents()
                    else -> showAllCharacters()
                }
                binding.titleTv.text = menuItem.title
                true
            }
            popup.show()
        }

        return binding.root
    }

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (binding.searchEt.length() > 0) {
                viewModel.searchByName(binding.searchEt.text.toString())
            }
        }
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun searchCharacterView(){
        if (isSearching){
            binding.searchEt.text.clear()
            binding.searchIv.setImageResource(R.drawable.ic_search)
            binding.titleTv.visibility = View.VISIBLE
            binding.searchEt.visibility = View.GONE
            showAllCharacters()
            hideKeyboard()
            isSearching = false
        }else{
            binding.searchIv.setImageResource(R.drawable.ic_back)
            binding.titleTv.visibility = View.GONE
            binding.searchEt.visibility = View.VISIBLE
            isSearching = true
        }
    }

    private fun showAllCharacters(){
        viewModel.staffModel.observe(viewLifecycleOwner, Observer {
            myAdapter.submitList(it)
        })
    }

    private fun showAllStudents(){
        viewModel.studentModel.observe(viewLifecycleOwner, Observer {
            myAdapter.submitList(it)
        })
    }

    private fun showAllTeachers(){
        viewModel.staffModel.observe(viewLifecycleOwner, Observer {
            myAdapter.submitList(it)
        })
    }


}

