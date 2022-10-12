package com.example.a4hw1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a4hw1.databinding.ItemBoardBinding

class BoardAdapter(
    private val list: ArrayList<BoardModel>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {
    inner class BoardViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(boardModel: BoardModel) {
            binding.nextBtn.text = boardModel.button
            binding.descriptionView.text = boardModel.description
            binding.lottieView.setAnimation(boardModel.animation)
            binding.nextBtn.setOnClickListener {
                if (adapterPosition == list.size - 1) {
                    listener.itemClick()
                } else if (adapterPosition == list.size - 2) {
                    listener.pageClick(2)
                } else if (adapterPosition == list.size - 3) {
                    listener.pageClick(1)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}