package com.example.a4hw1

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.a4hw1.databinding.FragmentHome2Binding
import com.example.a4hw1.room.TaskModel


class HomeFragment : Fragment(), TaskClickListener {

    lateinit var binding: FragmentHome2Binding
    var taskAdapter = TaskAdapter(arrayListOf(), this)

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
        (requireActivity() as MainActivity).binding.titleTv.text = "Задачи"
        (requireActivity() as MainActivity).binding.profileImage.load(R.drawable.ic_person)
        initClicker()

        App.appDataDataBase.taskDao().getAll().observe(viewLifecycleOwner, Observer {
            taskAdapter = TaskAdapter(it, this)
            binding.recyclerView.adapter = taskAdapter
        })


    }

    private fun initClicker() {
        binding.createFab.setOnClickListener {
            CreateTaskFragment().show(requireActivity().supportFragmentManager, "")
        }
        (requireActivity() as MainActivity).binding.profileImage.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }
    }

    private fun showDialog(
        context: Context, title: String,
        negativeBtnClickListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setCancelable(true)

        builder.setPositiveButton("Да", negativeBtnClickListener)
        val alert = builder.create()
        alert.show()
        return alert
    }

    override fun itemClick(taskModel: TaskModel) {
        val dialog = CreateTaskFragment()
        val bundle = Bundle()
        bundle.putSerializable("model", taskModel)
        dialog.arguments = bundle
        dialog.show(requireActivity().supportFragmentManager, "update")
    }

    override fun deleteItemClick(taskModel: TaskModel) {
        showDialog(
            requireContext(),
            "Вы точно хотите удалить?",
            object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    App.appDataDataBase.taskDao().delete(taskModel)
                }

            })
    }
}