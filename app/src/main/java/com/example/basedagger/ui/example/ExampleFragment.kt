package com.example.basedagger.ui.example

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.basedagger.R
import com.example.basedagger.base.BaseFragment
import com.example.basedagger.data.enum.Status
import com.example.basedagger.databinding.FragmentExampleBinding
import com.example.basedagger.ui.adapter.photos.AdapterPhotos
import com.example.basedagger.utill.*
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import java.net.HttpURLConnection

class ExampleFragment:
//    Fragment() {
    BaseFragment() {
    //    val binding = FragmentExampleBinding.inflate(layoutInflater)
    private val viewModel: ExampleViewModel by  viewModels()
    private var _binding: FragmentExampleBinding? = null
    val binding get() = _binding!!
    private var skeleton: Skeleton? = null

    private val adapter = AdapterPhotos {
        requireContext().showSnackBar(binding.root, it.title, Toast_Default)
    }

    private val timerSearch = object : CountDownTimer(1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
        }

        override fun onFinish() {
            searchText()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onInitialization() {
        binding.apply {
            rcvExample.adapter = adapter
            skeleton = rcvExample.applySkeleton(R.layout.item_photos)
            skeleton?.showShimmer = true
            skeleton?.showSkeleton()
        }

    }

    override fun onObserveAction() {
        observe(viewModel.listPhotos) {
            observe(it) { list ->
                if(list.size > 0 && !(viewModel.search.value.isNullOrEmpty())) skeleton?.showOriginal() else viewModel.fetchDataPhotos()
                list.let { data -> adapter.setListPhotos(data) }
            }
        }
        observe(viewModel.dataPhotos) {
            when(it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    skeleton?.showOriginal()
                }
                else -> {
                    skeleton?.showOriginal()
                    if(adapter.itemCount <= 0) {
                        binding.blanklayout.visible()
                        binding.blanklayout.setType(it.code ?: 400, it.message)
                        binding.blanklayout.setOnClick("Retry") {
                            binding.blanklayout.gone()
                            skeleton?.showSkeleton()
                            viewModel.fetchDataPhotos()
                        }
                    }
                }
            }
        }
        binding.searchPhotos.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                timerSearch.cancel()
                searchText()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                timerSearch.cancel()
                timerSearch.start()
                return true
            }
        })
        viewModel.search.value = ""
    }

    override fun onReadyAction() {
    }

    private fun searchText() {
        binding.apply {
            rcvExample.scrollToPosition(0)
            viewModel.search.value = searchPhotos.query.toString()
            searchPhotos.clearFocus()
        }
    }
}