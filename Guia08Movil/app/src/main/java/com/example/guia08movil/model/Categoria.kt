package com.example.guia08movil.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.guia08movil.db.HelperDB

class Categoria(context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }
    companion object {
        // Nombre de la tabla
        const val TABLE_NAME_CATEGORIA = "categoria"
        // Campos de la tabla
        const val COL_ID = "idcategoria"
        const val COL_NOMBRE = "nombre"
        // Sentencia SQL para crear la tabla
        const val CREATE_TABLE_CATEGORIA = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_CATEGORIA (" +
                        "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "$COL_NOMBRE VARCHAR(50) NOT NULL);"
                )
    }
    // Genera ContentValues para insertar o actualizar registros
    private fun generarContentValues(nombre: String?): ContentValues {
        val valores = ContentValues()
        valores.put(COL_NOMBRE, nombre)
        return valores
    }
    // Inserta las categorías predeterminadas si no existen registros
    fun insertValuesDefault() {
        val categories = arrayOf(
            "Abarrotes",
            "Carnes",
            "Embutidos",
            "Mariscos",
            "Pescado",
            "Bebidas",
            "Verduras",
            "Frutas",
            "Bebidas Carbonatadas",
            "Bebidas no carbonatadas"
        )
        val columns = arrayOf(COL_ID, COL_NOMBRE)
        val cursor: Cursor? = db?.query(TABLE_NAME_CATEGORIA, columns,
            null, null, null, null, null)
        if (cursor == null || cursor.count <= 0) {
            categories.forEach { item ->
                db?.insert(TABLE_NAME_CATEGORIA, null,
                    generarContentValues(item))
            }
        }
        cursor?.close()
    }
    // Recupera todas las categorías ordenadas por nombre
    fun showAllCategoria(): Cursor? {
        val columns = arrayOf(COL_ID, COL_NOMBRE)
        return db?.query(TABLE_NAME_CATEGORIA, columns, null, null, null,
            null, "$COL_NOMBRE ASC")
    }
    // Busca el ID de una categoría por su nombre
    fun searchID(nombre: String): Int? {
        val columns = arrayOf(COL_ID, COL_NOMBRE)
        val cursor: Cursor? = db?.query(
            TABLE_NAME_CATEGORIA,
            columns,
            "$COL_NOMBRE=?",
            arrayOf(nombre),
            null, null, null
        )
        cursor?.moveToFirst()
        val id = if (cursor != null && cursor.count > 0) cursor.getInt(0)
        else null
        cursor?.close()
        return id
    }
    // Busca el nombre de una categoría por su ID
    fun searchNombre(id: Int): String? {
        val columns = arrayOf(COL_ID, COL_NOMBRE)
        val cursor: Cursor? = db?.query(
            TABLE_NAME_CATEGORIA,
            columns,
            "$COL_ID=?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor?.moveToFirst()
        val nombre = if (cursor != null && cursor.count > 0)
            cursor.getString(1) else null
        cursor?.close()
        return nombre
    }
}