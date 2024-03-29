package kr.hs.dgsw.smartschool.dodamdodam.features.song.apply

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongApplyBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.RecommendSongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.song.melon.SongChart

@AndroidEntryPoint
class SongApplyFragment : BaseFragment<FragmentSongApplyBinding, SongApplyViewModel>() {

    override val viewModel: SongApplyViewModel by viewModels()

    lateinit var recommendSongAdapter: RecommendSongAdapter

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                SongApplyViewModel.EVENT_ON_CLICK_BACK -> popBackStack()
                SongApplyViewModel.EVENT_ON_URL_ERROR -> shortToast(viewModel.errorMessage)
                SongApplyViewModel.EVENT_ON_CLICK_SEARCH -> findNavController().navigate(R.id.action_songApplyFragment_to_songSearchFragment)
                SongApplyViewModel.EVENT_ON_SUCCESS_APPLY -> successApplySong()
            }
        }
        setRecommendSongAdapter()
        collectMelonChart()
    }

    private fun collectMelonChart() {
        lifecycleScope.launchWhenStarted {
            viewModel.getMelonChartState.collect { state ->
                if (state.songChartList.isNotEmpty()) {
                    updateRecommendSongRecyclerView(state.songChartList)
                }

                if (state.error.isNotBlank())
                    shortToast(state.error)
            }
        }
    }

    private fun searchYouTubeVideo(title: String) {
        val action = SongApplyFragmentDirections.actionSongApplyFragmentToYouTubeFragment(title)
        findNavController().navigate(action)
    }

    private fun setRecommendSongAdapter() {
        recommendSongAdapter = RecommendSongAdapter { title ->
            searchYouTubeVideo(title)
        }
        mBinding.recyclerRecommendSong.adapter = recommendSongAdapter
    }

    private fun updateRecommendSongRecyclerView(list: List<SongChart>) {
        recommendSongAdapter.submitList(list)
    }

    private fun successApplySong() {
        shortToast("기상송 신청에 성공했습니다.")
        popBackStack()
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }
}
