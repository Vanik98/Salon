package com.appointmate.home.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.appointmate.base.utils.SizeUtils
import com.appointmate.base.utils.extentions.navigateSafe
import com.appointmate.base.utils.helper.helper.VerticalSpacesItemDecoration
import com.appointmate.home.Fragment2MainTab
import com.appointmate.home.salon.SalonFragment
import com.appointmate.home.salon.SalonFragmentDirections
import com.example.salon.databinding.FragmentMenuMainTabBinding

class MenuMainTabFragment : Fragment() {
    private lateinit var binding: FragmentMenuMainTabBinding

    private val adapter: MenuAdapter = MenuAdapter {
        when(MenuItemEnum.from(it)){
            MenuItemEnum.ADMINISTRATOR->{
                findNavController().navigateSafe(SalonFragmentDirections.actionGlobalSalonFragment())
            }
            MenuItemEnum.SETTINGS->{}
            else -> {}
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuMainTabBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews(){
        val verticalDecoration = VerticalSpacesItemDecoration(SizeUtils.dp2px(requireActivity(), 3f))
        binding.menuRecyclerView.addItemDecoration(verticalDecoration)
        binding.menuRecyclerView.adapter = adapter
        adapter.updateData(arrayListOf("settings","administrator"))
    }
}