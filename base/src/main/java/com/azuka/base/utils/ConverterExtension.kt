package com.azuka.base.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by ivanaazuka on 01/10/20.
 * Android Engineer
 */

val gson = Gson()

//convert a data class to a map
fun <DC> DC.serializeToMap(): Map<String, Any> {
    return convert()
}

//convert a map to a data class
inline fun <reified DC> Map<String, Any>.toDataClass(): DC {
    return convert()
}

//convert an object of type J(son) to type D(ata)C(lass)
inline fun <J, reified DC> J.convert(): DC {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<DC>() {}.type)
}

inline fun <FROM, reified TO> FROM.convertDataClass(): TO {
    return serializeToMap().toDataClass()
}