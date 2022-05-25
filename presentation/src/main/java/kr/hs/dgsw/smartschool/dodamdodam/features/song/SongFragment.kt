package kr.hs.dgsw.smartschool.dodamdodam.features.song

import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealHomeAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.NowSongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.TodaySongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongBinding
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo
import kr.hs.dgsw.smartschool.domain.model.song.Song
import java.time.LocalDate

class SongFragment : BaseFragment<FragmentSongBinding, SongViewModel>() {
    override val viewModel: SongViewModel by viewModels()

    override fun observerViewModel() {
        setUpTodaySong()
        setUpNowSong()
        initDate()
    }

    private fun initDate() {
        mBinding.tvSongDate.text = LocalDate.now().toString()
    }

    private fun setUpNowSong() {
        val nowSongAdapter = NowSongAdapter()
        mBinding.recyclerNowSong.adapter = nowSongAdapter
        nowSongAdapter.submitList(
            listOf(
                Song("(G)I-DLE 'TOMBOY' Lyrics ((여자)아이들 TOMBOY 가사) (Color Coded Lyrics)", "https://i.ytimg.com/vi/E6W835snlNg/maxresdefault.jpg", "신청일 : 2022-05-25"),
                Song("(G)I-DLE 'TOMBOY' Lyrics ((여자)아이들 TOMBOY 가사) (Color Coded Lyrics)", "https://i.ytimg.com/vi/E6W835snlNg/maxresdefault.jpg", "신청일 : 2022-05-25"),
                Song("(G)I-DLE 'TOMBOY' Lyrics ((여자)아이들 TOMBOY 가사) (Color Coded Lyrics)", "https://i.ytimg.com/vi/E6W835snlNg/maxresdefault.jpg", "신청일 : 2022-05-25"),
                Song("(G)I-DLE 'TOMBOY' Lyrics ((여자)아이들 TOMBOY 가사) (Color Coded Lyrics)", "https://i.ytimg.com/vi/E6W835snlNg/maxresdefault.jpg", "신청일 : 2022-05-25"),
                Song("BTS X Coldplay My Universe Lyrics (방탄소년단 콜드플레이 My Universe 가사) [Color Coded Lyrics/Han/Rom/Eng]", "https://i.ytimg.com/vi/nHKk8MTXgds/maxresdefault.jpg", "신청일 : 2022-06-23"),
                Song("BTS X Coldplay My Universe Lyrics (방탄소년단 콜드플레이 My Universe 가사) [Color Coded Lyrics/Han/Rom/Eng]", "https://i.ytimg.com/vi/nHKk8MTXgds/maxresdefault.jpg", "신청일 : 2022-06-23"),
                Song("BTS X Coldplay My Universe Lyrics (방탄소년단 콜드플레이 My Universe 가사) [Color Coded Lyrics/Han/Rom/Eng]", "https://i.ytimg.com/vi/nHKk8MTXgds/maxresdefault.jpg", "신청일 : 2022-06-23"),
                Song("BTS X Coldplay My Universe Lyrics (방탄소년단 콜드플레이 My Universe 가사) [Color Coded Lyrics/Han/Rom/Eng]", "https://i.ytimg.com/vi/nHKk8MTXgds/maxresdefault.jpg", "신청일 : 2022-06-23"),
                Song("BTS X Coldplay My Universe Lyrics (방탄소년단 콜드플레이 My Universe 가사) [Color Coded Lyrics/Han/Rom/Eng]", "https://i.ytimg.com/vi/nHKk8MTXgds/maxresdefault.jpg", "신청일 : 2022-06-23"),
                Song("\uD83C\uDFC6발매와 함께 빌보드 1위 달성 : Harry Styles - As It Was [가사/해석/번역/lyrics]", "https://i.ytimg.com/vi/OMRZevAb_jU/maxresdefault.jpg", "신청일 : 2022-06-23"),
                Song("\uD83C\uDFC6발매와 함께 빌보드 1위 달성 : Harry Styles - As It Was [가사/해석/번역/lyrics]", "https://i.ytimg.com/vi/OMRZevAb_jU/maxresdefault.jpg", "신청일 : 2022-06-23"),
                Song("\uD83C\uDFC6발매와 함께 빌보드 1위 달성 : Harry Styles - As It Was [가사/해석/번역/lyrics]", "https://i.ytimg.com/vi/OMRZevAb_jU/maxresdefault.jpg", "신청일 : 2022-06-23"),
                Song("\uD83C\uDFC6발매와 함께 빌보드 1위 달성 : Harry Styles - As It Was [가사/해석/번역/lyrics]", "https://i.ytimg.com/vi/OMRZevAb_jU/maxresdefault.jpg", "신청일 : 2022-06-23")
            )
        )
    }

    private fun setUpTodaySong() {
        val todaySongAdapter = TodaySongAdapter()
        mBinding.viewPagerTodaySong.adapter = todaySongAdapter
        mBinding.viewPagerTodaySong.offscreenPageLimit = 3
        mBinding.viewPagerTodaySong.setPadding(90, 0, 90, 0)

        val transform = CompositePageTransformer()
        transform.addTransformer(MarginPageTransformer(10))

        transform.addTransformer { view: View, fl: Float ->
            val v = 1 - Math.abs(fl)
            view.scaleY = 0.8f + v * 0.2f
        }
        mBinding.viewPagerTodaySong.setPageTransformer(transform)
        todaySongAdapter.submitList(
            listOf(
                Song("(G)I-DLE 'TOMBOY' Lyrics ((여자)아이들 TOMBOY 가사) (Color Coded Lyrics)", "https://i.ytimg.com/vi/E6W835snlNg/maxresdefault.jpg", ""),
                Song("BTS X Coldplay My Universe Lyrics (방탄소년단 콜드플레이 My Universe 가사) [Color Coded Lyrics/Han/Rom/Eng]", "https://i.ytimg.com/vi/nHKk8MTXgds/maxresdefault.jpg", ""),
                Song("\uD83C\uDFC6발매와 함께 빌보드 1위 달성 : Harry Styles - As It Was [가사/해석/번역/lyrics]", "https://i.ytimg.com/vi/OMRZevAb_jU/maxresdefault.jpg", "")
            )
        )
    }

}