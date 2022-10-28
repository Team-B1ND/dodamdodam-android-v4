package kr.hs.dgsw.smartschool.dodamdodam.features.song.search

import android.view.KeyEvent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongSearchBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.SongSearchAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class SongSearchFragment : BaseFragment<FragmentSongSearchBinding, SongSearchViewModel>() {

    override val viewModel: SongSearchViewModel by viewModels()
    private lateinit var songSearchAdapter: SongSearchAdapter

    override fun observerViewModel() {
        initSongSearchAdapter()
        collectSearchResult()

        bindingViewEvent { event ->
            when(event) {
                SongSearchViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }

//        mBinding.etSearch.setOnKeyListener { _, keyCode, _ ->
//            if (keyCode == KeyEvent.KEYCODE_ENTER)
//                viewModel.getYoutubeResult()
//            true
//        }
    }

    private fun collectSearchResult() = lifecycleScope.launchWhenStarted {
        viewModel.getYoutubeVideoState.collect {
            it.youtubeVideo?.let { youtubeVideo ->
                songSearchAdapter.submitList(youtubeVideo.items)
            }

            if (it.error.isNotBlank()) {
                shortToast(it.error)
            }
        }
    }

    private fun initSongSearchAdapter() {
        songSearchAdapter = SongSearchAdapter()
        mBinding.rvSongSearch.adapter = songSearchAdapter
    }

}