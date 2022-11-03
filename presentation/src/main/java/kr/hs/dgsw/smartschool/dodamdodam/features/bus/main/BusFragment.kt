package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentBusBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.bus.adapter.BusAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo

@AndroidEntryPoint
class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>(), BusAdapter.BusApplyCallBack {
    override val viewModel: BusViewModel by viewModels()
    private lateinit var busAdapter: BusAdapter

    var tempBusId : Int = 0
    override fun onStart() {
        super.onStart()
        viewModel.getBusList()
        busAdapter = BusAdapter(requireContext(), this)
        mBinding.recyclerBus.adapter = busAdapter
    }
    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getBusList()
            mBinding.swipeRefreshLayout.isRefreshing = false
        }
        collectGetBusList()
        collectBusTask()
    }
    private fun collectGetBusList(){
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getBusListState.collect { state ->
                    Log.e("LostFoundFragment", "state")
                    if (state.bus != null) {
                        tempBusId = state.applyBusId
                        val busList = setBusInfo(state.bus,tempBusId)
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
    private fun collectBusTask(){
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                busTaskState.collect { state ->
                    Log.e("LostFoundFragment", "state")
                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                    if (state.success.isNotBlank()){
                        shortToast(state.success)
                    }
                }
            }
        }
    }

    private fun setBusInfo(bus: BusByDate, presentId : Int): List<BusInfo> {
        val list: MutableList<BusInfo> = mutableListOf()
        mBinding.tvDate.text = bus.date

        var rideAble = true
        var isSelected : Boolean
        bus.busList.map {
            if (it.peopleCount >= it.peopleLimit) {
                rideAble = false
            }
            isSelected = it.id == presentId
            Log.e("BusFragment","${it.id} : $isSelected at ${presentId}")
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
        busAdapter.submitList(list)
    }

    override fun applyBus(idx: Int) {
        if(idx == tempBusId) viewModel.cancelBus(idx)
        else viewModel.applyBus(idx)

    }
}
