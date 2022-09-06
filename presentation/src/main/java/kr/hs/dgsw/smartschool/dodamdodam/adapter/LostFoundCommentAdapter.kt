package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.LostFoundCommentDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.LostFoundDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemLostAndFoundBinding
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemLostFoundCommentBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.LostFoundFragment
import kr.hs.dgsw.smartschool.domain.model.lostfound.CommentInfo
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

class LostFoundCommentAdapter(val context: Context) : BaseListAdapter<CommentInfo, ItemLostFoundCommentBinding>(
    R.layout.item_lost_found_comment,
    LostFoundCommentDiffUtilCallback
){
    override fun action(item: CommentInfo, binding: ItemLostFoundCommentBinding) {
        binding.info = item
        Glide.with(binding.ivProfileImage)
            .load(item.img)
            .centerCrop()
            .error(R.drawable.default_user)
            .into(binding.ivProfileImage)
    }
}               