package com.example.display_spec_and.extension

import android.content.Context
import android.content.res.Configuration

fun Context.getScreenDensity(): String = resources.displayMetrics.density.let {
    return@let if (it == 0.75f) {
        "LDPI"
    } else if (it >= 1.0f && it < 1.5f) {
        "MDPI"
    } else if (it == 1.5f) {
        "HDPI"
    } else if (it > 1.5f && it <= 2.0f) {
        "XHDPI"
    } else if (it > 2.0f && it <= 3.0f) {
        "XXHDPI"
    } else {
        "XXXHDPI"
    }
}

fun Context.getActualTextSizeWithFormula(textSize: Float): String {
    val scaledDensity = resources.displayMetrics.scaledDensity
    val actualTextSize = textSize * scaledDensity
    return "$actualTextSize (= $textSize * $scaledDensity)"
}

fun Context.getScreenSize(): String =
    when (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
        Configuration.SCREENLAYOUT_SIZE_SMALL -> "Small"
        Configuration.SCREENLAYOUT_SIZE_NORMAL -> "Normal"
        Configuration.SCREENLAYOUT_SIZE_LARGE -> "Large"
        Configuration.SCREENLAYOUT_SIZE_XLARGE -> "X-Large"
        else -> "ERROR"
    }
