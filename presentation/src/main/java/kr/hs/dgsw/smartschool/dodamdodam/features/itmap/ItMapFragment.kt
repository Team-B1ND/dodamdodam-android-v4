package kr.hs.dgsw.smartschool.dodamdodam.features.itmap

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentItmapBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.adapter.CompanyAdapter
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.adapter.CompanyViewPagerAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class ItMapFragment : BaseFragment<FragmentItmapBinding, ItMapViewModel>() {
    override val viewModel: ItMapViewModel by viewModels()

    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var companyViewPagerAdapter: CompanyViewPagerAdapter

    override fun observerViewModel() {
        val mapview = MapView(requireActivity())
        mBinding.kakaoMapView.addView(mapview)

        setCompanyAdapter()
        setCompanyViewPagerAdapter()

        viewModel.getAllCompanies()
        collectGetAllCompaniesState()


        bindingViewEvent { event ->
            when (event) {
                ItMapViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }

    }

    private fun collectGetAllCompaniesState() = lifecycleScope.launchWhenStarted {
        viewModel.getAllCompaniesState.collect { state ->
            if (state.isUpdate) {
                companyAdapter.submitList(state.companies)
                companyViewPagerAdapter.submitList(state.companies)
            }

            if (state.error.isNotBlank()) {
                shortToast(state.error)
            }
        }
    }

    private fun setCompanyAdapter() {
        companyAdapter = CompanyAdapter()
        mBinding.bottomSheet.rvCompany.adapter = companyAdapter
    }

    private fun setCompanyViewPagerAdapter() {
        companyViewPagerAdapter = CompanyViewPagerAdapter()
        mBinding.vpCompany.adapter = companyViewPagerAdapter
    }
}