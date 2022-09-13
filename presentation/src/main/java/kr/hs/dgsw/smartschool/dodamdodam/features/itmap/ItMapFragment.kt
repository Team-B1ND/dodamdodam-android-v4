package kr.hs.dgsw.smartschool.dodamdodam.features.itmap

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentItmapBinding
import net.daum.mf.map.api.MapView

class ItMapFragment : BaseFragment<FragmentItmapBinding, ItMapViewModel>() {
    override val viewModel: ItMapViewModel by viewModels()

    override fun observerViewModel() {
        val mapview = MapView(requireActivity())
        mBinding.kakaoMapView.addView(mapview)

        bindingViewEvent { event ->
            when (event) {
                ItMapViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }

    }
}