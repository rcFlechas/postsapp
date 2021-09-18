package com.example.postsapp.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import com.example.postsapp.core.onClick
import com.example.postsapp.databinding.ItemUserBinding
import com.example.postsapp.views.binds.UserBind

class UserAdapter (
    val clickClosure: (UserBind) -> Unit
) : CustomAdapter<UserBind, UserAdapter.ViewHolder>() {

    private var dataItems = arrayListOf<UserBind>()

    fun setData(usersBind: List<UserBind>) {
        dataItems.clear()
        dataItems.addAll(usersBind)
        elements.clear()
        elements.addAll(usersBind)
        notifyDataSetChanged()
    }

    fun getData(): MutableList<UserBind> {
        return dataItems
    }

    fun clearData() {
        this.dataItems.clear()
        this.elements.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)

        binding.root.onClick {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@onClick
            clickClosure(elements[position])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return elements.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val userBind = elements[position]

        when (holder) {
            is ViewHolder -> holder.bind(userBind)
        }
    }

    inner class ViewHolder(private val binding: ItemUserBinding) : BaseViewHolder<UserBind>(binding.root) {

        override fun bind(item: UserBind) {
            binding.name.text = item.name
            binding.phone.text = item.phone
            binding.email.text = item.email
        }
    }
}