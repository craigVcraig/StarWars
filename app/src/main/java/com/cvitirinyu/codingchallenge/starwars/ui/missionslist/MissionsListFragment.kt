package com.cvitirinyu.codingchallenge.starwars.ui.missionslist

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.cvitirinyu.codingchallenge.starwars.adapters.MissionAdapter
import com.cvitirinyu.codingchallenge.starwars.databinding.FragmentMissionsListBinding

class MissionsListFragment : Fragment() {

    private val viewModel: MissionsListViewModel by viewModel()
    private lateinit var binding: FragmentMissionsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionsListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = MissionAdapter()
        binding.apply {
            missionsList.adapter = adapter
        //    missionsList.layoutManager = LinearLayoutManager(requireContext())
            lifecycleOwner = viewLifecycleOwner
        }

//     //   val displayMetrics = DisplayMetrics()
//        val width = requireActivity().window.attributes.width
//        android.util.Log.v("AAAAA", width.toString())
////        val displayMetriscs = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
////            requireActivity().display
////        } else {
////            TODO("VERSION.SDK_INT < R")
////        }


        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: MissionAdapter) {
        viewModel.starWarsMissions.observe(viewLifecycleOwner, Observer { missions ->
            adapter.submitList(missions)
            binding.missionListProgressbar.visibility = View.INVISIBLE
        })
    }
}