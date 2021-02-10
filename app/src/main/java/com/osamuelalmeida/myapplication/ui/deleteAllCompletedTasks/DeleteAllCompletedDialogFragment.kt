package com.osamuelalmeida.myapplication.ui.deleteAllCompletedTasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.osamuelalmeida.myapplication.R
import com.osamuelalmeida.myapplication.databinding.DeleteAllCompletedDialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAllCompletedDialogFragment : DialogFragment() {

    private val viewModel: DeleteAllCompletedTasksViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.delete_all_completed_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DeleteAllCompletedDialogFragmentBinding.bind(view)

        binding.apply {
            btnNegative.setOnClickListener {
                findNavController().popBackStack()
            }
            btnPositive.setOnClickListener {
                viewModel.onConfirmClick()
                findNavController().popBackStack()
            }
        }
    }
}