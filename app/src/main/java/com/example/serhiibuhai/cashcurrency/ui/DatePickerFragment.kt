package com.example.serhiibuhai.cashcurrency.ui


import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        private const val DIALOG_ID = "dialog_id"

        fun newDatePickerFragment(dialogId: Int): DatePickerFragment {
            val newFragment = DatePickerFragment()
            newFragment.arguments = Bundle().apply {
                putInt(DIALOG_ID, dialogId)
            }
            return newFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c: Calendar = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(activity, this, year, month, day)

    }

    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        activity?.let {
            if (it.isFinishing.not() && it is OnDatePickedListener) {
                val dialogId = arguments?.getInt(DIALOG_ID, -1) ?: -1
                it.processPBDatePickerResult(year, month, dayOfMonth, dialogId)
            }
        }
    }

}
