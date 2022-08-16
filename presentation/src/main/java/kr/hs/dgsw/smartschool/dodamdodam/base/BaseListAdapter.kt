package kr.hs.dgsw.smartschool.dodamdodam.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T : Any, B: ViewDataBinding>(
    @LayoutRes private val itemLayoutRes: Int,
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseListAdapter<T, B>.BaseViewHolder>(diffUtil) {

    inner class BaseViewHolder(private val binding: B) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            action(item, binding)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                itemLayoutRes,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    abstract fun action(item: T, binding: B)
}