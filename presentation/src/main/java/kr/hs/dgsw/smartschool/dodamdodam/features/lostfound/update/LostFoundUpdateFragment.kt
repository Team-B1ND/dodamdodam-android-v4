package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.update

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundModifyBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.getRealPathFromURI
import java.io.File

@AndroidEntryPoint
class LostFoundUpdateFragment : BaseFragment<FragmentLostFoundModifyBinding, LostFoundUpdateViewModel>(),LostFoundUpdateViewModel.ImageCallBack {
    override val viewModel: LostFoundUpdateViewModel by viewModels()
    val args: LostFoundUpdateFragmentArgs by navArgs()
    private val lostFoundId : Int = args.id

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getLostFound(lostFoundId)
        return mBinding.root
    }

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener{
            this.findNavController().popBackStack()
        }
        mBinding.btnImageAdd.setOnClickListener {
            startGallery()
        }
        mBinding.btnImageDelete.setOnClickListener {
        }
        mBinding.fbAddLostAndFound.setOnClickListener{
            viewModel.modifyLostFound()
        }
    }

    private fun startGallery(){
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
            setImage(url = null, uri = it.data!!.data)
        }
    }
    private fun setImage(url: String?, uri: Uri?) {
        Glide.with(mBinding.root)
            .load(url ?: uri)
            .error(R.drawable.default_user)
            .centerCrop()
            .into(mBinding.ivLostFound)
    }

    override fun updateImage(image: String) {
        Glide.with(mBinding.root)
            .load(image)
            .error(R.drawable.default_user)
            .centerCrop()
            .into(mBinding.ivLostFound)
    }
}
