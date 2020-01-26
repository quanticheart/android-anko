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
 *  * Copyright(c) Developed by John Alves at 2020/1/25 at 11:15:1 for quantic heart studios
 *
 */

package com.quanticheart.anko.recyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.quanticheart.anko.R
import com.quanticheart.anko.sqlite.entity.PersonEntity
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

class RecyclerActivity : AppCompatActivity() {

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

    private var clubs: MutableList<PersonEntity> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        verticalLayout {
            lparams(matchParent, wrapContent)

            recyclerView {
                layoutManager = LinearLayoutManager(context)
                adapter = RecyclerViewAdapter(context, clubs) {
                    startActivity<SecondActivity>(
                        "clubBundle" to it
                    )
                }
            }
        }
    }

    fun initData() {
        val clubName = resources.getStringArray(R.array.club_name)
        val clubImage = resources.obtainTypedArray(R.array.club_image)
        val clubDesc = resources.getStringArray(R.array.club_desc)
        clubs.clear()
        for (i in clubName.indices) {
            clubs.add(
                PersonEntity(
                    clubName[i],
                    clubDesc[i],
                    clubImage.getResourceId(i, 0),
                    PersonEntity.Data("", "")
                )
            )
        }
        //Recycle the typed array
        clubImage.recycle()
    }
}

