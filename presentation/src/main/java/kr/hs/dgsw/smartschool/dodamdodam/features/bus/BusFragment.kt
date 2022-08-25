package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import android.bluetooth.BluetoothAdapter.ERROR
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.BusAdapter
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
    var busId :  Int = 0
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

    private fun setBusInfo(busList: List<BusByDate>) : List<BusInfo>{
        val list : MutableList<BusInfo> = mutableListOf()
        //val today = LocalDate.now()
        val today = LocalDate.of(2022, 9, 2)
        val todayBus = busList.find {
            val busDateString = it.date.split("-")
            val busDate = LocalDate.of(busDateString[0].toInt(), busDateString[1].toInt(), busDateString[2].toInt())
            today.isAfter(busDate.minusDays(6)) && today.isBefore(busDate.plusDays(1))
        } ?: return emptyList()

        mBinding.tvDate.text = todayBus.date

        var rideAble = ""
        var isSelected : Boolean = false
        todayBus.bustList.forEach {
            if (it.busMemberlength < (it.peopleLimit)) {
                rideAble = "탑승가능"
            } else if (it.busMemberlength >= it.peopleLimit) {
                rideAble = "탑승불가"
            }
            isSelected = it.idx == busId//viewModel.busApplyState.value.busId
            list.add(
                BusInfo(
                    it.idx,
                    it.busName,
                    rideAble,
                    it.busMemberlength.toString()+" / "+it.peopleLimit.toString(),
                    it.leaveTime,
                    isSelected
                )
            )

        }
        Log.e("setBusInfo",list.toString())
        return list
    }

    private fun setBusRecyclerView(list: List<BusInfo>) {
        val busAdapter = BusAdapter(requireContext(),this)
        Log.e("setBusRecyclerView",list.toString())
        mBinding.recyclerBus.adapter = busAdapter
        busAdapter.submitList(list)
    }

    override fun applyBus(idx:Int) {
        viewModel.applyBus(idx)
        busId= idx
        Log.e("applyBus override","정상적 실행")
    }

    override fun cancelBus(idx: Int) {
        viewModel.cancelBus(idx)
        busId= idx
        Log.e("cancelBus override","정상적 실행")
    }
}
