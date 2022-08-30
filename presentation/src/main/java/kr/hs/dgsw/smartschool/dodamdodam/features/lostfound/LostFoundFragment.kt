package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.adapter.LostFoundAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundBinding
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo
import java.time.LocalDate

class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>(), LostFoundAdapter.LostFoundCallBack {
    override val viewModel: LostFoundViewModel by viewModels()
    private lateinit var lostFoundAdapter: LostFoundAdapter
    override fun observerViewModel() {
        lostFoundAdapter = LostFoundAdapter(requireContext(), this)
        mBinding.rvLostAndFound.adapter = lostFoundAdapter
    }
    override fun openComment(idx: Int) {
        viewModel.openComment(idx)
    }
    private fun setBusInfo(lostFoundList: List<LostFound>): List<BusInfo> {
        val list: MutableList<LostFound> = mutableListOf()
        // val today = LocalDate.now()
       lostFoundList.forEach {
           list.add(LostInfo(
               idx =
           ))
       }


        var rideAble = ""
        var isSelected: Boolean = false
        todayBus.bustList.forEach {
            if (it.busMemberlength < (it.peopleLimit)) {
                rideAble = "탑승가능"
            } else if (it.busMemberlength >= it.peopleLimit) {
                rideAble = "탑승불가"
            }
            isSelected = it.idx == busId
            list.add(
                BusInfo(
                    it.idx,
                    it.busName,
                    rideAble,
                    it.busMemberlength.toString() + " / " + it.peopleLimit.toString(),
                    it.leaveTime,
                    isSelected
                )
            )
        }
        return list
}
