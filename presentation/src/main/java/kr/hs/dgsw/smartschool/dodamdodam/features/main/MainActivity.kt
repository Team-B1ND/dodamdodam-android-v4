package kr.hs.dgsw.smartschool.dodamdodam.features.main

import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun observerViewModel() {
        connectNavigation()
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