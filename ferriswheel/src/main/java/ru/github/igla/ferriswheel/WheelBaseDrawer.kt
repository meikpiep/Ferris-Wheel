package ru.github.igla.ferriswheel

import android.content.Context
import android.graphics.*
import com.github.meikpiep.ferriswheel.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * Created by igor-lashkov on 11/01/2018.
 */

private const val PILL_ANGLE_FROM = 110.0
private const val PILL_ANGLE_TO = 70.0

internal class WheelBaseDrawer(private val context: Context, private val config: WheelViewConfig) : IWheelDrawer {

    private val outerPadding = context.dpF(6f)

    private val firstCircleFromStarRatio = 28.0f / 180.5f
    private val secondCircleFromStarRatio = 34.0f / 180.5f
    private val innerCircleRadiusRatio = 56.0f / 180.5f
    private val innerCircleStrokeRatio = 6.0f / 180.5f
    private val circleOuterStrokeRatio = 6.0f / 180.5f
    private val circleInnerStrokePaintStrokeRatio = 4.0f / 180.5f
    private val pillLineStrokeRatio = 8.0f / 180.5f
    private val circleInnerPaintStrokeRadiusRatio = 16.0f / 180.5f
    private val circleInnerPaintFillRadiusRatio = 14.0f / 180.5f
    private val starPathRadiusRatio = 32.0f / 180.5f
    private val groundPlateLengthRatio = 56.0f / 180.5f
    private val groundPlateHeightRatio = 16.0f / 180.5f
    private val groundPlateRoundedCornerRatio = 6.0f / 180.5f
    private val patternPaintStrokeRatio = 2.0f / 180.5f
    private val trianglePatternLengthRatio = 16.0f / 180.5f

    var radius = 0.0
    var radiusF = 0.0f

    private val useCabinAutoSize = config.cabinSize == -1
    private val defaultCabinSize: Int = context.resources.getDimensionPixelSize(R.dimen.fwv_cabin_size)
    var cabinSize = defaultCabinSize
    var ratioCabinSize: Double = 1.0

    val centerPoint = PointF()
    private var dirtyDraw = true

    var rotateAngle = 0f

    private val baseGroundPaint = smoothPaint(config.baseColor).apply {
        style = Paint.Style.FILL
    }

    private val patternPaint = smoothPaint(config.wheelColor).apply {
        style = Paint.Style.STROKE
    }

    private val innerCirclePaint = smoothPaint(config.wheelColor).apply {
        style = Paint.Style.STROKE
    }

    private val pillLinePaint = smoothPaint(config.baseColor).apply {
        style = Paint.Style.STROKE
    }

    private val circleOuterPaint = smoothPaint(config.wheelColor).apply {
        style = Paint.Style.STROKE
    }

    private val circleInnerPaintStroke = smoothPaint(config.coreStyle.colorCircleStroke).apply {
        style = Paint.Style.STROKE
    }
    private val circleInnerPaintFill = smoothPaint(config.coreStyle.colorCircleFill).apply {
        style = Paint.Style.FILL
    }

    private val pillLeftStart1 = PointF()
    private val pillRightStart2 = PointF()

    private val pillLeftEnd1 = PointF()
    private val pillRightEnd2 = PointF()

    private val pillGroundBlock1 = PointF()
    private val pillGroundBlock2 = PointF()

    private val roundRect = RectF()

    private val patternStep = 5
    private val patternPoints = 360 / (patternStep * 2)

    private val patternPointsOut = Array(patternPoints + 1) { PointF() }
    private val patternPointsIn = Array(patternPoints + 1) { PointF() }

    private val linePoints = FloatArray((patternPoints + 1) * 4 * 2 + patternPoints * 4)

    private val paintStar by lazyNonSafe {
        val color = config.coreStyle.starIcon?.colorFill
                ?: context.getColorRes(R.color.fwv_star_fill_color)
        smoothPaint(color).apply {
            style = Paint.Style.FILL
        }
    }
    private val pathStar by lazyNonSafe { Path() }

    private var groundPadding: Double = 0.0

    private fun getPaddingOutside(): Double = outerPadding.toDouble()

    override fun configure(rect: Rect) {
        val parentWidth = rect.width()
        val parentHeight = rect.height()

        val minSizeWithoutCabin = min(parentWidth, parentHeight) / 1.2

        if (useCabinAutoSize) {
            this.ratioCabinSize = minSizeWithoutCabin / 300 /
                    context.resources.displayMetrics.density
            this.cabinSize = (defaultCabinSize * ratioCabinSize).toInt()
        }

        groundPadding = cabinSize + CABIN_TILT_MAX + minSizeWithoutCabin * groundPlateHeightRatio

        val centerX = parentWidth / 2.0f
        val centerY = (parentHeight - groundPadding.toFloat()) / 2.0f
        this.centerPoint.set(centerX, centerY)
        val minSize = minOf(centerX - cabinSize / 2f, centerY)
        this.radius = minSize - getPaddingOutside()
        this.radiusF = radius.toFloat()

        innerCirclePaint.strokeWidth = radiusF * innerCircleStrokeRatio
        circleOuterPaint.strokeWidth = radiusF * circleOuterStrokeRatio
        circleInnerPaintStroke.strokeWidth = radiusF * circleInnerStrokePaintStrokeRatio
        pillLinePaint.strokeWidth = radiusF * pillLineStrokeRatio
        patternPaint.strokeWidth = radiusF * patternPaintStrokeRatio


        dirtyDraw = true
    }

    fun setPointPosAsWheel(outPoint: PointF, angle: Double) {
        setPointPos(outPoint, this.centerPoint, angle, this.radius)
    }

    override fun onPostDraw(canvas: Canvas) {
        canvas.apply {
            drawBase(this)
            drawCircle(centerPoint, radiusF * circleInnerPaintStrokeRadiusRatio, circleInnerPaintStroke)
            drawCircle(centerPoint, radiusF * circleInnerPaintFillRadiusRatio, circleInnerPaintFill)
            if (config.coreStyle.starIcon != null) {
                drawPath(pathStar, paintStar)
            }
        }
    }

    override fun onPreDraw(canvas: Canvas) {

        val radiusF = radius.toFloat()

        if (dirtyDraw) {
            calcNewPosition(centerPoint, radiusF)
            dirtyDraw = false
        }

        canvas.apply {
            save()
            rotate(rotateAngle, centerPoint.x, centerPoint.y)

            drawCircle(centerPoint, radiusF * innerCircleRadiusRatio, innerCirclePaint)

            drawCircle(centerPoint, radiusF * firstCircleFromStarRatio, patternPaint)
            drawCircle(centerPoint, radiusF * secondCircleFromStarRatio, patternPaint)

            drawCircle(centerPoint, radiusF, circleOuterPaint)

            drawLines(linePoints, patternPaint)

            drawCircle(centerPoint, getPatternRadiusInner(radiusF).toFloat(), patternPaint)
            restore()
        }
    }

    private fun setLineAtIndex(arr: FloatArray, index: Int, line1: PointF, line2: PointF) {
        arr[index] = line1.x
        arr[index + 1] = line1.y
        arr[index + 2] = line2.x
        arr[index + 3] = line2.y
    }

    private fun fillArrayWithData() {
        var n = 0
        var i = 0
        while (i <= patternPoints) {
            // first half of outer triangles
            setLineAtIndex(linePoints, n, patternPointsOut[i], patternPointsIn[i])
            n += 4
            if (i > 0) {
                // second half of outer triangles
                setLineAtIndex(linePoints, n, patternPointsIn[i], patternPointsOut[i - 1])
                n += 4
            }
            // draw crossings
            setLineAtIndex(linePoints, n, centerPoint, patternPointsIn[i])
            n += 4
            i++
        }
    }

    private fun drawBase(canvas: Canvas) {
        canvas.apply {
            drawLine(pillLeftStart1, pillLeftEnd1, pillLinePaint)
            drawLine(pillRightStart2, pillRightEnd2, pillLinePaint)
            drawRoundRect(
                    roundRect,
                radiusF * groundPlateRoundedCornerRatio,
                radiusF * groundPlateRoundedCornerRatio,
                    baseGroundPaint)
        }
    }

    private fun calcNewPosition(centerPoint: PointF, radius: Float) {

        setPointPos(pillLeftStart1, centerPoint, PILL_ANGLE_TO, (radiusF * groundPlateHeightRatio).toDouble())
        setPointPos(pillRightStart2, centerPoint, PILL_ANGLE_FROM, (radiusF * groundPlateHeightRatio).toDouble())

        val groundPoint = radius + groundPadding
        setPointPos(pillLeftEnd1, centerPoint, PILL_ANGLE_TO, groundPoint)
        setPointPos(pillRightEnd2, centerPoint, PILL_ANGLE_FROM, groundPoint)

        setPointPos(pillGroundBlock1, centerPoint, PILL_ANGLE_TO, groundPoint - radiusF * groundPlateHeightRatio)
        setPointPos(pillGroundBlock2, centerPoint, PILL_ANGLE_FROM, groundPoint - radiusF * groundPlateHeightRatio)

        roundRect.set(
                pillRightEnd2.x - radiusF * groundPlateLengthRatio/2,
                pillRightEnd2.y - outerPadding,
                pillLeftEnd1.x + radiusF * groundPlateLengthRatio/2,
                pillLeftEnd1.y - outerPadding + radiusF * groundPlateHeightRatio
        )

        var angle1 = patternStep.toDouble()
        var angle2 = 0.0
        val outPatternRadius = getPatternRadiusOuter(radius)
        val innerPatternRadius = getPatternRadiusInner(radius)
        val stepBy = patternStep * 2.0
        for (i in 0..patternPoints) {
            setPointPos(patternPointsOut[i], centerPoint, angle1, outPatternRadius)
            setPointPos(patternPointsIn[i], centerPoint, angle2, innerPatternRadius)

            angle1 += stepBy
            angle2 += stepBy
        }
        measureStarPath(centerPoint, radiusF * starPathRadiusRatio)

        fillArrayWithData()
    }

    private fun measureStarPath(centerPoint: PointF, size: Float) {
        pathStar.apply {
            val half = size / 2f
            val fromX = centerPoint.x - half
            val fromY = centerPoint.y - half
            rewind()
            // top left
            moveTo(fromX + half * 0.5f, fromY + half * 0.84f)
            // top right
            lineTo(fromX + half * 1.5f, fromY + half * 0.84f)
            // bottom left
            lineTo(fromX + half * 0.68f, fromY + half * 1.45f)
            // top tip
            lineTo(fromX + half * 1.0f, fromY + half * 0.5f)
            // bottom right
            lineTo(fromX + half * 1.32f, fromY + half * 1.45f)
            // top left
            lineTo(fromX + half * 0.5f, fromY + half * 0.84f)
            close()
        }
    }

    private fun getPatternRadiusOuter(radius: Float): Double = radius - circleOuterPaint.strokeWidth / 2.0
    private fun getPatternRadiusInner(radius: Float): Double = getPatternRadiusOuter(radius) - radiusF * trianglePatternLengthRatio


    private fun Canvas.drawLine(p1: PointF, p2: PointF, paint: Paint) {
        drawLine(p1.x, p1.y, p2.x, p2.y, paint)
    }

    private fun Canvas.drawCircle(point: PointF, radius: Float, paint: Paint) {
        drawCircle(point.x, point.y, radius, paint)
    }

    private fun setPointPos(outPoint: PointF, centerPoint: PointF, angle: Double, radius: Double) {
        outPoint.x = getXPos(centerPoint.x, radius, angle).toFloat()
        outPoint.y = getYPos(centerPoint.y, radius, angle).toFloat()
    }

    /***
     * https://en.wikipedia.org/wiki/Sine#Relation_to_the_unit_circle
     */
    private fun getXPos(centerX: Float, r: Double, angle: Double): Double = centerX + r * cos(getRadians(angle))

    private fun getYPos(centerY: Float, r: Double, angle: Double): Double = centerY + r * sin(getRadians(angle))

    private fun getRadians(angle: Double): Double = Math.toRadians(angle)
}