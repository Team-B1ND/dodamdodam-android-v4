package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.BusAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentBusBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo
import java.time.LocalDate

@AndroidEntryPoint
class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>(), BusAdapter.BusApplyCallBack {
    override val viewModel: BusViewModel by viewModels()
    var busId: Int = 0
    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getBusListState.collect { state ->
                    if (state.busList.isNotEmpty()) {
                        val busList = setBusInfo(state.busList)
                        if (busList.isNotEmpty()) {
                            hasBus.value = true
                        }
                        setBusRecyclerView(busList)
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun setBusInfo(busList: List<BusByDate>): List<BusInfo> {
        val list: MutableList<BusInfo> = mutableListOf()
        // val today = LocalDate.now()
        val today = LocalDate.of(2022, 9, 2)
        val todayBus = busList.find {
            val busDateString = it.date.split("-")
            val busDate = LocalDate.of(busDateString[0].toInt(), busDateString[1].toInt(), busDateString[2].toInt())
            today.isAfter(busDate.minusDays(6)) && today.isBefore(busDate.plusDays(1))
        } ?: return emptyList()

        mBinding.tvDate.text = todayBus.date
        busId = viewModel.busApplyState.value.busId

        var rideAble = ""
        var isSelected: Boolean
        todayBus.bustList.forEach {
            if (it.busMemberLength < (it.peopleLimit)) {
                rideAble = "탑승가능"
            } else if (it.busMemberLength >= it.peopleLimit) {
                rideAble = "탑승불가"
            }
            isSelected = it.id == busId
            list.add(
                BusInfo(
                    it.id,
                    it.busName,
                    rideAble,
                    it.busMemberLength.toString() + " / " + it.peopleLimit.toString(),
                    it.leaveTime,
                    isSelected
                )
            )
        }
        return list
    }

    private fun setBusRecyclerView(list: List<BusInfo>) {
        val busAdapter = BusAdapter(requireContext(), this)
        mBinding.recyclerBus.adapter = busAdapter
        busAdapter.submitList(list)
    }

    override fun applyBus(idx: Int) {
        viewModel.applyBus(idx)
        busId = idx
        Log.e("cancelBus override", "정상적 실행 : " + (viewModel.busApplyState.value.busId - 261))
    }

    override fun cancelBus(idx: Int) {
        viewModel.cancelBus(idx)
        busId = idx
        Log.e("cancelBus override", "정상적 실행 : " + (viewModel.busApplyState.value.busId - 261))
    }
}
