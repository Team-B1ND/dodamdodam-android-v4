package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.update

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundUpdateBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.update.LostFoundUpdateViewModel.Companion.EVENT_ERROR
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.update.LostFoundUpdateViewModel.Companion.EVENT_LOAD_IMG
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.getRealPathFromURI
import java.io.File

@AndroidEntryPoint
class LostFoundUpdateFragment : BaseFragment<FragmentLostFoundUpdateBinding, LostFoundUpdateViewModel>() {
    override val viewModel: LostFoundUpdateViewModel by viewModels()
    private val args: LostFoundUpdateFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        viewModel.getLostFound(args.id)
    }

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when(event){
                EVENT_LOAD_IMG -> {
                    setImage(viewModel.url)
                }
            }
        }

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
            val uri =  it.data?.data?.getRealPathFromURI(requireContext())
            setImage(uri = it.data!!.data)
            viewModel.imageUpload(File(uri?.path!!))
            Log.e("LostFoundWriteFragment","${File(uri.path!!)}")
        }
    }
    private fun setImage(uri: Uri?) {
        Glide.with(mBinding.root)
            .load(uri)
            .error(R.drawable.default_img)
            .centerCrop()
            .into(mBinding.ivLostFound)
    }
    private fun setImage(url: String?) {
        Glide.with(mBinding.ivLostFound)
            .load(url)
            .centerCrop()
            .error(R.drawable.default_img)
            .into(mBinding.ivLostFound)
    }
}
