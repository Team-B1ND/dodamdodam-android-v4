package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kr.hs.dgsw.smartschool.dodamdodam.adapter.BusAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentBusBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo
import java.time.LocalDate

@AndroidEntryPoint
class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>() {
    override val viewModel: BusViewModel by viewModels()
    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                busState.collect { state ->
                    if (state.busList.isNotEmpty()) {
                        setBusRecyclerView(setBusInfo(state.busList))
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

        val today = LocalDate.now()
        val todayBusList = busList.find { it.date.equals("2022-07-22") }


        var rideAble = ""
        todayBusList?.bustList?.forEach {
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
        val busAdapter = BusAdapter()
        mBinding.recyclerBus.adapter = busAdapter
        busAdapter.submitList(list)
    }

}