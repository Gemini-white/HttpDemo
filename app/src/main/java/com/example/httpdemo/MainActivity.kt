package com.example.httpdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.httpdemo.databinding.ActivityMainBinding
import com.example.httpdemo.util.HttpUtil
import com.example.httpdemo.util.XmlUtil
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.net.URLEncoder

import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val ip = "10.95.11.13"

    //private val dataTypeArray = resources.getStringArray(R.array.arr_data_type)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        mBinding.btnGet.setOnClickListener { thread {
                val data = HttpUtil.getHttpService().get("http://$ip:8080/login?username=admin&password=123456")
                //val data = HttpUtil.getHttpService().get("http://$ip:80/swish/index.php?page=1")
                runOnUiThread {
                    mBinding.textView.text = data
                }
            } }

        mBinding.btnPost.setOnClickListener { thread {
                val data = HttpUtil.getHttpService().post(
                    "http://$ip:8080/post?username=admin&password=123456",
                    "input=${URLEncoder.encode("测试测试post","UTF-8")}"
                )
                runOnUiThread {
                    mBinding.textView.text = data
                }
            } }

        mBinding.btnGetOkhttp.setOnClickListener { thread {
                try {
                    //创建OkHttp客户端对象
                    val client = OkHttpClient()
                    //创建Request对象,用来发送Http请求
                    val request = Request.Builder()
                        .url("http://$ip:8080/getKotlin?name=空白")
                        .build()
                    //发出网络请求,并接受回传数据
                    val response = client.newCall(request).execute()
                    //数据解析出来
                    val data = response.body?.string()
                    runOnUiThread {
                        mBinding.textView.text = data
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }

            } }

        mBinding.btnPostOkhttp.setOnClickListener { thread {
                try {
                    //创建OkHttp客户端对象
                    val client = OkHttpClient()
                    //构建参数对象
                    val requestBody = FormBody.Builder()
                        .add("id","001")
                        .add("name","白")
                        .build()
                    //创建Request对象,用来发送Http请求
                    val request = Request.Builder()
                        .url("http://$ip:8080/post/student")
                        .post(requestBody)
                        .build()
                    //发出网络请求,并接受回传数据
                    val response = client.newCall(request).execute()
                    //数据解析出来
                    val data = response.body?.string()
                    runOnUiThread {
                        mBinding.textView.text = data
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }

            } }

        mBinding.btnXmlPull.setOnClickListener { thread {
                try {
                    //创建OkHttp客户端对象
                    val client = OkHttpClient()
                    //创建Request对象,用来发送Http请求
                    val request = Request.Builder()
//                                .url("http://$ip:8080/get")
                        //发送xml请求
                        .url("http://$ip:8080/xml/get_data.xml")
                        .build()
                    //发出网络请求,并接受回传数据
                    val response = client.newCall(request).execute()
                    //数据解析出来
                    val data = response.body?.string()
                    runOnUiThread {
                        //mBinding.textView.text = data
                        mBinding.textView.text = XmlUtil.parseXMLWithPull(data)
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }

            } }

        mBinding.btnXmlSax.setOnClickListener { thread {
                try {
                    //创建OkHttp客户端对象
                    val client = OkHttpClient()
                    //创建Request对象,用来发送Http请求
                    val request = Request.Builder()
//                                .url("http://$ip:8080/get")
                        //发送xml请求
                        .url("http://$ip:8080/xml/get_data.xml")
                        .build()
                    //发出网络请求,并接受回传数据
                    val response = client.newCall(request).execute()
                    //数据解析出来
                    val data = response.body?.string()
                    runOnUiThread {
                        //mBinding.textView.text = data
                        mBinding.textView.text = XmlUtil.parseXMLWithSAX(data)
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }

            } }

        mBinding.btnJsonJsonOject.setOnClickListener {  }

        mBinding.btnJsonGson.setOnClickListener {  }
    }

}