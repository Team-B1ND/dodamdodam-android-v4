package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemLostAndFoundBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter.callback.LostFoundDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

class LostFoundAdapter(val context: Context, val listener: LostFoundCallBack) : BaseListAdapter<LostInfo, ItemLostAndFoundBinding>(R.layout.item_lost_and_found, LostFoundDiffUtilCallback) {
    interface LostFoundCallBack {
        fun openComment(idx: Int)
        fun modifyLostFound(idx: Int)
        fun deleteLostFound(idx: Int)
    }

    init {
        Log.d("LostFoundAdapter", "생성됨")
    }

    override fun action(item: LostInfo, binding: ItemLostAndFoundBinding) {
        binding.lostInfo = item
        if (item.member.id != item.myId) binding.ibBtnMore.visibility = View.INVISIBLE
        Log.e("LostFoundAdapter", item.toString())
        Glide.with(binding.ivProfileImage)
            .load(item.img)
            .centerCrop()
            .error(R.drawable.default_img)
            .into(binding.ivProfileImage)

        binding.root.setOnClickListener {
            listener.openComment(item.idx)
        }
        binding.ibBtnMore.setOnClickListener {
            if (item.member.id == item.myId) {
                val pm = PopupMenu(context, binding.ibBtnMore)
                pm.inflate(R.menu.lost_found_item_menu)
                pm.setOnMenuItemClickListener { data ->
                    when (data.itemId) {
                        R.id.modify ->
                            {
                                listener.modifyLostFound(item.idx)
                                true
                            }
                        R.id.delete ->
                            {
                                listener.deleteLostFound(item.idx)
                                true
                            }
                        else -> false
                    }
                }
                pm.show()
            } else {
                Log.d("LostFoundAdapter", "자신의 게시물이 아님")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return super.onCreateViewHolder(parent, viewType)
    }

    // 아이템뷰에 프로그레스바가 들어가는 경우
    inner class LoadingViewHolder(private val binding: ItemLostAndFoundBinding) :
        RecyclerView.ViewHolder(binding.root)
}
