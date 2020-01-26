/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2020/1/25 at 6:48:6 for quantic heart studios
 *
 */

package com.quanticheart.anko.commons

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.anko.R
import org.jetbrains.anko.*

class ToastDialogSnackbar : AppCompatActivity() {

    /**
     * Anko é um conjunto de funções auxiliares (funções de extensão Kotlin )
     * que ajudam você a fazer algo com a menor quantidade de código padrão. Ele está subdividido
     * em módulos que ajudam você a lidar com layouts , SQLite e coroutines . Essa modularização
     * permite escolher apenas o que você precisa.
     */

    /**
     * Anko Commons : ajudantes para intenções, diálogos, registros;
     * Layouts Anko : permite criar layouts Android de maneira rápida e programática;
     * Anko SQLite : auxiliares para trabalhar com Android SQLite;
     * Anko Coroutines : utilitários para o uso de corotinas Kotlin.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Toast
     */

//    private fun showShortToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//
//    private fun showLongToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//    }

    private fun showShortToast(message: String) {
        toast(message)
    }

    private fun showLongToast(message: String) {
        longToast(message)
    }

    /**
     * Alerts
     */

    private fun showAlert(messageResource: Int, onYesTapped: () -> Unit, onNoTapped: () -> Unit) {
        alert(messageResource) {
            //1. Creating the alert
            yesButton {
                //2. Handling yes button (This is optional)
                onYesTapped()
            }
            noButton {
                //3. Handling no button (This is optional)
                onNoTapped()
            }
        }.show() //4. Showing the alert

        //Or

        alert("message").show()
        alert("message", "title").show() //The title is optional

        alert("message") {
            yesButton { } //Adds the default android.R.string.yes text to the button
            noButton { } //Adds the default android.R.string.no text to the button
        }.show()
    }

    /**
     * Selectors
     */

//    private fun showSelector(
//        title: CharSequence, items: List<CharSequence>, onClick: (DialogInterface, Int) -> Unit) {
//        val context = this
//
//        //1. Creating the AlertDialog
//        val alertBuilder = AlertDialog.Builder(context)
//
//        //2. Setting the title
//        alertBuilder.setTitle(title)
//
//        //3. Setting click handlers for each item of the list
//        alertBuilder.setItems(Array(items.size) { itemIndex -> items[itemIndex].toString() }) { dialog, which ->
//            onClick(dialog, which)
//        }.show()
//    }

    private fun showSelector(
        title: CharSequence, items: List<CharSequence>, onClick: (DialogInterface, Int) -> Unit) {
        selector(title,items,onClick)
    }

    /**
     * Progress dialogs
     */

//    private fun showLoadingDialog(message: String, title: String): ProgressDialog {
//        val dialog = ProgressDialog(this)
//        dialog.setMessage(message)
//        dialog.setTitle(title)
//        dialog.show()
//        return dialog
//    }

    private fun showLoadingDialog(message: String, title: String): ProgressDialog {
        val dialog = indeterminateProgressDialog(message, title) {
            //Do any customization to the dialog here
            show()
        }

        //or
//        progressDialog("message","title").show() //The title is optional
//
//        progressDialog("message") {
//            //configure the alert as you please
//            val drawable = getDrawable(R.drawable.spinner)
//            setTitle("title")
//            // Set the drawable to be used to display the progress value.
//            setProgressDrawable(drawable)
//            show()
//        }
//
//        indeterminateProgressDialog("message","title").show()
        return dialog
    }
}
