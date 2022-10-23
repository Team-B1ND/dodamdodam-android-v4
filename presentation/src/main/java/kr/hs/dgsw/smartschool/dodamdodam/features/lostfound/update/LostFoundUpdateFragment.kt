package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.update

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundUpdateBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.getRealPathFromURI
import java.io.File

@AndroidEntryPoint
class LostFoundUpdateFragment : BaseFragment<FragmentLostFoundUpdateBinding, LostFoundUpdateViewModel>() {
    override val viewModel: LostFoundUpdateViewModel by viewModels()
    private val args: LostFoundUpdateFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        viewModel.getLostFound(args.id)
        setImage(viewModel.url.value)
    }

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
            viewModel.modifyLostFound(args.id)
            findNavController().popBackStack()
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
            viewModel.file = File(uri?.path!!)
            setImage(url = viewModel.url.value, uri = it.data!!.data)
        }
    }
    private fun setImage(url: String?, uri: Uri?) {
        Glide.with(mBinding.root)
            .load(url ?: uri)
            .error(R.drawable.default_img)
            .centerCrop()
            .into(mBinding.ivLostFound)
    }
    private fun setImage(url: String?) {
        Glide.with(mBinding.root)
            .load(url)
            .error(R.drawable.default_img)
            .centerCrop()
            .into(mBinding.ivLostFound)
    }
}
