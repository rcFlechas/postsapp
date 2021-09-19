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
import com.example.postsapp.databinding.FragmentPostBinding
import com.example.postsapp.utilities.UIState
import com.example.postsapp.viewmodels.PostViewModel
import com.example.postsapp.views.adapters.PostAdapter
import com.example.postsapp.views.binds.UserBind
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding

    private val postViewModel: PostViewModel by viewModel()
    private lateinit var  postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getParcelable<UserBind>(UserBind.TAG)?.let {
            postViewModel.userBind = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        setupCardView()
        setupHandler()
    }

    override fun onResume() {
        super.onResume()

        postViewModel.userBind?.let { user ->
            postViewModel.getByUser(user.id)
        }
    }

    private fun initUI() {

        postAdapter = PostAdapter ()
        postAdapter.setHasStableIds(true)
        binding?.postRecyclerView?.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = postAdapter
        }
    }

    private fun setupCardView() {
        postViewModel.userBind?.let {
            binding?.name?.text = it.name
            binding?.phone?.text = it.phone
            binding?.email?.text = it.email
        }
    }

    private fun setupHandler() {

        postViewModel.getByUser.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is UIState.OnLoading -> {
                    isLoading(uiState.loading)
                }
                is UIState.OnSuccess -> {

                    isLoading(false)
                    val data = uiState.data
                    postAdapter.clearData()
                    if (data.isNotEmpty()) {

                        dataNoEmpty()
                        postAdapter.setData(data)
                    } else {
                        dataEmpty(getString(R.string.message_list_empty))
                    }
                }
                is UIState.OnError -> {

                    isLoading(false)
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
        binding?.postRecyclerView?.visibility = View.GONE
        binding?.includeEmptyView?.root?.visibility = View.VISIBLE
        binding?.includeEmptyView?.emptyTextView?.text = text
    }

    private fun dataNoEmpty() {
        binding?.postRecyclerView?.visibility = View.VISIBLE
        binding?.includeEmptyView?.root?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        postViewModel.closeSubscriptions()
    }

    companion object {
        const val TAG = "PostFragment"

        fun newInstance(bundle: Bundle? = null): PostFragment = PostFragment().apply { arguments = bundle }
    }
}