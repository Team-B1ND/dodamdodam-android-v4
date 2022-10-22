package kr.hs.dgsw.smartschool.dodamdodam.features.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isInvisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivityMainBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.start.StartActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        if (!SharedPreferenceManager.getIsLogin(this))
            startActivityWithFinishAll(StartActivity::class.java)
        else {
            super.onCreate(savedInstanceState)
        }
    }

    override fun observerViewModel() {
        viewModel.dataSetUp()
        collectDataSetUpDate()
    }

    private fun collectDataSetUpDate() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                dataSetUpState.collect { state ->
                    if (state.result != null) {
                        connectNavigation()
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun connectNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        val navController = navHostFragment.navController
        mBinding.mainBottomNav
            .setupWithNavController(navController)
    }

    fun setNavVisible(demand: Boolean) {
        mBinding.mainBottomNav.isInvisible = demand
    }

    fun moveHomeToSong() {
        mBinding.mainBottomNav.selectedItemId = R.id.main_song
    }

    fun moveHomeToMeal() {
        mBinding.mainBottomNav.selectedItemId = R.id.main_meal
    }
}
