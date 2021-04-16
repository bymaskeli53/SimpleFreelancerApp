package com.muhammetgundogar.myfreelancerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            if (editText.text.toString() == "meg" && passwordText.text.toString() == "53"){
                val intent = Intent(applicationContext,UserActivity::class.java)
                startActivity(intent)
            }

        }


        callbackManager = CallbackManager.Factory.create()
        login_button.setPermissions(Arrays.asList("user_gender","user_friends"))
        login_button.registerCallback(callbackManager,object:FacebookCallback<LoginResult?>{
            override fun onSuccess(result: LoginResult?) {
                Log.d("Demo","Login Successful!")
            }

            override fun onCancel() {
                Log.d("Demo","Login Cancelled!")
            }

            override fun onError(error: FacebookException?) {
                Log.d("Demo","Login Error!")
            }

        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
       //we can here take users gender or users name,etc.with GraphRequest Class and accestoken



    }



        var accessTokenTracker =object:AccessTokenTracker(){
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
                if (currentAccessToken == null) {
                    LoginManager.getInstance().logOut()


            }

        }
    }

    override fun onStop() {
        super.onStop()
        LoginManager.getInstance().logOut()
    }

    override fun onDestroy() {
        super.onDestroy()
        accessTokenTracker.stopTracking()
        LoginManager.getInstance().logOut()
    }


}