package com.hsb.syedhaseebtaks_janbark.ui.fargs.movies

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hsb.syedhaseebtaks_janbark.R
import com.hsb.syedhaseebtaks_janbark.data.models.Movies
import com.hsb.syedhaseebtaks_janbark.databinding.FragmentMoviesBinding
import com.hsb.syedhaseebtaks_janbark.utils.*
import com.hsb.syedhaseebtaks_janbark.utils.ads.preLoadNativeAd
import com.hsb.syedhaseebtaks_janbark.viewModel.MyViewModel
import org.koin.android.ext.android.inject

class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private val myViewModel: MyViewModel by inject()
    private lateinit var moviesAdapter: MoviesAdapter
    private var moviesList = ArrayList<Movies>()
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            context?.let {
                moviesAdapter = MoviesAdapter(it, moviesList)
            }
            loadAd()

            //Call Api
            activity?.let {
                sharedPreferences = it.getSharedPreferences(veryPrivateName, MODE_PRIVATE)
                val callApi = sharedPreferences.getBoolean(isFirstTime, true)
                if (callApi) {
                    context?.toast("FirstTime")
                    myViewModel.saveMovies()
                    val edit = sharedPreferences.edit()
                    edit.putBoolean(isFirstTime, false)
                    edit.apply()
                }

                myViewModel.getMovies().observe(it) { list ->
                    moviesList.clear()
                    var c = 0
                    list.forEach {
                        if (c % 2 == 0 && c != 0 && context?.isNetworkConnected() == true) {
                            val ad = Movies(0, ViewHolder_Ad, "", "", "", "", "")
                            moviesList.add(ad)
                        }
                        c++
                        moviesList.add(it)
                    }
                    moviesAdapter.update(moviesList)
                }
            }

            moviesRv.layoutManager = context?.vertical()
            moviesRv.hasFixedSize()
            moviesRv.adapter = moviesAdapter
            adapterClick()
        }
    }

    private fun loadAd() {
        activity?.preLoadNativeAd(getString(R.string.admob_native_ad), {
            moviesAdapter.setNative(it)
        })
    }

    private fun adapterClick() {
        with(moviesAdapter) {
            itemClick = {
                val title = moviesList[it].Title
                val image = moviesList[it].Poster
                val type = moviesList[it].Type
                val year = moviesList[it].Year
                val bundle = bundleOf(
                    "title" to title,
                    "image" to image,
                    "type" to type,
                    "year" to year,
                )
                activity?.showInterstitial {
                    findNavController().navigate(
                        R.id.action_moviesNavigation_to_detailsNavigation,
                        bundle
                    )
                }
            }
            updateClick = { movie ->
                activity?.updateDialogue(movie) { updated ->
                    context?.toast("${updated.Title} will be saved")
                    myViewModel.update(updated)
                }
            }
            deleteClick = { movie ->
                context?.toast("${movie.Title} will be deleted")
                myViewModel.deleteMovie(movie)
            }
        }
    }
}