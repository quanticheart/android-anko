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
 *  * Copyright(c) Developed by John Alves at 2020/1/25 at 7:7:58 for quantic heart studios
 *
 */

package com.quanticheart.anko.ankologger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.anko.R
import org.jetbrains.anko.*

class Logger : AppCompatActivity() {

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

    private val logger = AnkoLogger("tag")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logger.error("This is an error")  //Will Print E/YourActivity: This is an error
        logger.info("Log Info") //Will log I/MainActivity: Log Info
        logger.info {
            "Log Info" //Will log I/MainActivity: Log Info
        }
    }

    private fun logError() {
        logger.error("Log Error") //Will log E/MainActivity: Log Error
    }

    private fun logInfo() {
        logger.info("Log Info") //Will log I/MainActivity: Log Info

        logger.info {
            "Log Info" //Will log I/MainActivity: Log Info
        }
    }

    private fun logVerbose() {
        logger.verbose("Log Verbose") //Will log I/MainActivity: Log Verbose

        logger.verbose {
            "Log Verbose" //Will log I/MainActivity: Log Verbose
        }
    }

    private fun logDebug() {
        logger.debug("Log Debug") //Will log D/MainActivity: Log Debug

        logger.debug {
            "Log Debug" //Will log D/MainActivity: Log Debug
        }
    }

    private fun logWarn() {
        logger.warn("Log Warn") //Will log W/MainActivity: Log Warn

        logger.warn {
            "Log Warn" //Will log W/MainActivity: Log Warn
        }
    }
}
