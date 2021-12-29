package com.example.httpdemo.util

import com.example.httpdemo.modle.App
import com.example.httpdemo.modle.AppItem
import com.google.gson.Gson
import org.json.JSONArray
import java.lang.Exception
import java.lang.StringBuilder

object JsonUtil {
    fun parseJsonWithJSONObject(data: String?) = try {
        //               []数组
        val jsonArray = JSONArray(data)
        val stringBuilder = StringBuilder()
        for (i in 0 until jsonArray.length()) {
            //                          {}对象
            val jsonObject = jsonArray.getJSONObject(i)
            stringBuilder.append("APP编号：${jsonObject.getString("id")}").append("\n")
                .append("APP名称：${jsonObject.getString("name")}").append("\n")
                .append("APP版本：${jsonObject.getString("version")}").append("\n")

        }
        stringBuilder.toString()
    } catch (e: Exception) {
        e.printStackTrace().toString()
    }

    fun parseJsonWithGson(data: String?): String {

        val gson = Gson()
        val appList = gson.fromJson(data, App::class.java)
        val stringBuilder = StringBuilder()
        for (app in appList) {
            stringBuilder
                .append("APP编号：${app.id}").append("\n").append("\n")
                .append("APP名称：${app.name}").append("\n").append("\n")
                .append("APP版本：${app.version}").append("\n").append("\n")
        }
        return stringBuilder.toString()

    }

}