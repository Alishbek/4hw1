package com.example.a4hw1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a4hw1.databinding.FragmentBoardBinding
import com.example.a4hw1.databinding.ItemBoardBinding


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
        list.add(BoardModel(R.drawable.board_first, "Экономь время", "Дальше"))
        list.add(BoardModel(R.drawable.board_second, "Достигай целей", "Дальше"))
        list.add(BoardModel(R.drawable.board_third, "Развивайся", "Начинаем"))
        val boardAdapter = BoardAdapter(list, this)
        binding.viewPager.adapter = boardAdapter
        binding.dotsIndicator.attachTo(binding.viewPager)
    }

    override fun itemClick() {
        findNavController().navigate(R.id.action_boardFragment_to_homeFragment)
    }


    override fun pageClick(number:Int) {
       binding.viewPager.setCurrentItem(number)
    }


}