package com.example.httpdemo.util

import com.example.httpdemo.ContentHandler
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.lang.Exception
import java.lang.StringBuilder
import javax.xml.parsers.SAXParserFactory

object XmlUtil {
    /**
     * xml数据的Pull解析方式
     */
    fun parseXMLWithPull(xmlData:String?) :String{
        try {
            //创建XML的Pull解析的工厂实例对象
            val xmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
            //将要解析的xml数据交给工厂对象
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            val stringBuilder = StringBuilder()
            while (eventType != XmlPullParser.END_DOCUMENT){
                val nodeName = xmlPullParser.name
                when(eventType){
                    XmlPullParser.START_TAG -> {
                        when(nodeName){
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if(nodeName == "app"){
                            stringBuilder.append("APP编号：${id}")
                                .append('\n')
                                .append("APP名称：${name}")
                                .append('\n')
                                .append("APP版本：${version}")
                                .append('\n').append('\n')
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
            return stringBuilder.toString()
        }catch (e:Exception){
            return e.printStackTrace().toString()
        }
    }

    /**
     * xml数据的SAX解析方式
     */
    fun parseXMLWithSAX(xmlData: String?) = try {
        //创建XML的SAX解析的宫实例对象
        val xmlSAXParser = SAXParserFactory.newInstance().newSAXParser()
        //获得SAC解析读入流
        val xmlReader = xmlSAXParser.xmlReader
        //获得我们自己编写的SAX解析算法的实例对象
        val handler = ContentHandler()
        //将解析算法设置到SAC解析读入流对象当中
        xmlReader.contentHandler = handler
        //开始解析
        xmlReader.parse(InputSource(StringReader(xmlData)))
        handler.getData()
    } catch (e: Exception) {
        e.printStackTrace().toString()
    }
}