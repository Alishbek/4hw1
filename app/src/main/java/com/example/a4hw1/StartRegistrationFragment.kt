package com.example.a4hw1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a4hw1.databinding.FragmentStartRegistrationBinding


class StartRegistrationFragment : Fragment() {
    lateinit var binding: FragmentStartRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = requireContext().getSharedPreferences("setting", Context.MODE_PRIVATE)
        val isRegistered: Boolean = preferences.getBoolean("isRegistered", false)
        if (isRegistered) {
            findNavController().navigate(R.id.homeFragment)
        } else {
            binding.registrationBtn.setOnClickListener {
                findNavController().navigate(R.id.registrationNumberFragment)
            }
        }
    }


}