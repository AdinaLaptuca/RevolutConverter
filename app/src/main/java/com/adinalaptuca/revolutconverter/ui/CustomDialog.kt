package com.adinalaptuca.revolutconverter.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.adinalaptuca.revolutconverter.R
import kotlinx.android.synthetic.main.custom_dialog.*

/**
 * Created by adinalaptuca on Aug, 2019
 */

class CustomDialog(val context: Context) {

    private var saveListener: SaveClickListener? = null
    private var cancelListener: CancelListener? = null
    private var dialogDismissListener: DismissListener? = null

    var canceledOnTouch = true
    var positiveButtonDrawable: Int? = null

    fun showDialog(title: String, text: String, positiveButton: String, negativeButton: String? = null, positiveButtonBackground: Int? = null) {

        Dialog(context, R.style.FullScreenDialogStyle).apply {
            setContentView(R.layout.custom_dialog)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (!title.isEmpty()) dialog_title.text = title else dialog_title.visibility = View.GONE

            if (!text.isEmpty()){
                dialog_text.text = text
            }
            else dialog_text.visibility = View.GONE

            if (!negativeButton.isNullOrEmpty()) cancel_button.text = negativeButton else cancel_button.visibility = View.GONE
            if (!positiveButton.isEmpty()) save_button.text = positiveButton else save_button.visibility = View.GONE
            positiveButtonBackground?.let { save_button.setBackgroundResource(it) }

            cancel_button.setOnClickListener {
                dismiss()
                cancelListener?.dismiss()
            }

            positiveButtonDrawable?.let { save_button?.setBackgroundResource(it) }
            save_button.setOnClickListener{
                dismiss()
                saveListener?.save()
            }

            setOnDismissListener{ dialogDismissListener?.dismiss() }

            setCancelable(canceledOnTouch)
            show()

            parent.setOnClickListener {
                if(canceledOnTouch){
                    dismiss()
                }
            }
        }
    }

    interface CancelListener{
        fun dismiss()
    }

    fun setCanceledOnTouchOut(isCanceledOnTouch : Boolean){
        canceledOnTouch = isCanceledOnTouch
    }

    fun setNegativeClickLister(cancelListener: CancelListener) {
        this.cancelListener = cancelListener
    }

    interface SaveClickListener{
        fun save()
    }

    fun setPositiveButtonListener(saveListener: SaveClickListener) {
        this.saveListener = saveListener
    }

    interface DismissListener{
        fun dismiss()
    }

    fun setDismissListener(dismissListener: DismissListener) {
        this.dialogDismissListener = dismissListener
    }
}