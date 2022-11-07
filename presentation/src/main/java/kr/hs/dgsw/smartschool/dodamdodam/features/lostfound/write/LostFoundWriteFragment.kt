package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.write

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundWriteBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.getRealPathFromURI
import java.io.File

@AndroidEntryPoint
class LostFoundWriteFragment : BaseFragment<FragmentLostFoundWriteBinding, LostFoundWriteViewModel>() {
    override val viewModel: LostFoundWriteViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            this.findNavController().popBackStack()
        }
        mBinding.btnImageAdd.setOnClickListener {
            startGallery()
        }
        mBinding.btnImageDelete.setOnClickListener {
        }
        mBinding.btnAdd.setOnClickListener {
            Log.d("LostFoundWriteFragment", "addLostFound()")
            viewModel.addLostFound()
            this.findNavController().popBackStack()
        }
    }

    private fun startGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            "image/*"
        )
        startForResult.launch(intent)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data?.getRealPathFromURI(requireContext())
            setImage(url = null, uri = it.data!!.data)
            viewModel.imageUpload(File(uri?.path!!))
            Log.e("LostFoundWriteFragment", "${File(uri.path!!)}")
        }
    }
    private fun setImage(url: String?, uri: Uri?) {
        Glide.with(mBinding.root)
            .load(url ?: uri)
            .error(R.drawable.default_img)
            .centerCrop()
            .into(mBinding.ivLostFound)
    }
}
