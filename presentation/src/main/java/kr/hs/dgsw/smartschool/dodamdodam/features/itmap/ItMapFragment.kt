package kr.hs.dgsw.smartschool.dodamdodam.features.itmap

import android.location.Geocoder
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.AndroidEntryPoint
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentItmapBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.adapter.CompanyAdapter
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.adapter.CompanyViewPagerAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.itmap.Company


@AndroidEntryPoint
class ItMapFragment : BaseFragment<FragmentItmapBinding, ItMapViewModel>(), OnMapReadyCallback {
    override val viewModel: ItMapViewModel by viewModels()

    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var companyViewPagerAdapter: CompanyViewPagerAdapter

    private lateinit var naverMap: NaverMap

    override fun observerViewModel() {
        mBinding.mapView.onCreate(savedInstanceState)
        mBinding.mapView.getMapAsync(this)

        setCompanyAdapter()
        setCompanyViewPagerAdapter()

        bindingViewEvent { event ->
            when (event) {
                ItMapViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }

    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        naverMap.maxZoom = 20.0
        naverMap.minZoom = 5.0

        with(naverMap.uiSettings) {
            isLocationButtonEnabled = false
            logoGravity = Gravity.END.or(Gravity.TOP)
            setLogoMargin(0, 0, 16, 0)
            isCompassEnabled = false
            isZoomControlEnabled = false
        }

        viewModel.getAllCompanies()
        viewModel.getAllCompanies()
        collectGetAllCompaniesState()
    }

    private fun collectGetAllCompaniesState() = lifecycleScope.launchWhenStarted {
        viewModel.getAllCompaniesState.collect { state ->
            if (state.isUpdate) {
                companyAdapter.submitList(state.companies)
                companyViewPagerAdapter.submitList(state.companies)

                state.companies.forEach { company ->
                    setMarker(company)
                }

                mBinding.bottomSheet.tvBottomSheetTitle.text = "${state.companies.size}개의 회사"
            }

            if (state.error.isNotBlank()) {
                shortToast(state.error)
            }
        }
    }

    private fun setMarker(company: Company) {
        val marker = Marker()

        marker.position = Geocoder(requireContext()).getFromLocationName(company.address, 1).let {
            if(it.isEmpty())
                return
            LatLng(it[0].latitude, it[0].longitude)
        }

        marker.map = naverMap
        marker.tag = company.id
        marker.icon = OverlayImage.fromResource(R.drawable.marker_office_building)

        marker.setOnClickListener {
            shortToast(it.tag.toString())
            true
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

    // 아래 수명주기 연결
    override fun onStart() {
        super.onStart()
        mBinding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mBinding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mBinding.mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mBinding.mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mBinding.mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mBinding.mapView.onLowMemory()
    }

}