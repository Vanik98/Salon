package com.appointmate.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appointmate.barber.net.BarberItemDto
import com.example.salon.databinding.ItemBarberBinding
import java.util.*

class BarberAdapter(val barberItemClick: (barberItemDto: BarberItemDto) -> Unit) : RecyclerView.Adapter<BarberAdapter.BaseViewHolder>() {

    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private val barbers = mutableListOf<BarberItemDto>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DefaultBarberViewHolder(ItemBarberBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind(barbers[position])

    override fun getItemCount() = barbers.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(barberUpdateList: List<BarberItemDto>?) {
        barbers.clear()
        barberUpdateList?.let { barbers.addAll(it) }
        notifyDataSetChanged()
    }

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(barberItemDto: BarberItemDto)
    }

    inner class DefaultBarberViewHolder(private val binding: ItemBarberBinding) : BaseViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { barberItemClick(barbers[adapterPosition]) }
        }

        override fun bind(barberItemDto: BarberItemDto) {
            binding.barber = barberItemDto
            binding.executePendingBindings()
        }
    }
}