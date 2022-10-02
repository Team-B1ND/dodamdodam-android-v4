package kr.hs.dgsw.smartschool.dodamdodam.features.main

import android.content.Context
import android.graphics.Rect
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
    private val tempFileList: MutableList<String> = ArrayList()
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
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}
