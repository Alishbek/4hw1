package com.example.a4hw1

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.a4hw1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    private val pickImage = 100
    private var imageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).binding.titleTv.text = "Профиль"
        (requireActivity() as MainActivity).binding.profileImage.load(R.drawable.ic_task)
        (requireActivity() as MainActivity).binding.profileImage.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

//        val preferences = requireContext().getSharedPreferences("gallery", Context.MODE_PRIVATE)
//        val isGalleryUsed: Boolean = preferences.getBoolean("isGalleryUsed", false)
//        if (isGalleryUsed) {
//            binding.profileImage.setImageURI((preferences.getString("Uri", "")))
//        }
        binding.profileImage.setOnClickListener {

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
//            val preferences = requireContext().getSharedPreferences("gallery", Context.MODE_PRIVATE)
//            preferences.edit().putBoolean("isGalleryUsed", true).apply()
//            preferences.edit().putString("Uri", (imageUri.toString())).apply()
            imageUri = data?.data
            binding.profileImage.setImageURI(imageUri)

        }
    }

}