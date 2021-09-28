package es.jco.demo.presentation.common

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import kotlin.reflect.KMutableProperty1

/**
 * Inflate layouts
 *
 * @param id
 * @return
 */
fun ViewGroup.inflate(@LayoutRes id: Int): View = LayoutInflater.from(this.context).inflate(id, this, false)

/**
 * Show error dialog
 *
 * @param message
 * @param acceptButton
 * @param acceptListener
 */
fun Context.showErrorDialog(message: Int, acceptButton: Int,  acceptListener: DialogInterface.OnClickListener) = AlertDialog.Builder(this)
    .setMessage(message)
    .setPositiveButton(acceptButton, acceptListener)
    .setCancelable(false)
    .create()
    .show()

/**
 * Binding String to TextInputEditText with validations
 *
 * @param T
 * @param property
 * @param reference
 * @param errorMessage
 */
@JvmName("bindingString")
fun <T : Any> TextInputEditText.binding(property: KMutableProperty1<T, String?>, reference: T?, errorMessage: String){
    this.addTextChangedListener {
        doOnTextChanged { text, _, _, _ ->
            run {
                if (text.toString().isNotEmpty()) {
                    this.error = null
                    property.setter.call(reference, text.toString())
                } else {
                    this.error = errorMessage
                }
            }
        }
    }
}

/**
 * Binding String to TextInputEditText
 *
 * @param T
 * @param property
 * @param reference
 */
@JvmName("bindingString")
fun <T : Any> TextInputEditText.binding(property: KMutableProperty1<T, String?>, reference: T?){
    this.addTextChangedListener {
        doOnTextChanged { text, _, _, _ -> property.setter.call(reference, text.toString()) }
    }
}

/**
 * Binding Double to TextInputEditText
 *
 * @param T
 * @param property
 * @param reference
 */
@JvmName("bindingDouble")
fun <T : Any> TextInputEditText.binding(property: KMutableProperty1<T, Double?>, reference: T?){
    this.addTextChangedListener {
        doOnTextChanged { text, _, _, _ -> try {property.setter.call(reference, text.toString().toDouble()) } catch (e: Exception) { property.setter.call(reference, null)} }
    }
}