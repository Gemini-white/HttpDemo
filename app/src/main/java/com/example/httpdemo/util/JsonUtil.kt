package com.example.httpdemo.util

import org.json.JSONArray
import java.lang.Exception
import java.lang.StringBuilder

object JsonUtil {
    fun parseJsonWithJSONObject(data: String) = try {
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

}