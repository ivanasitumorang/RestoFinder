package com.azuka.base.presentation.widget

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.azuka.base.R


/**
 * Created by ivanaazuka on 30/09/20.
 * Android Engineer
 */

class LoadingDialog : DialogFragment() {

    companion object {
        private const val TAG = "loading-dialog"
        fun newInstance(): LoadingDialog {
            return LoadingDialog()
        }
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.apply {
            setCanceledOnTouchOutside(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.loading_dialog, container)
    }

    fun show(fragmentManager: FragmentManager) {
        if (!isAdded) {
            showNow(fragmentManager, TAG)
        }
    }

    fun hide() {
        if (isAdded || isVisible) {
            dismiss()
        }
    }

}