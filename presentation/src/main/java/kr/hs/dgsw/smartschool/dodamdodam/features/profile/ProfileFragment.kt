package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
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
import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint
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

    private val pointList: MutableLiveData<FloatArray> = MutableLiveData(floatArrayOf(0F, 0F))

    private var myYearPoint: MyYearPoint? = null

    override fun observerViewModel() {
        mBinding.cardBus.setOnClickListener {
            findNavController().navigate(R.id.action_main_profile_to_busFragment)
        }
        mBinding.cardSetting.setOnClickListener {
            findNavController().navigate(R.id.action_main_profile_to_settingFragment)
        }

        setPieChart()
        bindingViewEvent()
        mBinding.tvPointDate.text = "$date 기준"
        viewModel.getMyPoint(date.year.toString(), 1)
        viewModel.getMyPoint(date.year.toString(), 2)
        observeSchoolAndDormitoryState()
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

    private fun observeSchoolAndDormitoryState() {
        pointList.observe(this) {
            PieDataSet(
                listOf(
                    PieEntry(pointList.value!![1]),
                    PieEntry(pointList.value!![0])
                ), "My Point"
            ).apply {
                setColors(intArrayOf(R.color.color_penalty, R.color.color_prize), context)
                setDrawValues(false)
                setDrawIcons(false)
                mBinding.chartPoint.data = PieData(this)
            }
        }
    }

    private fun collectMyPoint() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getMyPointState.collect { state ->
                    if (state.myYearPoint != null) {
                        myYearPoint = state.myYearPoint
                        setMyPoint( viewModel.dormitorySelected.value == true)
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

    private fun setMyPoint(isDormitory: Boolean) {
        // type : 1은 상점, 2는 벌점
        Log.d("TestTest", "setMyPoint: ${myYearPoint?.yearScore}")
        Log.d("TestTest", "setMyPoint: ${myYearPoint?.log?.getOrNull(0)}")
        when (myYearPoint?.log?.getOrNull(0)?.type ?: 0) {
            0 -> {
                mBinding.tvPrizePoint.text = "0점"
                mBinding.tvPenaltyPoint.text = "0점"
                setChartListData(0, 0)
            }
            1 -> {
                if (isDormitory) {
                    mBinding.tvPrizePoint.text = "${myYearPoint?.yearScore?.zero ?: 0}점"
                    setChartListData(myYearPoint?.yearScore?.zero ?: 0, -1)
                } else {
                    mBinding.tvPrizePoint.text = "${myYearPoint?.yearScore?.one ?: 0}점"
                    setChartListData(myYearPoint?.yearScore?.one ?: 0, -1)
                }
            }
            2 -> {
                if (isDormitory) {
                    mBinding.tvPenaltyPoint.text = "${myYearPoint?.yearScore?.zero ?: 0}점"
                    setChartListData(-1, myYearPoint?.yearScore?.zero ?: 0)
                } else {
                    mBinding.tvPenaltyPoint.text = "${myYearPoint?.yearScore?.one ?: 0}점"
                    setChartListData(-1, myYearPoint?.yearScore?.one ?: 0)
                }
            }
        }
    }

    private fun setChartListData(prize: Int, penalty: Int) {
        if (prize == 0 && penalty == 0) {
            pointList.value?.set(1, penalty.toFloat())
            pointList.value?.set(0, prize.toFloat())
            return
        }
        if (prize == -1) pointList.value?.set(1, penalty.toFloat()) else pointList.value?.set(0, prize.toFloat())
    }

    private fun setNavData(email: String, phone: String, memberId: String, profileImage: String) {
        this.email = email
        this.phone = phone
        this.memberId = memberId
        this.profileImage = profileImage
    }

    private fun setPieChart() {
        mBinding.chartPoint.apply {
            isRotationEnabled = false
            description.isEnabled = false
            holeRadius = 0F
            isDrawHoleEnabled = false
            legend.isEnabled = false
            setNoDataText("데이터가 없습니다.")
        }
    }

    private fun setSwipeRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMyInfo()
        }
    }

    private fun endRefreshing() {
        mBinding.swipeRefreshLayout.isRefreshing = false
    }

    private fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@ProfileFragment) {
                it.getContentIfNotHandled()?.let { event ->
                    when(event) {
                        ProfileViewModel.EVENT_CHANGE_SELECTED -> {
                            setMyPoint(dormitorySelected.value == true)
                        }
                    }
                }
            }
        }
    }
}