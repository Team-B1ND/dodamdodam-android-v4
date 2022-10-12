package kr.hs.dgsw.smartschool.dodamdodam.features.itmap

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

        collectGetAllCompaniesState()

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
    }

    private fun collectGetAllCompaniesState() = lifecycleScope.launchWhenStarted {
        viewModel.getAllCompaniesState.collectLatest { state ->
            if (state.isUpdate) {
                if (state.companies.isNotEmpty()) {
                    setMarker(state.companies)
                }
                companyAdapter.submitList(state.companies)
                companyViewPagerAdapter.submitList(state.companies)

                mBinding.bottomSheet.tvBottomSheetTitle.text = "${state.companies.size}개의 회사"
            }

            if (state.error.isNotBlank()) {
                shortToast(state.error)
            }
        }
    }

    private fun setMarker(companies: List<Company>) {
        companies.forEach { company ->
            val marker = Marker()

            try {
                marker.position = addressToGps(company.address)
            } catch (e: Exception) {
                return@forEach
            }

            marker.map = naverMap
            marker.tag = company.id
            marker.icon = OverlayImage.fromResource(R.drawable.ic_marker_office_building)

            marker.setOnClickListener {
                navigateToDetail(it.tag as Int)
                true
            }
        }
    }

    private fun navigateToDetail(id: Int) {
        val action = ItMapFragmentDirections.actionItMapFragmentToItMapDetailFragment(id)
        findNavController().navigate(action)
    }

    private fun setCompanyAdapter() {
        companyAdapter = CompanyAdapter {
            navigateToDetail(it)
        }
        mBinding.bottomSheet.rvCompany.adapter = companyAdapter
    }

    private fun setCompanyViewPagerAdapter() {
        companyViewPagerAdapter = CompanyViewPagerAdapter {
            navigateToDetail(it)
        }
        mBinding.vpCompany.adapter = companyViewPagerAdapter

        mBinding.vpCompany.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                CoroutineScope(Dispatchers.Default).launch {
                    val selectedCompanyModel = companyViewPagerAdapter.currentList[position]
                    val cameraUpdate = CameraUpdate
                        .scrollAndZoomTo(addressToGps(selectedCompanyModel.address), 15.0)
                        .animate(CameraAnimation.Easing)
                    naverMap.moveCamera(cameraUpdate)
                }
            }
        })
    }

    private fun addressToGps(address: String): LatLng {
        Geocoder(requireContext()).getFromLocationName(address, 1).let {
            if (it.isEmpty())
                throw Exception("NoAddress")
            return LatLng(it[0].latitude, it[0].longitude)
        }
    }

    // 아래 수명주기 연결
    override fun onStart() {
        super.onStart()
        mBinding.mapView.onStart()
        Log.d("MapTest", "START")
    }

    override fun onResume() {
        super.onResume()
        mBinding.mapView.onResume()
        Log.d("MapTest", "RESUME")
    }

    override fun onPause() {
        super.onPause()
        mBinding.mapView.onPause()
        Log.d("MapTest", "PAUSE")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mBinding.mapView.onSaveInstanceState(outState)
        Log.d("MapTest", "ON save...")
    }

    override fun onStop() {
        super.onStop()
        mBinding.mapView.onStop()
        Log.d("MapTest", "STOP")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding.mapView.onDestroy()
        Log.d("MapTest", "DESTROY")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mBinding.mapView.onLowMemory()
        Log.d("MapTest", "LOW MEMORY")
    }
}