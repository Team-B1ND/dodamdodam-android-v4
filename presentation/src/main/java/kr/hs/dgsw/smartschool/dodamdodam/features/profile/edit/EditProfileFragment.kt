package kr.hs.dgsw.smartschool.dodamdodam.features.profile.edit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
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
        initViewEvent()
    }

    private fun bindClickEvent() {
        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        mBinding.tvChangeToDefaultImage.setOnClickListener {
            viewModel.picture = Picture("", "", "")
            setImage(url = "", uri = null)
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
                        shortToast(state.message)
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
                    if (state.url.isNotBlank()) {
                        shortToast("이미지 업로드에 성공했습니다.")
                        viewModel.url.value = state.url
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
            url.value = args.profileImage
            viewModel.picture = Picture(args.profileImage, args.profileImage, args.profileImage.getExtension())
        }

        setImage(args.profileImage, null)
    }

    private fun setImage(url: String?, uri: Uri?) {
        Glide.with(mBinding.root)
            .load(url ?: uri)
            .error(R.drawable.default_user)
            .centerCrop()
            .into(mBinding.ivProfile)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data?.getRealPathFromURI(requireContext())
            setImage(url = null, uri = it.data!!.data)
            viewModel.uploadImg(File(uri?.path ?: return@registerForActivityResult))
        }
    }

    private fun initViewEvent() {
        bindingViewEvent { event ->
            when (event) {
                EditProfileViewModel.EVENT_EMPTY -> shortToast("입력란을 모두 채워 주세요.")
                EditProfileViewModel.EVENT_NOT_PHONE_NUMBER -> shortToast("전화번호 형식이 일치하지 않습니다.")
                EditProfileViewModel.EVENT_NOT_MATCH_FORM -> shortToast("형식이 일치하지 않습니다.")
                EditProfileViewModel.EVENT_NOT_EMAIL -> shortToast("이메일 형식이 일치하지 않습니다.")
            }
        }
    }
}
