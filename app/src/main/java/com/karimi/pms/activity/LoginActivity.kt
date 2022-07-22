package com.karimi.pms.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.karimi.pms.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.login_email.*
import kotlinx.android.synthetic.main.vertification.view.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lunchPhonePage()
        lunchPasswordPage()
        countDownTimer()


//        in_pass.nextPass.setOnClickListener { startActivity(Intent(this,HomeActivity ::class.java)) }
    }


    private fun lunchPhonePage() {
        val slideAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        slideAnimation.duration = 1000
        slideAnimation.duration = 1000
        img_phone.startAnimation(slideAnimation)
        tv_enterYourPhone.startAnimation(slideAnimation)
        et_phone.startAnimation(slideAnimation)
        tv_nextPhone.startAnimation(slideAnimation)
    }

    private fun lunchPasswordPage() {
        tv_nextPhone.setOnClickListener {
            var transition: Transition
            if (img_phone.isVisible) {
                transition = Slide(Gravity.END)
                transition.duration = 2000
                TransitionManager.beginDelayedTransition(relative, transition)
                tv_enterYourPhone.visibility = View.GONE
                img_phone.visibility = View.GONE
                et_phone.visibility = View.GONE
                tv_nextPhone.visibility = View.GONE
            }
//        else{
//            transition = Fade()
//            transition.duration = 2000
//            TransitionManager.beginDelayedTransition(relative,transition)
//            img_phone.visibility = View.GONE
//        }
//
//        if(tv_enterYourPhone.isGone && et_phone.isGone && tv_nextPhone.isGone){
//            transition = Slide(Gravity.RIGHT)
//            transition.duration = 2000
//            TransitionManager.beginDelayedTransition(relative,transition)
//            tv_enterYourPhone.visibility = View.VISIBLE
//            et_phone.visibility = View.VISIBLE
//            tv_nextPhone.visibility = View.VISIBLE
//        }else {
//            transition = Fade()
//            transition.duration = 2000
//            TransitionManager.beginDelayedTransition(relative,transition)
//            tv_enterYourPhone.visibility = View.GONE
//            et_phone.visibility = View.GONE
//            tv_nextPhone.visibility = View.GONE
//        }
        }
    }


    private fun countDownTimer() {
        val timer = object : CountDownTimer(120000, 1000) {
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


    private fun toast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}
