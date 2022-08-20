package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.BusAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.BusApplyCallBack
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentBusBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo
import java.time.LocalDate

@AndroidEntryPoint
class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>(),BusApplyCallBack {
    override val viewModel: BusViewModel by viewModels()
    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                busState.collect { state ->
                    if (state.busList.isNotEmpty()) {
                        val busList = setBusInfo(state.busList)
                        if (busList.isNotEmpty()) {
                            hasBus.value = true
                        }
                        setBusRecyclerView(busList)
                    }

                    if(state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }

    }

    private fun setBusInfo(busList: List<BusByDate>) : List<BusInfo>{
        val list : MutableList<BusInfo> = mutableListOf()

        //val today = LocalDate.now()
        val today = LocalDate.of(2022, 7, 19)

        val todayBus = busList.find {
            val busDateString = it.date.split("-")
            val busDate = LocalDate.of(busDateString[0].toInt(), busDateString[1].toInt(), busDateString[2].toInt())
            today.isAfter(busDate.minusDays(6)) && today.isBefore(busDate.plusDays(1))
        } ?: return emptyList()

        mBinding.tvDate.text = todayBus.date

        var rideAble = ""
        todayBus.bustList.forEach {
            if(it.busMemberlength < (it.peopleLimit)){
                rideAble = "탑승가능"
            } else if(it.busMemberlength >= it.peopleLimit){
                rideAble = "탑승불가"
            }
            list.add(
                BusInfo(
                    it.busName,
                    rideAble,
                    it.busMemberlength.toString()+" / "+it.peopleLimit.toString(),
                    it.leaveTime
                )
            )
        }
        return list
    }

    private fun setBusRecyclerView(list: List<BusInfo>) {
        val busAdapter = BusAdapter(requireContext())
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