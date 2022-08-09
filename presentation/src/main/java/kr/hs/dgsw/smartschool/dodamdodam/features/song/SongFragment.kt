package kr.hs.dgsw.smartschool.dodamdodam.features.song

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.ApplySongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.SongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongBinding
import kr.hs.dgsw.smartschool.dodamdodam.util.ViewPagerUtils.getTransform
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import java.time.LocalDate

@AndroidEntryPoint
class SongFragment : BaseFragment<FragmentSongBinding, SongViewModel>() {
    override val viewModel: SongViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private lateinit var songAdapter: SongAdapter
    private lateinit var applySongAdapter: ApplySongAdapter

    override fun observerViewModel() {
        mBinding.tvSongDate.text = LocalDate.now().plusDays(1).toString()
        setUpTomorrowSong()
        setUpPendingSong()
        collectSongList()
        collectPendingSongList()
    }

    private fun collectSongList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getAllowSongState.collect { state ->
                if (state.songList.isNotEmpty()) {
                    songAdapter.submitList(state.songList.mapNotNull(VideoYoutubeData::source))
                    setEmptySongView(false)
                } else {
                    setEmptySongView(true)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                    setEmptySongView(true)
                }
            }
        }
    }

    private fun collectPendingSongList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getPendingSongState.collect { state ->
                if (state.songList.isNotEmpty())
                    applySongAdapter.submitList(state.songList.mapNotNull(VideoYoutubeData::source).sortedBy {
                        it.submitDate
                    })

                if (state.error.isNotBlank())
                    shortToast(state.error)
            }
        }
    }

    private fun setEmptySongView(isEmptySongList: Boolean) {
        if (isEmptySongList) {
            mBinding.tvEmptySong.visibility = View.VISIBLE
            mBinding.viewPagerTomorrowSong.visibility = View.GONE
        } else {
            mBinding.tvEmptySong.visibility = View.GONE
            mBinding.viewPagerTomorrowSong.visibility = View.VISIBLE
        }
    }

    private fun setUpTomorrowSong() {
        songAdapter = SongAdapter()
        mBinding.viewPagerTomorrowSong.adapter = songAdapter
        mBinding.viewPagerTomorrowSong.offscreenPageLimit = 3
        mBinding.viewPagerTomorrowSong.setPadding(90, 0, 90, 0)
        mBinding.viewPagerTomorrowSong.setPageTransformer(getTransform())
    }

    private fun setUpPendingSong() {
        applySongAdapter = ApplySongAdapter()
        mBinding.recyclerApplySong.adapter = applySongAdapter
    }
}