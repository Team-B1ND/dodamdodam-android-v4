package kr.hs.dgsw.smartschool.dodamdodam.features.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealHomeAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.StudyRoomAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.TodaySongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentHomeBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.song.SongFragment
import kr.hs.dgsw.smartschool.domain.model.location.Location
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo
import kr.hs.dgsw.smartschool.domain.model.song.Song

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()

    override fun observerViewModel() {
        setUpTodayMeal()
        setUpStudyRoom()
        setUpTodaySong()
        bindViews()
    }

    private fun setUpTodayMeal() {
        val mealHomeAdapter = MealHomeAdapter()
        mBinding.viewPagerMealList.adapter = mealHomeAdapter
        mBinding.viewPagerMealList.offscreenPageLimit = 3
        mBinding.viewPagerMealList.setPadding(90, 0, 90, 0)
        mBinding.viewPagerMealList.setPageTransformer(getTransform())
        mealHomeAdapter.submitList(
            listOf(
                MealInfo(1, "쇠고기버섯죽 , *크로크무슈 , 나박물김치 , *오레오오즈레드+우유 , 바나나"),
                MealInfo(2, "*발아현미밥 , *놀부부대찌개 , *꽁치감자조림 , *시카고피자 , 배추김치 , 납작복숭아주스"),
                MealInfo(3, "*기장밥 , 김치어묵국 , *명태껍질볶음 , 새송이돈육마늘구이 , *짜먹는요거트 , *꽃상추쌈/쌈장")
            )
        )
    }

    private fun setUpTodaySong() {
        val todaySongAdapter = TodaySongAdapter()
        mBinding.viewPagerTodaySong.adapter = todaySongAdapter
        mBinding.viewPagerTodaySong.offscreenPageLimit = 3
        mBinding.viewPagerTodaySong.setPadding(90, 0, 90, 0)
        mBinding.viewPagerTodaySong.setPageTransformer(getTransform())
        todaySongAdapter.submitList(
            listOf(
                Song("(G)I-DLE 'TOMBOY' Lyrics ((여자)아이들 TOMBOY 가사) (Color Coded Lyrics)", "https://i.ytimg.com/vi/E6W835snlNg/maxresdefault.jpg", ""),
                Song("BTS X Coldplay My Universe Lyrics (방탄소년단 콜드플레이 My Universe 가사) [Color Coded Lyrics/Han/Rom/Eng]", "https://i.ytimg.com/vi/nHKk8MTXgds/maxresdefault.jpg", ""),
                Song("\uD83C\uDFC6발매와 함께 빌보드 1위 달성 : Harry Styles - As It Was [가사/해석/번역/lyrics]", "https://i.ytimg.com/vi/OMRZevAb_jU/maxresdefault.jpg", "")
            )
        )
    }

    private fun setUpStudyRoom() {
        val studyRoomAdapter = StudyRoomAdapter()
        mBinding.recyclerStudyRoom.adapter = studyRoomAdapter
        studyRoomAdapter.submitList(
            listOf(
                Location("17:00 ~ 17:20", "시간대가 지났습니다."),
                Location("17:00 ~ 17:20", "시간대가 지났습니다."),
                Location("17:00 ~ 17:20", "시간대가 지났습니다."),
                Location("17:00 ~ 17:20", "시간대가 지났습니다.")
            )
        )
    }

    private fun bindViews() {
        mBinding.tvSongMore.setOnClickListener {
            (activity as? MainActivity)?.moveHomeToSong()
        }
    }

    companion object {
        fun getTransform() : CompositePageTransformer {
            val transform = CompositePageTransformer()
            transform.addTransformer(MarginPageTransformer(10))

            transform.addTransformer { view: View, fl: Float ->
                val v = 1 - Math.abs(fl)
                view.scaleY = 0.8f + v * 0.2f
            }

            return transform
        }
    }
}