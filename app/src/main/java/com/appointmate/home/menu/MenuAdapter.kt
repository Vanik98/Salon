package com.appointmate.home.menu

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.salon.databinding.ItemMenuBinding
import java.util.*

class MenuAdapter(val menuClick: (name: String) -> Unit) : RecyclerView.Adapter<MenuAdapter.BaseViewHolder>() {

    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private val namesList = mutableListOf<String>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DefaultCustomerViewHolder(ItemMenuBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind(namesList[position])

    override fun getItemCount() = namesList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(menuUpdateList: List<String>?) {
        namesList.clear()
        menuUpdateList?.let { namesList.addAll(it) }
        notifyDataSetChanged()
    }

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(name: String)
    }

    inner class DefaultCustomerViewHolder(private val binding: ItemMenuBinding) : BaseViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { menuClick(namesList[adapterPosition]) }
        }

        override fun bind(name: String) {
            binding.name = name
            binding.executePendingBindings()
        }
    }
}