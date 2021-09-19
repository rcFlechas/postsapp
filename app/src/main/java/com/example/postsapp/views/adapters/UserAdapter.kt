package com.example.postsapp.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import android.widget.Filter
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import com.example.postsapp.core.onClick
import com.example.postsapp.databinding.ItemUserBinding
import com.example.postsapp.views.binds.UserBind

class UserAdapter (
    val clickClosure: (UserBind) -> Unit,
    val listEmptyClosure: (Boolean) -> Unit
) : CustomAdapter<UserBind, UserAdapter.ViewHolder>(), Filterable {

    private var dataItems = arrayListOf<UserBind>()

    fun setData(usersBind: List<UserBind>) {
        dataItems.clear()
        dataItems.addAll(usersBind)
        elements.clear()
        elements.addAll(usersBind)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.dataItems.clear()
        this.elements.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)

        binding.btnViewPost.onClick {
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

    override fun getFilter(): Filter {

        return object : Filter() {

            override fun performFiltering(text: CharSequence?): FilterResults {

                val charSearch = text.toString().trim()
                elements = if (charSearch.isEmpty()) {
                    dataItems
                } else {
                    val resultList = ArrayList<UserBind>()
                    for (row in dataItems) {
                        if (row.name.contains(charSearch, true) && !resultList.contains(row)) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = elements
                return filterResults
            }

            override fun publishResults(text: CharSequence?, results: FilterResults?) {
                elements = results?.values as ArrayList<UserBind>
                notifyDataSetChanged()
                listEmptyClosure.invoke(elements.isEmpty())
            }
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