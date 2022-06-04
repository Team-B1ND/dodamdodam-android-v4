package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentBusBinding

class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>() {
    override val viewModel: BusViewModel by viewModels()

    override fun observerViewModel() {

    }

}