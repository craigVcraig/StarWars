package com.cvitirinyu.codingchallenge.starwars.ui.missionslist

import android.os.Bundle
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMissionsListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = MissionAdapter()
        binding.apply {
            missionsList.adapter = adapter
            missionsList.layoutManager = LinearLayoutManager(requireContext())
            lifecycleOwner = viewLifecycleOwner
        }
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: MissionAdapter){
            viewModel.starWarsMissions.observe(viewLifecycleOwner, Observer { missions ->
                adapter.submitList(missions)
            })
    }
}