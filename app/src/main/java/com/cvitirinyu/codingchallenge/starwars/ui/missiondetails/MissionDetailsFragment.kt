package com.cvitirinyu.codingchallenge.starwars.ui.missiondetails

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.cvitirinyu.codingchallenge.starwars.R
import com.cvitirinyu.codingchallenge.starwars.databinding.FragmentMissionDetailsBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MissionDetailsFragment: Fragment() {

    private lateinit var viewModel: MissionDetailsViewModel
    private val navArgs: MissionDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentMissionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionDetailsBinding.inflate(inflater, container, false)
        viewModel = getViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.missionDetailsToolbar.inflateMenu(R.menu.menu_mission_details)

        binding.missionDetailsToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.missionDetailsToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.getMission(navArgs.missionId).observe(viewLifecycleOwner, Observer { mission ->
            binding.mission = mission
        })
    }
}