package com.example.marmitech.extension

import android.content.Context
import com.example.domain.marmitech.appPeople.model.Marmitech
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.IOException

fun Context.readJson(jsonName: String): String {
    val jsonString: String
    try {
        jsonString = assets.open(jsonName).bufferedReader().use { it.readText() }
    } catch (error: IOException) {
        error.printStackTrace()
        return ""
    }
    return jsonString
}

fun Context.toDataList(): Marmitech {
    return jacksonObjectMapper().readValue(readJson("JsonToMarmitech.json"))
}