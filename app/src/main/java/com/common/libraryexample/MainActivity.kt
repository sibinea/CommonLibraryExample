package com.common.libraryexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.common.libraryexample.model.GetUserModel
import com.common.libraryexample.model.UserCreateRequest
import com.sibin.commonlib.*
import com.sibin.commonlib.network.isConnected
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkHelper.retrofitConfigure { baseUrl = "https://reqres.in/api/" }
        btnGetApi.setOnClickListener {
            getApi()
        }
        btnPostApi.setOnClickListener {
            postApi()
        }
        btnPatchApi.setOnClickListener {
            patchApi()
        }
        btnPutApi.setOnClickListener {
            putApi()
        }
        btnDeleteApi.setOnClickListener {
            deleteApi()
        }
        btnImage.setOnClickListener {
            startActivity(ImageActivity.newIntent(this))
        }


    }

    private fun postApi() {
        if (isConnected()) {
            Thread {
                val postData = UserCreateRequest("Sibin", "Engineer")
                NetworkHelper.postCall("users", postData, ::successCallback, ::failureCallback)
            }.start()
        }
    }

    private fun patchApi() {
        if (isConnected()) {
            Thread {
                val postData = UserCreateRequest("Sibin E A")
                NetworkHelper.patchCall("users/2", postData, ::successCallback, ::failureCallback)
            }.start()
        }
    }


    private fun putApi() {
        if (isConnected()) {
            Thread {
                val postData = UserCreateRequest("Sibin E A", "Computer Engineer")
                NetworkHelper.putCall("users/2", postData, ::successCallback, ::failureCallback)
            }.start()
        }
    }

    private fun deleteApi() {
        if (isConnected()) {
            Thread {
                NetworkHelper.deleteCall("users/7", ::successCallback, ::failureCallback)
            }.start()
        }
    }

    private fun getApiOb() {
        if (isConnected()) {
            Thread {

                NetworkHelper.getCall<GetUserModel>("users?page=2",::successCallbackGet, ::failureCallback)
            }.start()
        }
    }

    private fun getApi() {
        if (isConnected()) {
            Thread {
                NetworkHelper.getCall("users?page=2", ::successCallbackGet, ::failureCallback)
            }.start()
        }
    }

    private fun successCallback(response: UserCreateRequest) {
        Log.d("result", "" + response.toString())
        showToast(" ${response.toString()}")

    }


    private fun successCallback() {
        showToast("Deleted Successfully")
    }

    private fun successCallbackGet(response: GetUserModel) {
        Log.d("result", "" + response.data.size)
        showToast("List size : ${response.data.size.toString()}")
    }

    private fun failureCallback() {
        Log.d("result", "Error")
        showToast("Api failed")
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


}



