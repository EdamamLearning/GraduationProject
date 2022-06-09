package ru.edamamlearning.graduationproject.utils

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.edamamlearning.graduationproject.databinding.ModalDialogBinding

open class ModalDialog : BottomSheetDialogFragment() {

    private var _binding: ModalDialogBinding? = null

    private val binding get() = _binding!!

    private var title = ""
    private var message = ""
    private var btnCount = 2
    private var btnTextFirst = ""
    private var btnTextSecond = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        title = requireArguments().getString("title").toString()
        message = requireArguments().getString("message").toString()
        btnCount = requireArguments().getInt("btnCount")
        btnTextFirst = requireArguments().getString("btnTextFirst").toString()
        btnTextSecond = requireArguments().getString("btnTextSecond").toString()

        _binding = ModalDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.message.text = message
        binding.title.text = title

        binding.firstButton.text = btnTextFirst
        binding.secondButton.text = btnTextSecond

        if (btnCount == 1) {
            binding.secondButton.isGone = true
        } else if (btnCount == 0) {
            binding.firstButton.isGone = true
            binding.secondButton.isGone = true
        } else if (btnCount == 3) {
            binding.firstButton.isGone = true
        }

        if (message == "") {
            binding.message.isGone = true
        }

        binding.firstButton.setOnClickListener {
            onFirstButtonClick()
        }

        binding.secondButton.setOnClickListener {
            onSecondButtonClick()
        }

        dialog!!.setOnCancelListener {
            onCancel()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    open fun onFirstButtonClick() {
        dismiss()
    }

    open fun onSecondButtonClick() {
        if (btnCount == 3) {
            dismiss()
        }
    }

    open fun onCancel() {}
}