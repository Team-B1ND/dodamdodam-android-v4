package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentProfileBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointType
import java.time.LocalDate

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private var email: String = ""
    private var phone = ""
    private var memberId: String = ""
    private var profileImage: String = ""

    private var schoolMinusPoint: Int? = null
    private var dormitoryMinusPoint: Int? = null
    private var schoolBonusPoint: Int? = null
    private var dormitoryBonusPoint: Int? = null

    override fun observerViewModel() {
        setPieChart()
        collectMyInfo()
        collectBonusPoint()
        setPointCard(1)
        setSwipeRefresh()
        initViewEvent()
    }

    private fun initViewEvent() {
        bindingViewEvent { event ->
            when (event) {
                ProfileViewModel.EVENT_CHANGE_SELECTED -> changePointCard()
                ProfileViewModel.EVENT_GO_EDIT_PROFILE -> startEditProfileFragment()
                ProfileViewModel.EVENT_ON_CLICK_BUS -> findNavController().navigate(R.id.action_main_profile_to_busFragment)
                ProfileViewModel.EVENT_ON_CLICK_SETTING -> findNavController().navigate(R.id.action_main_profile_to_settingFragment)
            }
        }
    }

    private fun changePointCard() {
        if (viewModel.dormitorySelected.value == false)
            setPointCard(0)
        else
            setPointCard(1)
    }

    private fun startEditProfileFragment() {
        val navAction =
            ProfileFragmentDirections.actionMainProfileToEditProfileFragment(
                email,
                phone,
                profileImage,
                memberId
            )
        findNavController().navigate(navAction)
    }

    private fun collectMyInfo() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                myInfoState.collect { state ->
                    if (state.myInfo != null) {
                        with(state.myInfo) {
                            val generation = "%d%d%02d".format(classroom.grade, classroom.room, number)
                            setProfileInfo(generation, member.name, member.email, member.profileImage ?: "")
                            setNavData(member.email, phone, member.id, member.profileImage ?: "")
                            endRefreshing()
                        }

                        mBinding.tvModify.visibility = View.VISIBLE
                    }

                    if (state.error.isNotBlank()) {
                        setProfileInfo("", "값을 받아올 수 없습니다.", "", "")
                        mBinding.tvModify.visibility = View.GONE
                        endRefreshing()
                    }
                }
            }
        }
    }

    private fun collectBonusPoint() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getMyYearPointsState.collect { state ->
                    if (state.isReach) {
                        state.yearPointList.forEach { point ->
                            Log.d("PointTest", "${point.type.name} : ${point.score}")
                        }
                        dividePoint(yearPointList = state.yearPointList)
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun dividePoint(yearPointList: List<Point>) {

        dormitoryMinusPoint = 0
        schoolBonusPoint = 0
        dormitoryMinusPoint = 0
        schoolMinusPoint = 0

        yearPointList.map { pointLog ->
            if (pointLog.type == PointType.BONUS) {
                if (pointLog.target == PointPlace.DORMITORY)
                    dormitoryBonusPoint = (dormitoryBonusPoint ?: 0) + pointLog.score
                else
                    schoolBonusPoint = (schoolBonusPoint ?: 0) + pointLog.score
            } else if (pointLog.type == PointType.MINUS) {
                if (pointLog.target == PointPlace.DORMITORY)
                    dormitoryMinusPoint = (dormitoryMinusPoint ?: 0) + pointLog.score
                else
                    schoolMinusPoint = (schoolMinusPoint ?: 0) + pointLog.score
            }
        }
        setPointCard(1)
    }

    private fun setPointCard(target: Int) {
        if (target == 0) {
            Log.d("PointTest", "SetPointCard :  ${dormitoryMinusPoint ?: 0}")
            mBinding.tvBonusPoint.text = (dormitoryBonusPoint ?: 0).toString() + "점"
            mBinding.tvMinusPoint.text = (dormitoryMinusPoint ?: 0).toString() + "점"
            updatePieChart(dormitoryBonusPoint ?: 0, dormitoryMinusPoint ?: 0)
        } else {
            mBinding.tvBonusPoint.text = (schoolBonusPoint ?: 0).toString() + "점"
            mBinding.tvMinusPoint.text = (schoolMinusPoint ?: 0).toString() + "점"
            updatePieChart(schoolBonusPoint ?: 0, schoolMinusPoint ?: 0)
        }
    }

    private fun updatePieChart(bonusPoint: Int, minusPoint: Int) {
        if (bonusPoint == 0 && minusPoint == 0) {
            mBinding.tvNoData.visibility = View.VISIBLE
            mBinding.chartPoint.visibility = View.INVISIBLE
        } else {
            mBinding.tvNoData.visibility = View.GONE
            mBinding.chartPoint.visibility = View.VISIBLE
            PieDataSet(
                listOf(
                    PieEntry(minusPoint.toFloat()),
                    PieEntry(bonusPoint.toFloat())
                ),
                "My Point"
            ).apply {
                setColors(intArrayOf(R.color.color_minus, R.color.color_bonus), context)
                setDrawValues(false)
                setDrawIcons(false)
                mBinding.chartPoint.data = PieData(this)
                mBinding.chartPoint.invalidate()
            }
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

    override fun onResume() {
        super.onResume()
        Log.d("TestProfile", "onResume: asdlkfjsa;l")
    }
}
