package kr.hs.dgsw.smartschool.dodamdodam.features.itmap

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentItmapBinding

class ItMapFragment : BaseFragment<FragmentItmapBinding, ItMapViewModel>(), OnMapReadyCallback {
    override val viewModel: ItMapViewModel by viewModels()

    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource


    override fun observerViewModel() {
        mBinding.mapView.onCreate(savedInstanceState)
        mBinding.mapView.getMapAsync(this)

        bindingViewEvent { event ->
            when (event) {
                ItMapViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        naverMap.cameraPosition = CameraPosition(LatLng(35.662321,128.413837), 7.0)
        naverMap.maxZoom = 18.0
        naverMap.minZoom = 6.0

        with(naverMap.uiSettings) {

            isLocationButtonEnabled = false
            logoGravity = Gravity.END.or(Gravity.TOP)
            setLogoMargin(0, 150, 16, 0)
            isCompassEnabled = false
            isZoomControlEnabled = false
        }

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

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}