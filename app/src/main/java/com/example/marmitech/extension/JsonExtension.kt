package com.example.marmitech.extension

import android.content.Context
import android.widget.Toast
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.appPeople.model.Marmitech
import com.fasterxml.jackson.core.JsonGenerationException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
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

fun Context.toJson(it: List<Apontamento>): String {
    return try {
        ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(it)
    } catch (e: JsonGenerationException) {
        e.printStackTrace()
        ""
    } catch (e: JsonMappingException) {
        e.printStackTrace()
        ""
    }
}

fun Context.writeFile(json: String) {
    try {
        val file = File(
            this.filesDir,
            "MarmitechToJson.json" //data/user/0/com.example.marmitech/files/MarmitechToJson.json
        )
        val fileWriter = FileWriter(file)
        val bufferedWriter = BufferedWriter(fileWriter)
        bufferedWriter.write(json)
        bufferedWriter.close()
    } catch (e: Exception) {
        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
    }
}