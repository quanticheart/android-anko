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
 *  * Copyright(c) Developed by John Alves at 2020/1/25 at 3:53:45 for quantic heart studios
 *
 */

package com.quanticheart.anko.commons

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.anko.MainActivity
import com.quanticheart.anko.R
import com.quanticheart.anko.others.entity.TestEntity
import org.jetbrains.anko.*

class Intents : AppCompatActivity() {

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
     * without Anko
     */
    private fun fun1(data: TestEntity) {
        // 1 Create the intent and specify the target activity
        val intent = Intent(this, MainActivity::class.java)

        // 2 details
        intent.putExtra("string", data.data1)
        intent.putExtra("int", data.data2)
        intent.putExtra("boolean", data.data3)
        intent.putExtra("serializable", data.data4)

        // 3. open the detail activity
        startActivity(intent)
    }

    /**
     * With Anko
     */
    private fun fun2(data: TestEntity) {
        startActivity<MainActivity>(
            "string" to data.data1,
            "int" to data.data2,
            "boolean" to data.data3,
            "serializable" to data.data4
        )

        //or without bundle
        startActivity<MainActivity>()
    }

    /**
     * Share
     */

    /**
     * without Anko
     */
    private fun shareAnime1() {
        val text = "$title"
        val subject = "Give a checkout to $title"

        //1. Creating the Intent
        val intent = Intent(Intent.ACTION_SEND)

        //2. Add extra data
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)

        //3. open a list of possible apps that can handle this intent
        startActivity(Intent.createChooser(intent, null))
    }

    /**
     * With Anko
     */
    private fun shareAnime2() {
        share("$title", "Give a checkout to $title")
    }

    /**
     * more
     */
    private fun others() {

        //Outras chamadas de Activitys
        //todas as chamadas retornam true caso as activitys foram abertas

        // Telefone
        val ok = makeCall("11999999999")

        //Enviar Texto
        sendSMS("11999999999")
        sendSMS("11999999999", "OI!")

        //Web
        browse("www.google.com")
        browse("www.google.com", true)

        //Compartilhar
        share("testando")
        share("testando", "app")

        //Email
        email("app@gmail.com")
        email("app@gmail.com", "app")
        email("app@gmail.com", "app", "oi!")
    }
}
