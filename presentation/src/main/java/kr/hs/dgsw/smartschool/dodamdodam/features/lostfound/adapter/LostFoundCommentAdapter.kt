package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter

import android.content.Context
import android.widget.PopupMenu
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.LostFoundCommentDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemLostFoundCommentBinding
import kr.hs.dgsw.smartschool.domain.model.lostfound.CommentInfo

class LostFoundCommentAdapter(val context: Context, val listener : CommentCallBack) : BaseListAdapter<CommentInfo, ItemLostFoundCommentBinding>(
    R.layout.item_lost_found_comment,
    LostFoundCommentDiffUtilCallback
){
    interface CommentCallBack {
        fun deleteComment(idx:Int)
        fun modifyComment(idx : Int)
    }
    override fun action(item: CommentInfo, binding: ItemLostFoundCommentBinding) {
        binding.info = item
        Glide.with(binding.ivProfileImage)
            .load(item.img)
            .centerCrop()
            .error(R.drawable.default_user)
            .into(binding.ivProfileImage)
        binding.btnMore.setOnClickListener{
            if(item.isMine){
                val pm = PopupMenu(context, binding.btnMore)
                pm.inflate(R.menu.lost_found_item_menu)
                pm.setOnMenuItemClickListener{ data ->
                    when (data.itemId) {
                        R.id.modify ->
                        {
                            listener.modifyComment(item.idx)
                            true
                        }
                        R.id.delete ->
                        {
                            listener.deleteComment(item.idx)
                            true
                        }
                        else -> false
                    }
                }
                pm.show()
            }else{
            }
        }
    }
}               