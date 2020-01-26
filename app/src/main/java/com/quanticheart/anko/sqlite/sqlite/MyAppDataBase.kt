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
 *  * Copyright(c) Developed by John Alves at 2020/1/25 at 10:23:35 for quantic heart studios
 *
 */

package com.quanticheart.anko.sqlite.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.quanticheart.anko.sqlite.consts.DBConst
import org.jetbrains.anko.db.*

class MyAppDataBase(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DBConst.database) {
    companion object {
        private var instance: MyAppDataBase? = null
        @Synchronized
        fun getInstance(ctx: Context): MyAppDataBase {
            if (instance == null) {
                instance =
                    MyAppDataBase(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            DBConst.personTable,
            true,
            DBConst.idPrimary to INTEGER + PRIMARY_KEY,
            "name" to TEXT,
            "surname" to TEXT,
            "age" to INTEGER
        )

        db.createTable(
            DBConst.dataTable,
            true,
            DBConst.idPrimary to INTEGER + PRIMARY_KEY,
            "personId" to INTEGER,
            "email" to TEXT,
            "cell" to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}