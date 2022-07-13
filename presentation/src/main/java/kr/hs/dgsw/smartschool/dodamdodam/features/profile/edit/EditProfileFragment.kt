package kr.hs.dgsw.smartschool.dodamdodam.features.profile.edit

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.data.util.Constants
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentEditProfileBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.getExtension
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.getRealPathFromURI
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import java.io.File

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {
    override val viewModel: EditProfileViewModel by viewModels()
    private val args: EditProfileFragmentArgs by navArgs()
    private var isInit = false

    override fun observerViewModel() {
        if (!isInit)
            setInitValue()
        bindClickEvent()
        collectEditProfileResult()
        collectUploadImgResult()
    }

    private fun bindClickEvent() {
        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        mBinding.tvChangeToDefaultImage.setOnClickListener {
            viewModel.picture = Picture("", "", "")
        }
        mBinding.ivProfile.setOnClickListener {
            selectImage()
        }
        mBinding.btnAddImage.setOnClickListener {
            selectImage()
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startForResult.launch(intent)
    }

    private fun collectEditProfileResult() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                editProfileState.collect { state ->
                    if (state.message.isNotEmpty()) {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun collectUploadImgResult() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                uploadImageState.collect { state ->
                    if (state.picture != null) {
                        shortToast("이미지 업로드에 성공했습니다.")
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun setInitValue() {
        isInit = true
        with(viewModel) {
            email.value = args.email
            phone.value = args.phone
            memberId = args.memberId
            viewModel.picture = Picture(args.profileImage, args.profileImage, args.profileImage.getExtension())
        }

        setImage(args.profileImage)
    }

    private fun setImage(url: String) {
        Glide.with(mBinding.root)
            .load(url)
            .error(R.drawable.default_user)
            .centerCrop()
            .into(mBinding.ivProfile)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data?.getRealPathFromURI(context!!)
            viewModel.file = File(uri?.path!!)
            setImage()
            viewModel.uploadImg()
        }
    }
}