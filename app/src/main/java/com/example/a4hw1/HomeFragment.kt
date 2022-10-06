package com.example.a4hw1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a4hw1.databinding.FragmentHome2Binding
import com.example.a4hw1.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHome2Binding
    var taskAdapter = TaskAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHome2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showToolBar()
        initClicker()
        arguments?.let {
            val list = arrayListOf<TaskModel>()
            val model = it.getSerializable("model") as TaskModel
            list.add(model)
            taskAdapter = TaskAdapter(list)
binding.recyclerView.adapter = taskAdapter
        }
    }

    private fun initClicker() {
        binding.createFab.setOnClickListener {
            CreateTaskFragment().show(requireActivity().supportFragmentManager, "")
        }
    }
}