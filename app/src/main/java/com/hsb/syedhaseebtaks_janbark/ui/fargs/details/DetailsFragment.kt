package com.hsb.syedhaseebtaks_janbark.ui.fargs.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hsb.syedhaseebtaks_janbark.databinding.FragmentDetailBinding
import com.hsb.syedhaseebtaks_janbark.utils.setImage

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var myTitle: String
    private lateinit var myType: String
    private lateinit var myYear: String
    private lateinit var mySrc: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setData()
    }

    private fun getData() {
        args.let {
            myTitle = it.title
            myType = it.type
            myYear = it.year
            mySrc = it.image
        }
    }

    private fun setData() {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = args.title

        with(binding) {
            imageView.setImage(mySrc)
            titletxt.text = myTitle
            typetxt.text = myType
            yeartxt.text = myYear
        }
    }
}