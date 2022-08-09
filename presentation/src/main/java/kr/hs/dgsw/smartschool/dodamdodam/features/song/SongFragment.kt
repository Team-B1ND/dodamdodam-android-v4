package kr.hs.dgsw.smartschool.dodamdodam.features.song

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.adapter.SongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongBinding
import kr.hs.dgsw.smartschool.dodamdodam.util.ViewPagerUtils.getTransform
import java.time.LocalDate

class SongFragment : BaseFragment<FragmentSongBinding, SongViewModel>() {
    override val viewModel: SongViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {
        setUpTodaySong()
        setUpNowSong()
        initDate()
    }

    private fun initDate() {
        mBinding.tvSongDate.text = LocalDate.now().toString()
    }

    private fun setUpNowSong() {
        val songAdapter = SongAdapter()
        mBinding.recyclerNowSong.adapter = songAdapter
    }

    private fun setUpTodaySong() {
        val songAdapter = SongAdapter()
        mBinding.viewPagerTodaySong.adapter = songAdapter
        mBinding.viewPagerTodaySong.offscreenPageLimit = 3
        mBinding.viewPagerTodaySong.setPadding(90, 0, 90, 0)
        mBinding.viewPagerTodaySong.setPageTransformer(getTransform())
    }
}