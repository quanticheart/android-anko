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
 *  * Copyright(c) Developed by John Alves at 2020/1/25 at 10:24:8 for quantic heart studios
 *
 */

package com.quanticheart.anko.sqlite.ext

import android.content.Context
import com.quanticheart.anko.sqlite.consts.DBConst
import com.quanticheart.anko.sqlite.entity.PersonEntity
import com.quanticheart.anko.sqlite.sqlite.MyAppDataBase
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync

// Access property for Context
val Context.database: MyAppDataBase
    get() = MyAppDataBase.getInstance(
        applicationContext
    )

fun Context.databaseInsertPerson(personEntity: PersonEntity): Boolean {
    this.database.use {
        insert(
            DBConst.personTable,
            DBConst.idPrimary to 1,
            "name" to personEntity.name,
            "surname" to personEntity.surname,
            "age" to personEntity.age
        )

        insert(
            DBConst.dataTable,
            DBConst.idPrimary to 1,
            "personId" to 1,
            "email" to personEntity.moreData.email,
            "cell" to personEntity.moreData.cell
        )
    }
    return true
}

fun Context.getPersonList(): ArrayList<PersonEntity> {
    val parser2 = classParser<PersonEntity>()
    val personList2: ArrayList<PersonEntity> = ArrayList()

    this@getPersonList.database.use {
        select(DBConst.dataTable).exec {
            val thisCursor = this
            val personList: List<PersonEntity> = parseList(parser2)
            for (i in 0 until thisCursor.count) {
                thisCursor.moveToNext()
                personList2.add(
                    PersonEntity(
                        thisCursor.getString(thisCursor.getColumnIndex("name")),
                        thisCursor.getString(thisCursor.getColumnIndex("surname")),
                        thisCursor.getInt(thisCursor.getColumnIndex("age")),
                        PersonEntity.Data(
                            "", ""
                        )
                    )
                )
            }

        }
    }
    return personList2
}



