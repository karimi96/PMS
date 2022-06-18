package com.karimi.pms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.karimi.pms.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.login_email.view.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        email_ac.continueEmail.setOnClickListener { email_ac.visibility = View.GONE
//        pass_ac.visibility = View.VISIBLE}
    }



    private fun toast(text : String){
        Toast.makeText(applicationContext,text, Toast.LENGTH_SHORT).show()
    }


}