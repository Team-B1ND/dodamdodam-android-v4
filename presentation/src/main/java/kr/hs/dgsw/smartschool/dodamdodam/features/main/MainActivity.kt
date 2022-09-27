package kr.hs.dgsw.smartschool.dodamdodam.features.main

import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivityMainBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

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

    /*override fun onResume() {
        super.onResume()

        val appWidgetManager: AppWidgetManager? = getSystemService(AppWidgetManager::class.java)
        val myProvider = ComponentName(this, MealWidgetProvider::class.java)

        val successCallback: PendingIntent? = if (
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
               appWidgetManager!!.isRequestPinAppWidgetSupported
            else
                return
        ) {
            Intent(this, MainActivity::class.java).let { intent ->
                PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            }
        } else {
            null
        }
    }*/

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
