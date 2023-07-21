package com.cixteam.balance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    // Versión y nombre de la base de datos
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "balance.db";

    // Nombres de las tablas
    public static final String TABLE_STOCK = "t_stock";
    public static final String TABLE_PRODUCTS = "t_products";
    public static final String TABLE_SALES = "t_sales";

    // Constructor
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    // Método llamado cuando se crea la base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear tabla TABLE_STOCK con columnas específicas
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_STOCK + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "tipo TEXT," +
                "precio REAL NOT NULL," +
                "stock REAL NOT NULL)");

        // Crear tabla TABLE_PRODUCTS con columnas específicas
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRODUCTS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "tipo TEXT," +
                "code TEXT)");

        // Insertar registros iniciales en TABLE_PRODUCTS
        insertarProducto(sqLiteDatabase, "PLUMON PIZARRA FABER-CASTELL", "UTILES", "7754111602211");
        insertarProducto(sqLiteDatabase, "CUADERNO", "UTILES", "7754227186483");
        insertarProducto(sqLiteDatabase, "AGUA SAN CARLOS 625ML", "ALIMENTO", "7751580001118");
        insertarProducto(sqLiteDatabase, "FRUGOS DEL VALLE 500ML", "ALIMENTO", "7750182007092");
        insertarProducto(sqLiteDatabase, "LAPICERO ARTESCO AZUL", "UTILES", "7750082067769");
        insertarProducto(sqLiteDatabase, "PLUMON VERDE ARTESCO", "UTILES", "7754111602631");
        insertarProducto(sqLiteDatabase, "PLUMON AZUL ARTESCO", "UTILES", "7754111602211");
        insertarProducto(sqLiteDatabase, "PLUMONES PACK 4 UNIDADES", "UTILES", "7750082007741");
        insertarProducto(sqLiteDatabase, "CAMARA INSTAX MINI 11", "ELECTRONICOS", "074101202298");
        insertarProducto(sqLiteDatabase, "AUDIFONOS SONY", "ELECTRONICOS", "027242815100");
        // Crear tabla TABLE_SALES con columnas específicas
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SALES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "tipo TEXT," +
                "importe REAL NOT NULL," +
                "unidades REAL NOT NULL," +
                "code TEXT)");
    }

    // Método llamado cuando la base de datos necesita ser actualizada (no se utiliza aquí)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Lógica para actualizar la base de datos en futuras versiones
    }

    // Método auxiliar para insertar un producto en la tabla TABLE_PRODUCTS
    private void insertarProducto(SQLiteDatabase sqLiteDatabase, String nombre, String tipo, String code) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("tipo", tipo);
        values.put("code", code);
        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values);
    }
}
