package com.appointmate.home.barber

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appointmate.base.utils.RealPathUtil
import com.appointmate.base.utils.constants.SalonConstants.EMPTY_STRING
import com.example.salon.databinding.FragmentBarberBinding

class BarberFragment : Fragment() {
    private lateinit var binding: FragmentBarberBinding
    private val reqCode = 1000
    var path: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBarberBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == reqCode) {
                val uri = data?.data
                binding.barberPhotoImageView.setImageURI(uri)
                path = uri?.let { RealPathUtil.getRealPath(requireContext(), it) }
            }
        }
    }

    private fun setupViews() {
        binding.barberPhotoImageView.setOnClickListener {
            val iGallery = Intent(Intent.ACTION_PICK)
            iGallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(iGallery, reqCode)
        }


//        binding.barberPhotoImageView.setOnClickListener{
//            val iGallery = Intent(Intent.ACTION_PICK)
//            iGallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            val resultLauncher = requireActivity().registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (result.resultCode == Activity.RESULT_OK) {
//                    binding.barberPhotoImageView.setImageURI(result.data?.data)
//                }
//            }
//            resultLauncher.launch(iGallery)
//        }
    }
}