package com.cixteam.balance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "balance.db";
    public static final String TABLE_STOCK = "t_stock";
    public static final String TABLE_PRODUCTS = "t_products";
    public static final String TABLE_SALES = "t_sales";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear tabla TABLE_STOCK
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_STOCK + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "tipo TEXT," +
                "precio REAL NOT NULL," +
                "stock REAL NOT NULL)");

        // Crear tabla TABLE_PRODUCTS
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRODUCTS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "tipo TEXT," +
                "code TEXT)");

        // Insertar registros iniciales en la tabla TABLE_PRODUCTS
        insertarProducto(sqLiteDatabase, "PLUMON PIZARRA FABER-CASTELL", "UTILES", "7754111602211");
        insertarProducto(sqLiteDatabase, "CUADERNO", "UTILES", "7754227186483");
        insertarProducto(sqLiteDatabase, "AGUA SAN CARLOS 625ML", "ALIMENTO", "7751580001118");
        insertarProducto(sqLiteDatabase, "FRUGOS DEL VALLE 500ML", "ALIMENTO", "7750182007092");
        insertarProducto(sqLiteDatabase, "LAPICERO ARTESCO AZUL", "UTILES", "7750082067769");
        insertarProducto(sqLiteDatabase, "PLUMON VERDE ARTESCO", "UTILES", "7750082002043");

        // Crear tabla TABLE_SALES
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SALES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "tipo TEXT," +
                "importe REAL NOT NULL," +
                "unidades REAL NOT NULL," +
                "code TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Lógica para actualizar la base de datos en futuras versiones
    }

    private void insertarProducto(SQLiteDatabase sqLiteDatabase, String nombre, String tipo, String code) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("tipo", tipo);
        values.put("code", code);
        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values);
    }
}
