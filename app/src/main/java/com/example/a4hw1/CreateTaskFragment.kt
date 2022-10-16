package com.example.a4hw1

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.fragment.findNavController
import com.example.a4hw1.databinding.FragmentCreateTask2Binding
import com.example.a4hw1.databinding.RegularDialogBinding
import com.example.a4hw1.room.TaskModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import okhttp3.internal.concurrent.Task
import java.util.*


class CreateTaskFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentCreateTask2Binding
    var task = ""
    var date = ""
    var regular = ""
    var taskModel: TaskModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTask2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
        if (tag == "update") {
            arguments?.let {
                taskModel = it.getSerializable("model") as TaskModel

                binding.taskEd.setText(taskModel!!.task)
                binding.dateBtn.text = taskModel!!.date
                binding.regularBtn.text = taskModel!!.regular
            }
        }
    }

    private fun showRegularDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = RegularDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        binding.everyDayBtn.setOnClickListener {
            regular = binding.everyDayBtn.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
        }
        binding.everyWeekBtn.setOnClickListener {
            regular = binding.everyWeekBtn.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
        }
        binding.everyMonthBtn.setOnClickListener {
            regular = binding.everyMonthBtn.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
        }
        binding.everyYearBtn.setOnClickListener {
            regular = binding.everyYearBtn.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
        }
        binding.cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun initClicker() {
        with(binding) {
            applyBtn.setOnClickListener {
                if (tag == "update") {
                    val model = TaskModel(
                        id = taskModel!!.id,
                        task = taskEd.text.toString(),
                        date = dateBtn.text.toString(),
                        regular = regularBtn.text.toString()
                    )
                    App.appDataDataBase.taskDao().update(model)
                } else {
                    val model =
                        TaskModel(task = taskEd.text.toString(), date = date, regular = regular)
                    App.appDataDataBase.taskDao().insert(model)

                }
                dismiss()
            }
            regularBtn.setOnClickListener {
                showRegularDialog()
            }
            dateBtn.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)


                val dpd = DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                        // Display Selected date in textbox
                        dateBtn.text = "$dayOfMonth.${monthOfYear + 1}.$year"
                        date = dateBtn.text.toString()
                    },
                    year,
                    month,
                    day
                )

                dpd.show()
            }
        }
    }
}