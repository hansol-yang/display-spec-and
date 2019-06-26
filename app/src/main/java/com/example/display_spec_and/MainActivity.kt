package com.example.display_spec_and

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity(),
    MainContract.View {

    private val presenter: MainContract.Presenter by lazy {
        MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onViewCreate()
    }

    override fun showScreenSize() {
        tv_screen_size.text = getScreenSize()
    }

    override fun showScreenDensity() {
        tv_screen_density.text = getScreenDensity()
    }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }

    private fun getScreenSize(): String =
        when (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
            Configuration.SCREENLAYOUT_SIZE_SMALL -> "Small"
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> "Normal"
            Configuration.SCREENLAYOUT_SIZE_LARGE -> "Large"
            Configuration.SCREENLAYOUT_SIZE_XLARGE -> "X-Large"
            else -> "ERROR"
        }

    private fun getScreenDensity(): String {
        val density = resources.displayMetrics.density

        return if (density == 0.75f) {
            "LDPI"
        } else if (density >= 1.0f && density < 1.5f) {
            "MDPI"
        } else if (density == 1.5f) {
            "HDPI"
        } else if (density > 1.5f && density <= 2.0f) {
            "XHDPI"
        } else if (density > 2.0f && density <= 3.0f) {
            "XXHDPI"
        } else {
            "XXXHDPI"
        }
    }
}