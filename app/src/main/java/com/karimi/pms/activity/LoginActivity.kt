package com.karimi.pms.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.karimi.pms.R
import com.karimi.pms.helper.Session
import com.karimi.pms.helper.Tools
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bottom_sheet_setting.*
import kotlinx.android.synthetic.main.forget_pass.*
import kotlinx.android.synthetic.main.login_email.*
import kotlinx.android.synthetic.main.login_pass.*
import kotlinx.android.synthetic.main.verification.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Tools.checkTheme()
        lunchPhonePage()
        lunchPasswordPage()
        lunchHomeActivity()
        lunchValidation()
        lunchForgetPass()
        countDownTimer()

    }


    private fun lunchPhonePage() {
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_to_up)
        slideAnimation.duration = 900
        img_phone.startAnimation(slideAnimation)
        tv_enterYourPhone.startAnimation(slideAnimation)
        et_phone.startAnimation(slideAnimation)
        tv_nextPhone.startAnimation(slideAnimation)
    }


    private fun lunchPasswordPage() {
        tv_nextPhone.setOnClickListener {
            if (img_phone.isVisible) {
                var transition: Transition
                transition = Slide(Gravity.END)
                transition.duration = 1000
                TransitionManager.beginDelayedTransition(relative, transition)
                arrayOf(
                    tv_enterYourPhone,
                    et_phone,
                    img_phone,
                    tv_nextPhone
                ).forEach { it.visibility = View.GONE }
            }
            if (img_phone.isGone) {
                val slideAnimation = AnimationUtils.loadAnimation(this,android.R.anim.fade_in)
                slideAnimation.duration = 1000
                in_pass.visibility = View.VISIBLE
                in_pass.startAnimation(slideAnimation)
            }
        }
    }


    private fun lunchValidation() {
        tv_forgetPass.setOnClickListener {
            var transition: Transition
            if (img_loginPass.isVisible) {
                transition = Slide(Gravity.BOTTOM)
                transition.duration = 1000
                TransitionManager.beginDelayedTransition(relative, transition)
                in_pass.visibility = View.GONE
            }
            var transition2: Transition
            if (in_pass.isGone) {
                transition2 = Slide(Gravity.BOTTOM)
                transition2.duration = 1000
                TransitionManager.beginDelayedTransition(relative, transition2)
                in_verification.visibility = View.VISIBLE
            }
        }
    }


    private fun lunchForgetPass() {
        tv_nextVerification.setOnClickListener {
            if (img_verification.isVisible) {
                var transition: Transition
                transition = Slide(Gravity.TOP)
                transition.duration = 1000
                TransitionManager.beginDelayedTransition(relative, transition)
                img_verification.visibility = View.GONE
            }
            if (tv_interYourCode.isVisible) {
                var transition1: Transition
                transition1 = Slide(Gravity.TOP)
                transition1.duration = 1000
                TransitionManager.beginDelayedTransition(relative, transition1)
                tv_interYourCode.visibility = View.GONE
                linear_verification.visibility = View.GONE
                timer.visibility = View.GONE
                tv_nextVerification.visibility = View.GONE
                in_verification.visibility = View.GONE
            }

            if (img_verification.isGone) {
                var transition: Transition
                transition = Slide(Gravity.TOP)
                transition.duration = 1000
                TransitionManager.beginDelayedTransition(relative, transition)
                arrayOf(
                    in_forgetPass,
                    img_forget_pass,
                    et_newPass,
                    et_repetition_newPass,
                    tv_nextForgetPass
                ).forEach { it.visibility = View.VISIBLE }
            }
        }
    }


    private fun lunchHomeActivity() {
        tv_nextPass.setOnClickListener {
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        }
    }


    private fun countDownTimer() {
        val timer = object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timer.text = String.format("%02d:%02d", minutes, seconds);
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
