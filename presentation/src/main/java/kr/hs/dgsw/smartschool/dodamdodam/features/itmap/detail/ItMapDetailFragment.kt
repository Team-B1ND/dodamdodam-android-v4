package kr.hs.dgsw.smartschool.dodamdodam.features.itmap.detail

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentItmapDetailBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.detail.adapter.ItMapUserAdapter

@AndroidEntryPoint
class ItMapDetailFragment : BaseFragment<FragmentItmapDetailBinding, ItMapDetailViewModel>() {

    override val viewModel: ItMapDetailViewModel by viewModels()
    private val navArgs: ItMapDetailFragmentArgs by navArgs()

    private lateinit var itMapUserAdapter: ItMapUserAdapter

    override fun observerViewModel() {
        viewModel.getDetailCompanyInfo(navArgs.id)
        collectGetDetailCompanyState()
        setItMapUserAdapter()

        bindingViewEvent { event ->
            when (event) {
                ItMapDetailViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }
    }

    private fun collectGetDetailCompanyState() = lifecycleScope.launchWhenStarted {
        viewModel.getCompanyByIdState.collect { state ->
            if (state.company != null) {
                if (state.company.symbolLogo != null) {
                    setLogoImage(state.company.symbolLogo!!)
                } else if (state.company.textLogo != null) {
                    setLogoImage(state.company.textLogo!!)
                } else {
                    setLogoFirstWord()
                }
                mBinding.company = state.company
                itMapUserAdapter.submitList(state.company.users)
            }

            if (state.error.isNotBlank())
                findNavController().popBackStack()
        }
    }

    private fun setItMapUserAdapter() {
        itMapUserAdapter = ItMapUserAdapter {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it)
            startActivity(intent)
        }

        mBinding.rvItmapUser.adapter = itMapUserAdapter
    }

    private fun setLogoImage(url: String) {
        mBinding.layoutCompanyIcon.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.color_transparent)
        mBinding.tvCompanyIcon.visibility = View.INVISIBLE
        mBinding.ivCompanyLogo.visibility = View.VISIBLE

        Glide.with(mBinding.ivCompanyLogo.context)
            .load(url)
            .error(R.drawable.default_img)
            .into(mBinding.ivCompanyLogo)
    }

    private fun setLogoFirstWord() {
        mBinding.layoutCompanyIcon.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.color_itmap)
        mBinding.tvCompanyIcon.visibility = View.VISIBLE
        mBinding.ivCompanyLogo.visibility = View.INVISIBLE
    }
}
