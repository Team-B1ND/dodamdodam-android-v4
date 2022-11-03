package kr.hs.dgsw.smartschool.dodamdodam.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.dodamdodam.BR
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.start.StartActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll
import kr.hs.dgsw.smartschool.domain.util.Utils
import java.lang.reflect.ParameterizedType
import java.util.Locale
import java.util.Objects

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    protected abstract fun observerViewModel()

    protected fun bindingViewEvent(action: (event: Any) -> Unit) {
        viewModel.viewEvent.observe(this) {
            it.getContentIfNotHandled()?.let { event ->
                action.invoke(event)
            }
        }

        viewModel.tokenErrorEvent.observe(this) {
            if (it == Utils.TOKEN_EXCEPTION) {
                shortToast("세션이 만료되었습니다.")
                SharedPreferenceManager.logout(this)
                startActivityWithFinishAll(StartActivity::class.java)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
        observerViewModel()
    }

    private fun performDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layoutRes())
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.vm, mViewModel)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBinding.isInitialized) mBinding.unbind()
    }

    /**
     * Generic Type (Binding) class 를 가져와서 layout 파일명으로 변환 후 자동으로 Layout Resource 를 가져옴
     *
     * @return layout resource
     */
    @LayoutRes
    private fun layoutRes(): Int {
        val split = (
            (Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType)
                .actualTypeArguments[0] as Class<*>
            )
            .simpleName.replace("Binding$".toRegex(), "")
            .split("(?<=.)(?=\\p{Upper})".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()

        val name = StringBuilder()

        for (i in split.indices) {
            name.append(split[i].lowercase(Locale.ROOT))
            if (i != split.size - 1)
                name.append("_")
        }

        try {
            return R.layout::class.java.getField(name.toString()).getInt(R.layout::class.java)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }

        return 0
    }
}
