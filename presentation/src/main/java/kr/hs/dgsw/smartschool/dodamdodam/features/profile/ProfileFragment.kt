package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentProfileBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.point.MyPoint
import java.time.LocalDate

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private var email: String = ""
    private var phone = ""
    private var memberId: String = ""
    private var profileImage: String = ""
    private val date: LocalDate = LocalDate.now()

    private val pointList: Array<Float?> = arrayOfNulls(2)

    override fun observerViewModel() {
        mBinding.cardBus.setOnClickListener {
            findNavController().navigate(R.id.action_main_profile_to_busFragment)
        }
        mBinding.cardSetting.setOnClickListener {
            findNavController().navigate(R.id.action_main_profile_to_settingFragment)
        }

        mBinding.tvPointDate.text = "$date 기준"
        viewModel.getMyPoint(date.year.toString(), 1)
        viewModel.getMyPoint(date.year.toString(), 2)
        setSwipeRefresh()
        collectMyInfo()
        collectMyPoint()
        goEditProfile()
    }

    private fun collectMyInfo() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                myInfoState.collect { state ->
                    if (state.myInfo != null) {
                        with(state.myInfo) {
                            val generation = "%d%d%02d".format(grade, room, number)
                            setProfileInfo(generation, name, email, profileImage ?: "")
                            setNavData(email, phone, id, profileImage ?: "")
                            endRefreshing()
                        }

                        mBinding.btnGoInfoUpdate.visibility = View.VISIBLE
                    }

                    if (state.error.isNotBlank()) {
                        setProfileInfo("", "값을 받아올 수 없습니다.", "", "")
                        mBinding.btnGoInfoUpdate.visibility = View.GONE
                        endRefreshing()
                    }
                }
            }
        }
    }

    private fun collectMyPoint() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getMyPointState.collect { state ->
                    if (state.myPoint != null) {
                        setMyPoint(state.myPoint)
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun goEditProfile() {
        mBinding.btnGoInfoUpdate.setOnClickListener {
            val navAction = ProfileFragmentDirections.actionMainProfileToEditProfileFragment(
                email,
                phone,
                profileImage,
                memberId
            )
            findNavController().navigate(navAction)
        }
    }

    private fun setProfileInfo(
        generation: String,
        name: String,
        email: String,
        profileImage: String
    ) {
        mBinding.tvGeneration.text = generation
        mBinding.tvId.text = name
        mBinding.tvEmail.text = email

        Glide.with(mBinding.root)
            .load(profileImage)
            .centerCrop()
            .error(R.drawable.default_user)
            .into(mBinding.ivProfile)
    }

    private fun setMyPoint(myPoint: MyPoint) {
        // type : 1은 상점, 2는 벌점
        when (myPoint.log.getOrNull(0)?.type ?: 0) {
            0 -> {
                mBinding.tvPrizePoint.text = "0점"
                mBinding.tvPenaltyPoint.text = "0점"
                setChartListData(0, 0)
            }
            1 -> {
                mBinding.tvPrizePoint.text = "${myPoint.score.zero}점"
                setChartListData(myPoint.score.zero, -1)
            }
            2 -> {
                mBinding.tvPenaltyPoint.text = "${myPoint.score.zero}점"
                setChartListData(-1, myPoint.score.zero)
            }
        }
    }

    private fun setChartListData(prize: Int, penalty: Int) {
        if (prize == 0 && penalty == 0) {
            pointList[1] = penalty.toFloat()
            pointList[0] = prize.toFloat()
            if (pointList[0] != null && pointList[1] != null) {
                setPieChart()
                updatePieChart()
            }
            return
        }
        if (prize == -1) pointList[1] = penalty.toFloat() else pointList[0] = prize.toFloat()
        if (pointList[0] != null && pointList[1] != null) {
            setPieChart()
            updatePieChart()
        }
    }

    private fun setPieChart() {
        mBinding.chartPoint.apply {
            isRotationEnabled = false
            description.isEnabled = false
            holeRadius = 0F
            isDrawHoleEnabled = false
            legend.isEnabled = false
            setNoDataText("데이터가 없습니다.")
            invalidate()
        }
    }

    private fun updatePieChart() {
        PieDataSet(
            listOf(
                PieEntry(pointList[1]!!),
                PieEntry(pointList[0]!!)
            ), "My Point"
        ).apply {
            setColors(intArrayOf(R.color.color_penalty, R.color.color_prize), context)
            setDrawValues(false)
            setDrawIcons(false)
            mBinding.chartPoint.data = PieData(this)
        }
    }

    private fun setNavData(email: String, phone: String, memberId: String, profileImage: String) {
        this.email = email
        this.phone = phone
        this.memberId = memberId
        this.profileImage = profileImage
    }

    private fun setSwipeRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMyInfo()
            viewModel.getMyPoint(date.year.toString(), 1)
            viewModel.getMyPoint(date.year.toString(), 2)
        }
    }

    private fun endRefreshing() {
        mBinding.swipeRefreshLayout.isRefreshing = false
    }
}