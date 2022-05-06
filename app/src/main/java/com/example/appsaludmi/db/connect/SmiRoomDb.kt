package com.example.appsaludmi.db.connect

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appsaludmi.db.dao.PerfilDAO
import com.example.appsaludmi.db.model.Perfil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Perfil::class), version=1, exportSchema = false)
public abstract  class SmiRoomDb: RoomDatabase() {

    abstract fun perfilDao(): PerfilDAO

    companion object {

        @Volatile
        private var inst: SmiRoomDb? = null

        fun getDb(context: Context, viewModelScope: CoroutineScope ): SmiRoomDb {
            val instTemp = inst
            if (instTemp != null) {
                return instTemp
            }
            synchronized(this ) {
                val instancia = Room.databaseBuilder( context.applicationContext, SmiRoomDb::class.java, "SmiDB")
                    .addCallback( SmiDBCallback( viewModelScope ))
                    .build()
                inst = instancia
                return instancia
            }
        }

        private class SmiDBCallback( private val scope: CoroutineScope): RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                inst?.let { database ->
                    scope.launch {
                        loadData( database.perfilDao() )
                    }
                }
            }

            suspend fun loadData( perfilDao: PerfilDAO ) {
                var perfil1 = Perfil( 1, "Paula","Dominguez","JB Justo 134","05/05/1995")
                perfilDao.savePerfil( perfil1 )
                var perfil2 = Perfil( 2, "Marta","Rodriguez","A Maiz 567","23/02/1988")
                perfilDao.savePerfil( perfil2 )
            }
        }
    }
}