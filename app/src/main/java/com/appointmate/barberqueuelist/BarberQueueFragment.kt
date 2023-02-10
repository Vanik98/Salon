package com.appointmate.barberqueuelist

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
import com.github.mikephil.charting.data.PieEntry
import com.google.android.gms.maps.model.LatLng
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
        binding.barberQueueListRecyclerView.addItemDecoration(verticalDecoration)
        binding.barberQueueListRecyclerView.addItemDecoration(horizontalDecoration)
        binding.barberQueueListRecyclerView.layoutManager = GridLayoutManager(context, 6,GridLayoutManager.VERTICAL,false)
        binding.barberQueueListRecyclerView.adapter = adapter
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