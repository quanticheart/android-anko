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
 *  * Copyright(c) Developed by John Alves at 2020/1/30 at 1:27:26 for quantic heart studios
 *
 */

package com.quanticheart.anko.ThreadFunctions

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.onComplete
import org.jetbrains.anko.uiThread

class ThreadFragment : Fragment() {

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

    /**
     * https://medium.com/@v.souhrada/introduction-to-anko-for-android-part-2-9ba043baf9c0
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Aqui, você pode ler ou gravar em arquivos grandes, fazer download de um arquivo da Internet ou
     * executar uma tarefa que levará muito tempo para ser concluída. Este bloco será executado
     * em um thread em segundo plano
     */
    private fun background() {
        doAsync {
            // in background
        }
    }

    /**
     * Sempre que você precisar voltar ao thread da interface do usuário,
     * faça-o dentro do bloco activityUiThread O exemplo de código a seguir mostra uma lista
     * completa de uma atividade de amostra que usa o doAsync da Anko para executar um cálculo
     * longo e, em seguida, escreva algo de volta para a UI MainActivity, anotada código.
     */

    private fun backgroundToFront() {
        doAsync {
            // in background
            uiThread {
                // in front
            }
        }
    }

    private fun backgroundToFront2() {
        doAsync {
            uiThread {
                // in front
            }
            onComplete {

            }
        }
    }

    /**
    (1) Vamos configurar um OnClickListener básico. Isso acionará a tarefa em segundo plano

    (2) Vamos contar de 1 a 15

    (3) Isso simula uma tarefa de longa duração, nosso loop será realizado cerca de 15 vezes; portanto, a tarefa levará um total de 30 segundos para ser concluída.

    (4) Vamos dizer ao usuário o que está acontecendo com o aplicativo. Atualize o objeto TextView com o valor atual de i
     */

    private fun backgroundToFront3(button: Button, textView: TextView) {
        button.setOnClickListener {
            // (1)
            doAsync {
                for (i in 1..15) {                   // (2)
                    Thread.sleep(2000)                // (3)
                    uiThread {
                        textView.text = i.toString()    // (4)
                    }
                }
            }
        }
    }

    /**
     * doAsync é usado para executar código em um encadeamento diferente, mas não retorna
     * nada ao encadeamento principal quando concluído.

     * doAsyncResult é usado para executar uma atividade em um encadeamento separado e
     * executar uma operação no encadeamento principal após concluir a execução no encadeamento separado.
     */

    /**
     * Nota: uiThread()atua de maneira bastante diferente para diferentes classes. Quando chamado
     * em Activity, o código no lambda não seria executado se isFinishing()for verdadeiro.
     * Use ctx.uiThread { }se este for um resultado indesejado.
     */
    private fun backgroundToFront4(button: Button, textView: TextView) {
        doAsyncResult {
            uiThread {
            }
            onComplete {
                Log.d("Controller", "Sdk Connected")
            }
        }
    }
}
