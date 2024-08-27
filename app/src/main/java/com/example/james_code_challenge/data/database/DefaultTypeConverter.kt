package com.example.james_code_challenge.data.database

import androidx.room.TypeConverter
import com.example.james_code_challenge.data.model.Procedure
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DefaultTypeConverter {

    @TypeConverter
    fun fromStringToProcedure(value: String?): Procedure? {
        val type = object : TypeToken<Procedure>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toString(procedure: Procedure?): String? {
        return Gson().toJson(procedure)
    }

}