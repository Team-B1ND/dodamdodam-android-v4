package kr.hs.dgsw.smartschool.dodamdodam.features.song.search

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongSearchBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.SongSearchAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.openVideoFromUrl
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class SongSearchFragment : BaseFragment<FragmentSongSearchBinding, SongSearchViewModel>(), SongSearchAdapter.Action {

    override val viewModel: SongSearchViewModel by viewModels()
    private lateinit var songSearchAdapter: SongSearchAdapter

    override fun observerViewModel() {
        initSongSearchAdapter()
        collectSearchResult()
        collectApplySong()

        bindingViewEvent { event ->
            when(event) {
                SongSearchViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }
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

    private fun collectApplySong() = lifecycleScope.launchWhenStarted {
        viewModel.applySongState.collect {
            if (it.message.isNotBlank()) {
                shortToast(it.message)
                findNavController().popBackStack()
            }

            if (it.error.isNotBlank()) {
                shortToast(it.error)
            }
        }
    }

    private fun initSongSearchAdapter() {
        songSearchAdapter = SongSearchAdapter(this)
        mBinding.rvSongSearch.adapter = songSearchAdapter
    }

    override fun onClickApply(url: String) {
        viewModel.applySong(url)
    }

    override fun onClickThumbnail(url: String) {
        this.openVideoFromUrl(url)
    }

}