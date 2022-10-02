package kr.hs.dgsw.smartschool.dodamdodam.features.song

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.ApplySongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.SongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.util.ViewPagerUtils.getTransform
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.openVideoFromUrl
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData
import java.time.LocalDate

@AndroidEntryPoint
class SongFragment : BaseFragment<FragmentSongBinding, SongViewModel>(), ApplySongAdapter.Action {
    override val viewModel: SongViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private lateinit var songAdapter: SongAdapter
    private lateinit var applySongAdapter: ApplySongAdapter

    private var pendingSongList: List<VideoSongData> = emptyList()
    private var mySongList: List<VideoSongData> = emptyList()

    override fun observerViewModel() {
        mBinding.tvSongDate.text = LocalDate.now().plusDays(1).toString()

        mBinding.appBarSong.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            mBinding.layoutTomorrowSongContainer.alpha = ((100 - (-verticalOffset / 5.45)) / 100).toFloat()
            Log.d("OffsetTest",  "${((100 - (-verticalOffset / 5.45)) / 100).toFloat()}")
        }

        setUpTomorrowSong()
        setSwipeRefresh()

        viewModel.getApplySong()

        collectMyAccount()
        collectTomorrowSong()
        collectMySongList()
        collectPendingSongList()
        collectDeleteSongState()

        bindingViewEvent { event ->
            when (event) {
                SongViewModel.EVENT_ON_CLICK_TOGGLE -> changeRecyclerShow()
                SongViewModel.EVENT_ON_CLICK_APPLY_SONG -> findNavController().navigate(R.id.action_main_song_to_songApplyFragment)
            }
        }
    }

    private fun collectTomorrowSong() {
        lifecycleScope.launchWhenStarted {
            viewModel.getAllowSongState.collect { state ->
                if (state.songList.isNotEmpty()) {
                    songAdapter.submitList(state.songList)
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

    private fun collectMyAccount() {
        lifecycleScope.launchWhenStarted {
            viewModel.getMyAccountState.collect { state ->

                if (state.account != null) {
                    setUpPendingSong(state.account.id)
                }

                if (state.error.isNotBlank())
                    shortToast(state.error)
            }
        }
    }

    private fun collectPendingSongList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getPendingSongState.collect { state ->
                if (state.songList.isNotEmpty()) {
                    pendingSongList = state.songList
                        .sortedBy { it.createdDate }
                    changeRecyclerShow()
                    Log.d("Refreshing", "collectPendingSongList: dodo")
                    endRefreshing()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                    endRefreshing()
                }
            }
        }
    }

    private fun collectMySongList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getMySongState.collect { state ->
                if (state.songList.isNotEmpty()) {
                    if (state.songList[0].quality == "-1") {
                        changeRecyclerShow()
                    }
                    mySongList = state.songList
                        .sortedBy { it.createdDate }
                        .filter { it.playDate == null }
                    changeRecyclerShow()
                }

                if (state.error.isNotBlank())
                    shortToast(state.error)
            }
        }
    }

    private fun collectDeleteSongState() {
        lifecycleScope.launchWhenStarted {
            viewModel.deleteSongState.collect { state ->
                if (state.message != null) {
                    viewModel.getMySong()
                    viewModel.getApplySong()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.message)
                }
            }
        }
    }

    private fun changeRecyclerShow() {
        if (viewModel.songType.value == true) {
            Log.d("CRS", "changeRecyclerShow: 진입 true")
            if (pendingSongList.isEmpty()) {
                viewModel.getApplySong()
            }
            applySongAdapter.submitList(pendingSongList)
        } else {
            mySongList.forEach { mySong ->
                Log.d("CRS", mySong.videoTitle)
            }
            if (mySongList.isEmpty()) {
                viewModel.getMySong()
            }
            applySongAdapter.submitList(mySongList)
        }
    }

    private fun setEmptySongView(isEmptySongList: Boolean = false) {
        if (isEmptySongList) {
            mBinding.tvEmptySong.visibility = View.VISIBLE
            mBinding.viewPagerTomorrowSong.visibility = View.GONE
        } else {
            mBinding.tvEmptySong.visibility = View.GONE
            mBinding.viewPagerTomorrowSong.visibility = View.VISIBLE
        }
    }

    private fun setUpTomorrowSong() {
        songAdapter = SongAdapter { url ->
            this.openVideoFromUrl(url)
        }
        mBinding.viewPagerTomorrowSong.adapter = songAdapter
    }

    private fun setUpPendingSong(id: String) {
        applySongAdapter = ApplySongAdapter(id, this)
        mBinding.recyclerApplySong.adapter = applySongAdapter
    }

    override fun onClickItem(url: String) {
        this@SongFragment.openVideoFromUrl(url)
    }

    override fun onClickDelete(itemId: String) {
        viewModel.deleteSong(itemId)
    }

    private fun setSwipeRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMySong()
            viewModel.getApplySong()
            viewModel.getTomorrowSong()
        }
    }

    private fun endRefreshing() {
        Log.d("Refreshing", "endRefreshing: Hello")
        mBinding.swipeRefreshLayout.isRefreshing = false
    }
}
