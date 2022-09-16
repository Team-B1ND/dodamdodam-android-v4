package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.write

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundWriteBinding

@AndroidEntryPoint
class LostFoundWriteFragment : BaseFragment<FragmentLostFoundWriteBinding, LostFoundWriteViewModel>() {
    override val viewModel: LostFoundWriteViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener{
            this.findNavController().popBackStack()
        }
        mBinding.btnImageAdd.setOnClickListener {
            startDefaultGalleryApp()
        }
        mBinding.btnImageDelete.setOnClickListener {
        }
        with(viewModel){

        }
    }
    private fun startDefaultGalleryApp() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
