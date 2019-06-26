package com.example.display_spec_and

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.display_spec_and.extension.getActualTextSizeWithFormula
import com.example.display_spec_and.extension.getScreenDensity
import com.example.display_spec_and.extension.getScreenSize
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity :
    AppCompatActivity(),
    MainContract.View {

    private val presenter: MainContract.Presenter by lazy {
        MainPresenter(this)
    }

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        presenter.onViewCreate()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }

    override fun showScreenSize() {
        tv_screen_size.text = getScreenSize()
    }

    override fun showScreenDensity() {
        tv_screen_density.text = getScreenDensity()
    }

    override fun showScaledDensity() {
        tv_scaled_density.text = resources.displayMetrics.scaledDensity.toString()
    }

    private fun setupViews() {
        edit_text_size
            .textChanges()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                val textSize = it.toString().toFloat()
                tv_sample_title.textSize = textSize
                tv_actual_text_size.apply {
                    this.textSize = textSize
                    text = getActualTextSizeWithFormula(textSize)
                }

            }
            .addTo(disposables)
    }
}