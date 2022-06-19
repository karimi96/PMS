package com.karimi.pms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.karimi.pms.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.login_email.view.*
import kotlinx.android.synthetic.main.login_pass.*
import kotlinx.android.synthetic.main.login_pass.view.*
import kotlinx.android.synthetic.main.vertification.view.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        in_email.nextEmail.setOnClickListener { in_email.visibility = View.GONE
        in_pass.visibility = View.VISIBLE}

        in_email.tv_join.setOnClickListener {in_email.visibility = View.GONE
        in_vertification.visibility = View.VISIBLE}

        in_vertification.nextVertification.setOnClickListener {  in_vertification.visibility = View.GONE
        in_signUp.visibility = View.VISIBLE}

        in_pass.forgetPass.setOnClickListener { in_pass.visibility = View.GONE
        in_vertification.visibility = View.VISIBLE}

        in_vertification.nextVertification.setOnClickListener { in_vertification.visibility = View.GONE
        in_forgetPass.visibility = View.VISIBLE }

        countDownTimer()

        in_pass.nextPass.setOnClickListener {  }
    }



    private fun toast(text : String){
        Toast.makeText(applicationContext,text, Toast.LENGTH_SHORT).show()
    }


    private fun countDownTimer(){
        val timer = object: CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                in_vertification.timer.text = String.format("%02d:%02d", minutes, seconds);

            }
            override fun onFinish() {
//                tt.text = "done!"
            }
        }
        timer.start()
    }

}