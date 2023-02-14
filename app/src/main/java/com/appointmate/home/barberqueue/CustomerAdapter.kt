package com.appointmate.home.barberqueue

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appointmate.customer.CustomerDto
import com.example.salon.databinding.ItemCoustomerBinding
import java.util.*

internal class CustomerAdapter(val customerClick: (customerDto: CustomerDto) -> Unit) : RecyclerView.Adapter<CustomerAdapter.BaseViewHolder>() {

    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater
    private val customers = mutableListOf<CustomerDto>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DefaultCustomerViewHolder(ItemCoustomerBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind(customers[position])

    override fun getItemCount() = customers.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(customersUpdateList: List<CustomerDto>?) {
        customers.clear()
        customersUpdateList?.let { customers.addAll(it) }
        notifyDataSetChanged()
    }

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(customerDto: CustomerDto)
    }

    inner class DefaultCustomerViewHolder(private val binding: ItemCoustomerBinding) : BaseViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { customerClick(customers[adapterPosition]) }
        }

        override fun bind(customerDto: CustomerDto) {
            binding.customer = customerDto
            binding.executePendingBindings()
        }
    }
}