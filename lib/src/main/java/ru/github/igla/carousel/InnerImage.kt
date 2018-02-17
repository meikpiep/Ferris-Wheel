package ru.github.igla.carousel

import android.graphics.PointF

/**
 * Created by igor-lashkov on 11/01/2018.
 */
internal class InnerImage constructor(
        val drawable: CabinDrawable,
        private val angle: Double = 0.0,
        var carouselPos: PointF = PointF(),
        var lastSize: Int
) {
    fun getAngleOffset(rotateAngle : Float): Double = (angle + rotateAngle) % 360.0
}