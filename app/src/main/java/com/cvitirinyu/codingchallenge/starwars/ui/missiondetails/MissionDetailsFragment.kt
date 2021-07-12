package com.cvitirinyu.codingchallenge.starwars.ui.missiondetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.cvitirinyu.codingchallenge.starwars.MainActivity
import com.cvitirinyu.codingchallenge.starwars.R
import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission
import com.cvitirinyu.codingchallenge.starwars.databinding.FragmentMissionDetailsBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MissionDetailsFragment : Fragment() {

    private lateinit var missionDetails: StarWarsMission
    private lateinit var viewModel: MissionDetailsViewModel
    private lateinit var binding: FragmentMissionDetailsBinding
    private val navArgs: MissionDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionDetailsBinding.inflate(inflater, container, false)
        viewModel = getViewModel()
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        setUpActionBarMenu()
    }

    private fun subscribeUi() {
        viewModel.getMission(navArgs.missionId).observe(viewLifecycleOwner, Observer { mission ->
            binding.mission = mission
            missionDetails = mission
            binding.missionDetailsProgressbar.visibility = View.INVISIBLE
        })
    }

    private fun setUpActionBarMenu() {

        binding.missionDetailsToolbar.inflateMenu(R.menu.menu_mission_details)

        /* adding back icon */
        binding.missionDetailsToolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }

        /* responding to menu click events */
        binding.missionDetailsToolbar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.menu_item_call -> {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        val number = missionDetails.phone ?: ""
                        data = Uri.parse("tel: $number")
                    }
                    startActivity(intent)
                    true
                }
                R.id.menu_item_share -> {
                    shareMission()
                    true
                }
                else -> false
            }
        }
    }


    private fun shareMission() {

//        val imgBitmap = binding.missionDetailsToolbarImg.drawable.toBitmap()
//        var imageUri: Uri? = null
//        try {
//            val file = File(Environment.getStorageDirectory(), "share_img" + System.currentTimeMillis() + ".png")
//            val out = FileOutputStream(file)
//            imgBitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
//            out.close()
//            imageUri = Uri.fromFile(file)
//        } catch (exception: Exception) {
//            android.util.Log.e("Error:", exception.message.toString())
//        }

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/*"
            data = Uri.parse("mailto:")
            data = Uri.parse("smsto:")
            //  putExtra(Intent.EXTRA_STREAM, imageUri)
            putExtra(Intent.EXTRA_TITLE, missionDetails.title)
            putExtra(Intent.EXTRA_TEXT, missionDetails.description)
            putExtra(Intent.EXTRA_TEXT, missionDetails.date)
            putExtra(Intent.EXTRA_TEXT, missionDetails.locationLineOne)
            putExtra(Intent.EXTRA_TEXT, missionDetails.locationLineTwo)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        startActivity(Intent.createChooser(shareIntent, "share mission"))
    }
}