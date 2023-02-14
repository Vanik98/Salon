package com.appointmate.home.barberqueuelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.appointmate.BaseFragment
import com.appointmate.base.utils.SizeUtils
import com.appointmate.base.utils.helper.helper.HorizontalSpacesItemDecoration
import com.appointmate.base.utils.helper.helper.VerticalSpacesItemDecoration
import com.appointmate.customer.CustomerDto
import com.example.salon.R
import com.example.salon.databinding.FragmentBarberQueueBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BarberQueueFragment : BaseFragment() {

    private lateinit var binding: FragmentBarberQueueBinding
    private val viewModel by viewModel<BarberQueueViewModel>()

    private val adapter: CustomerAdapter = CustomerAdapter {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_barber_queue, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        val verticalDecoration = VerticalSpacesItemDecoration(SizeUtils.dp2px(requireActivity(), 3f))
        val horizontalDecoration = HorizontalSpacesItemDecoration(SizeUtils.dp2px(requireActivity(), 3f))
        binding.queueVisualRecyclerView.addItemDecoration(verticalDecoration)
        binding.queueVisualRecyclerView.addItemDecoration(horizontalDecoration)
        binding.queueVisualRecyclerView.layoutManager = GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false)
        binding.queueVisualRecyclerView.adapter = adapter
        adapter.updateData(
            arrayListOf(
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
                CustomerDto("11:30"),
            )
        )
    }

}