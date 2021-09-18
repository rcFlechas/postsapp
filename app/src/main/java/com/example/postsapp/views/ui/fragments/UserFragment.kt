package com.example.postsapp.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.R
import com.example.postsapp.core.observeEvent
import com.example.postsapp.databinding.FragmentUserBinding
import com.example.postsapp.utilities.UIState
import com.example.postsapp.viewmodels.UserViewModel
import com.example.postsapp.views.adapters.UserAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding

    private val userViewModel: UserViewModel by viewModel()
    private lateinit var  userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        setupHandler()
    }

    override fun onResume() {
        super.onResume()
        userViewModel.getAll()
    }

    private fun initUI() {

        userAdapter = UserAdapter ( clickClosure = {

            //val bundle = bundleOf("movieWithCartBind" to it)
            //findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragmentDialog, bundle)
        })

        userAdapter.setHasStableIds(true)
        binding?.userRecyclerView?.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    private fun setupHandler() {
        userViewModel.getAll.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is UIState.OnLoading -> {
                    isLoading(uiState.loading)
                }
                is UIState.OnSuccess -> {
                    val data = uiState.data
                    userAdapter.clearData()
                    if (data.isNotEmpty()) {
                        dataNoEmpty()
                        userAdapter.setData(data)
                    } else {
                        dataEmpty(getString(R.string.message_list_empty))
                    }
                }
                is UIState.OnError -> {
                    dataEmpty(uiState.error)
                }
            }
        }
    }

    private fun isLoading(loading: Boolean) {
        binding?.progressBar?.visibility = if (loading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun dataEmpty(text: String = String()) {
        binding?.nestedScrollView?.visibility = View.GONE
        binding?.includeEmptyView?.root?.visibility = View.VISIBLE
        binding?.includeEmptyView?.emptyTextView?.text = text
    }

    private fun dataNoEmpty() {
        binding?.nestedScrollView?.visibility = View.VISIBLE
        binding?.includeEmptyView?.root?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "UserFragment"

        fun newInstance(bundle: Bundle? = null): UserFragment = UserFragment().apply { arguments = bundle }
    }
}