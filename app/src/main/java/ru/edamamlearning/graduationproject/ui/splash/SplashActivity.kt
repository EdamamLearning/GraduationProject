package ru.edamamlearning.graduationproject.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.ActivitySplashBinding
import ru.edamamlearning.graduationproject.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding?.root

        binding?.pokeAnimationImageView?.let {
            Glide
                .with(this)
                .load(R.drawable.splash_eat)
                .into(it)
        }

        binding!!.pokeAnimationImageView.animate().rotationBy(300f)
            .setInterpolator(LinearInterpolator()).duration = 3000

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)

        setContentView(view)
    }
}