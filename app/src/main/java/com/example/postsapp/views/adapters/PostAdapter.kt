package com.example.postsapp.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.databinding.ItemPostBinding
import com.example.postsapp.views.binds.PostBind

class PostAdapter: RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var dataItems = arrayListOf<PostBind>()

    fun setData(postsBind: List<PostBind>) {
        dataItems.clear()
        dataItems.addAll(postsBind)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.dataItems.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val postBind = dataItems[position]
        when (holder) {
            is PostAdapter.ViewHolder -> holder.bind(postBind)
        }
    }

    override fun getItemCount() = dataItems.count()

    inner class ViewHolder(private val binding: ItemPostBinding) : BaseViewHolder<PostBind>(binding.root) {

        override fun bind(item: PostBind) {
            binding.title.text = item.title
            binding.body.text = item.body
        }
    }
}