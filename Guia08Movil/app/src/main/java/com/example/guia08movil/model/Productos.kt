package com.example.guia08movil.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.guia08movil.db.HelperDB

class Productos(context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }
    companion object {
        // Nombre de la tabla productos
        const val TABLE_NAME_PRODUCTOS = "productos"
        // Campos de la tabla
        const val COL_ID = "idproductos"
        const val COL_IDCATEGORIA = "idcategoria"
        const val COL_DESCRIPCION = "descripcion"
        const val COL_PRECIO = "precio"
        const val COL_CANTIDAD = "cantidad"
        // Sentencia SQL para crear la tabla productos
        const val CREATE_TABLE_PRODUCTOS = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_PRODUCTOS (" +
                        "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "$COL_IDCATEGORIA INTEGER NOT NULL," +
                        "$COL_DESCRIPCION VARCHAR(150) NOT NULL," +
                        "$COL_PRECIO DECIMAL(10,2) NOT NULL," +
                        "$COL_CANTIDAD INTEGER," +
                        "FOREIGN KEY($COL_IDCATEGORIA) REFERENCES categoria(idcategoria)" + ");"
        )
    }
    // Genera ContentValues para insertar o actualizar productos
    private fun generarContentValues(
        idcategoria: Int?,
        descripcion: String?,
        precio: Double?,
        cantidad: Int?
    ): ContentValues {
        val valores = ContentValues()
        valores.put(COL_IDCATEGORIA, idcategoria)
        valores.put(COL_DESCRIPCION, descripcion)
        valores.put(COL_PRECIO, precio)
        valores.put(COL_CANTIDAD, cantidad)
        return valores
    }
    // Inserta un nuevo producto
    fun addNewProducto(idcategoria: Int?, descripcion: String?, precio:
    Double?, cantidad: Int?) {
        db?.insert(
            TABLE_NAME_PRODUCTOS,
            null,
            generarContentValues(idcategoria, descripcion, precio,
                cantidad)
        )
    }
    // Elimina un producto por ID
    fun deleteProducto(id: Int) {
        db?.delete(TABLE_NAME_PRODUCTOS, "$COL_ID=?",
            arrayOf(id.toString()))
    }
    // Actualiza un producto por ID
    fun updateProducto(
        id: Int,
        idcategoria: Int?,
        descripcion: String?,
        precio: Double?,
        cantidad: Int?
    ) {
        db?.update(
            TABLE_NAME_PRODUCTOS,
            generarContentValues(idcategoria, descripcion, precio,
                cantidad),
            "$COL_ID=?",
            arrayOf(id.toString())
        )
    }
    // Busca un producto por ID
    fun searchProducto(id: Int): Cursor? {
        val columns = arrayOf(COL_ID, COL_IDCATEGORIA, COL_DESCRIPCION,
            COL_PRECIO, COL_CANTIDAD)
        return db?.query(
            TABLE_NAME_PRODUCTOS,
            columns,
            "$COL_ID=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
    }
    // Obtiene todos los productos ordenados por descripción
    fun searchProductosAll(): Cursor? {
        val columns = arrayOf(COL_ID, COL_IDCATEGORIA, COL_DESCRIPCION,
            COL_PRECIO, COL_CANTIDAD)
        return db?.query(
            TABLE_NAME_PRODUCTOS,
            columns,
            null,
            null,
            null,
            null,
            "$COL_DESCRIPCION ASC"
        )
    }
}