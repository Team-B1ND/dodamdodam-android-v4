package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.BusAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentBusBinding
import java.time.LocalDate

@AndroidEntryPoint
class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>() {
    override val viewModel: BusViewModel by viewModels()
    override fun observerViewModel() {
        val date = LocalDate.now()
        viewModel.getBusList(date)
        mBinding.recyclerBus.adapter = BusAdapter(viewModel.busInfo)
    }

}