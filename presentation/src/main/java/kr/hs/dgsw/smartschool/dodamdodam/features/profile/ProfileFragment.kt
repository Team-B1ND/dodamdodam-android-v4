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
import kr.hs.dgsw.smartschool.domain.model.point.MyTargetPoint
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

    private var dormitoryPoint: MyTargetPoint? = null
    private var schoolPoint: MyTargetPoint? = null

    override fun observerViewModel() {
        mBinding.cardBus.setOnClickListener {
            findNavController().navigate(R.id.action_main_profile_to_busFragment)
        }
        mBinding.cardSetting.setOnClickListener {
            findNavController().navigate(R.id.action_main_profile_to_settingFragment)
        }

        mBinding.tvPointDate.text = "$date 기준"

        setPieChart()
        bindingViewEvent()
        collectMyInfo()
        collectDormitoryPoint()
        collectSchoolPoint()
        setSwipeRefresh()
    }

    private fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@ProfileFragment) {
                it.getContentIfNotHandled()?.let { event ->
                    when(event) {
                        ProfileViewModel.EVENT_CHANGE_SELECTED -> {
                            if (dormitorySelected.value == true)
                                setPointCard(0)
                            else
                                setPointCard(1)
                        }
                        ProfileViewModel.EVENT_GO_EDIT_PROFILE -> {
                            val navAction = ProfileFragmentDirections.actionMainProfileToEditProfileFragment(
                                email,
                                phone,
                                profileImage,
                                memberId
                            )
                            findNavController().navigate(navAction)
                        }

                    }
                }
            }
        }
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

    private fun collectDormitoryPoint() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                myDormitoryPointState.collect { state ->
                    if (state.myDormitoryPoint != null) {
                        dormitoryPoint = state.myDormitoryPoint

                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun collectSchoolPoint() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                mySchoolPointState.collect { state ->
                    if (state.mySchoolPoint != null) {
                        schoolPoint = state.mySchoolPoint
                        setPointCard(0)
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun setPointCard(target: Int) {
        val bonusPoint: Int
        val minusPoint: Int

        if (target == 0) {
            bonusPoint = dormitoryPoint?.targetScore?.bonusPoint ?: 0
            minusPoint = dormitoryPoint?.targetScore?.minusPoint ?: 0
        } else {
            bonusPoint = schoolPoint?.targetScore?.bonusPoint ?: 0
            minusPoint = schoolPoint?.targetScore?.minusPoint ?: 0
        }

        mBinding.tvBonusPoint.text = bonusPoint.toString()
        mBinding.tvMinusPoint.text = minusPoint.toString()
        updatePieChart(bonusPoint, minusPoint)
    }

    private fun updatePieChart(bonusPoint: Int, minusPoint: Int) {
        PieDataSet(
            listOf(
                PieEntry(minusPoint.toFloat()),
                PieEntry(bonusPoint.toFloat())
            ), "My Point"
        ).apply {
            setColors(intArrayOf(R.color.color_minus, R.color.color_bous), context)
            setDrawValues(false)
            setDrawIcons(false)
            mBinding.chartPoint.data = PieData(this)
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
}