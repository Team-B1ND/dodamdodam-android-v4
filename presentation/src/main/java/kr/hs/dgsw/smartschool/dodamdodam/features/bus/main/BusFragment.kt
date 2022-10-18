package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.features.bus.adapter.BusAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentBusBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo
import java.time.LocalDate

@AndroidEntryPoint
class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>(), BusAdapter.BusApplyCallBack {
    override val viewModel: BusViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBusList()
    }
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

    private fun setBusInfo(busList: List<Bus>): List<BusInfo> {
        val list: MutableList<BusInfo> = mutableListOf()
        var rideAble = ""
        var isSelected: Boolean
        busList.forEach {
            if (it.peopleCount < (it.peopleLimit)) {
                rideAble = "탑승가능"
            } else if (it.peopleCount >= it.peopleLimit) {
                rideAble = "탑승불가"
            }
            isSelected = it.id == viewModel.busId
            list.add(
                BusInfo(
                    it.id,
                    it.busName,
                    rideAble,
                    it.peopleCount.toString() + " / " + it.peopleLimit.toString(),
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
    }

    override fun cancelBus(idx: Int) {
        viewModel.cancelBus(idx)
    }
}
