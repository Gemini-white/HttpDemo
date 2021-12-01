package com.example.httpdemo.util

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUrlConnectionUtil:HttpService {


    override fun get(urlString: String):String {
        //将读到的数据拼接为字符串
        val stringBuilder = StringBuilder()
        var connection: HttpURLConnection? = null
            try {
                //根据网址创建URL对象
                val url = URL(urlString)
                //打开网址链接
                connection = url.openConnection() as HttpURLConnection
                //设置（多少毫秒后算为）连接超时
                connection.connectTimeout = 8000
                //设置读取超时
                connection.readTimeout = 8000
                //获取网络请求(服务器返回)的输入流
                val input = connection.inputStream
                //对输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        stringBuilder.append(it)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace().toString()
            } finally {
                //将连接关闭
                connection?.disconnect()
            }
            return stringBuilder.toString()

    }

    override fun post(urlString: String, Parameter: String):String {
        //将读到的数据拼接为字符串
        val stringBuilder = StringBuilder()
        var connection: HttpURLConnection? = null
            try {
                //根据网址创建URL对象
                val url = URL(urlString)
                //打开网址链接
                connection = url.openConnection() as HttpURLConnection
                //设置（多少毫秒后算为）连接超时
                connection.connectTimeout = 8000
                //设置读取超时
                connection.readTimeout = 8000


                //设置请求方式为POST（默认是GET）
                connection.requestMethod = "POST"
                //准备提交API所需的数据
                val output = DataOutputStream(connection.outputStream)
                //写入参数
                output.writeBytes(Parameter)

                //获取网络请求(服务器返回)的输入流
                val input = connection.inputStream
                //对输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        stringBuilder.append(it)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace().toString()
            } finally {
                //将连接关闭
                connection?.disconnect()
            }
        return stringBuilder.toString()
    }

}