package com.example.a4hw1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a4hw1.databinding.FragmentBoardBinding
import com.example.a4hw1.databinding.ItemBoardBinding
import com.example.a4hw1.room.TaskModel


class BoardFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideToolBar()
        val list = arrayListOf<BoardModel>()
        list.add(BoardModel("first_anim.json", "Экономь время", "Дальше"))
        list.add(BoardModel("second_anim.json", "Достигай целей", "Дальше"))
        list.add(BoardModel("third_anim.json", "Развивайся", "Начинаем"))
        val boardAdapter = BoardAdapter(list, this)
        binding.viewPager.adapter = boardAdapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        val preferences = requireContext().getSharedPreferences("setting", Context.MODE_PRIVATE)
        val isShow: Boolean = preferences.getBoolean("isShow", false)
        if (isShow){
            findNavController().navigate(R.id.action_boardFragment_to_homeFragment)
        }
    }

    override fun itemClick() {
        val preferences = requireContext().getSharedPreferences("setting", Context.MODE_PRIVATE)
        preferences.edit().putBoolean("isShow", true).apply()
        findNavController().navigate(R.id.action_boardFragment_to_homeFragment)
    }


    override fun pageClick(number: Int) {
        binding.viewPager.setCurrentItem(number)
    }


}