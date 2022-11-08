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
        fun onClickMore(item: LostInfo, binding: ItemLostAndFoundBinding)
    }

    override fun action(item: LostInfo, binding: ItemLostAndFoundBinding) {
        binding.lostInfo = item
        if (item.member.id == item.myId)
            binding.ibBtnMore.visibility = View.VISIBLE
        else
            binding.ibBtnMore.visibility = View.GONE

        Glide.with(binding.ivProfileImage)
            .load(item.img)
            .centerCrop()
            .error(R.drawable.default_img)
            .into(binding.ivProfileImage)

        binding.root.setOnClickListener {
            listener.openComment(item.idx)
        }

        binding.ibBtnMore.setOnClickListener {
            listener.onClickMore(item, binding)
        }
    }

}
