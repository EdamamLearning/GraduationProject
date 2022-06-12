package ru.edamamlearning.graduationproject.ui.datepickerdialogfragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = GregorianCalendar().get(GregorianCalendar.YEAR)
        val month = GregorianCalendar().get(GregorianCalendar.MONTH)
        val day = GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        setFragmentResult(
            REQUEST_KEY,
            bundleOf(KEY_RESPONSE to intArrayOf(year, month, dayOfMonth))
        )
    }

    companion object {

        @JvmStatic
        val REQUEST_KEY = "DatePickerDialogFragment:defaultRequestKey"

        @JvmStatic
        val KEY_RESPONSE = "RESPONSE"
    }
}