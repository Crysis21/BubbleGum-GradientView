package com.hold1.bubblegum

import android.os.Build
import android.view.View

/**
 * Created by Cristian Holdunu on 09/04/2018.
 */

class BubbleBuilder {
    var startColor = -1
    var endColor = -1
    var gradients: ArrayList<Gradient>? = null
    var animationDuration = -1
    var animationInterval = -1
    var angle = 0

    fun withStartColor(color: Int): BubbleBuilder {
        this.startColor = color
        return this
    }

    fun withAngle(angle: Int): BubbleBuilder {
        this.angle = angle
        return this
    }

    fun withEndColor(color: Int): BubbleBuilder {
        this.endColor = color
        return this
    }

    fun withGradient(gradient: Gradient): BubbleBuilder {
        if (this.gradients == null)
            this.gradients = ArrayList()
        this.gradients?.add(gradient)
        return this
    }

    fun withAnimation(duration: Int, interval: Int): BubbleBuilder {
        this.animationDuration = duration
        this.animationInterval = interval
        return this
    }

    fun withGradients(gradient: Array<Gradient>) {
        this.gradients = gradients
    }

    fun intoView(view: View) {
        val drawable: GradientDrawable?
        if (gradients != null) {
            drawable = GradientDrawable(gradients!!.toTypedArray())
        } else {
            drawable = GradientDrawable(Gradient(intArrayOf(startColor, endColor), angle))
        }
        if (animationDuration != -1) {
            drawable.loopDuration = animationDuration
            drawable.loopInterval = animationInterval
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.background = drawable
        } else {
            view.setBackgroundDrawable(drawable)
        }
    }
}