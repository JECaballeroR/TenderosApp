package com.slabcode.agutierrezt.example2.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.slabcode.agutierrezt.example2.R
import com.slabcode.agutierrezt.example2.ui.adapters.CommentAdapter
import com.slabcode.agutierrezt.example2.ui.listeners.OnCommentListener
import com.slabcode.agutierrezt.example2.data.models.Comment
import com.slabcode.agutierrezt.example2.databinding.FragmentCommentBinding
import com.slabcode.agutierrezt.example2.ui.viewmodels.CommentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentFragment : Fragment() {

    private var _binding: FragmentCommentBinding? = null

    private val binding get() = _binding!!

    private val commentViewModel: CommentViewModel by viewModel()

    private lateinit var commentAdapter: CommentAdapter
    private lateinit var commentManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_comment, container, false)
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.commentRefresh.setOnRefreshListener {
            commentViewModel.loadComments()
        }

        binding.commentRefresh.setColorSchemeColors(requireContext().getColor(R.color.purple_500),
            requireContext().getColor(R.color.teal_700), requireContext().getColor(R.color.black))

        commentManager = LinearLayoutManager(requireContext())
        commentAdapter = CommentAdapter(
            listOf()
        )
        commentAdapter.listener = object : OnCommentListener {
            override fun onClick(comment: Comment) {
                Log.d("CLICK",comment.name!!)
            }

        }
        commentViewModel.loadComments()
        binding.commentRecycler.apply {
            adapter = commentAdapter
            layoutManager = commentManager
        }

        commentViewModel.comments.observe(viewLifecycleOwner, Observer { comments ->
            binding.commentRefresh.isRefreshing = false
            commentAdapter.newDataset(comments)
        })

    }



}