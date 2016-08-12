package jp.cordea.butler.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import jp.cordea.butler.R

/**
 * Created by Yoshihiro Tanaka on 2016/07/29.
 */
class CircularChartView : View {

    private val paint = Paint()
    private val secondPaint = Paint()
    private val textPaint = Paint()
    private val secondTextPaint = Paint()

    private val rectF = RectF()
    private val strokeWidth = resources.getDimension(R.dimen.circular_chart_stroke_width)

    private var percentage = 0f

    fun setPercentage(percentage: Float) {
        this.percentage = percentage
        invalidate()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        paint.isAntiAlias = true
        paint.color = ContextCompat.getColor(context, R.color.colorAccent)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth

        secondPaint.isAntiAlias = true
        secondPaint.color = ContextCompat.getColor(context, R.color.colorPrimary)

        textPaint.isAntiAlias = true
        textPaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        textPaint.textSize = resources.getDimension(R.dimen.circular_chart_text_size)

        val type = Typeface.create("sans-serif-light", Typeface.NORMAL)
        textPaint.typeface = type
        textPaint.textAlign = Paint.Align.CENTER

        secondTextPaint.isAntiAlias = true
        secondTextPaint.color = ContextCompat.getColor(context, android.R.color.white)
        secondTextPaint.textSize = resources.getDimension(R.dimen.circular_chart_text_size)
        secondTextPaint.typeface = type
        secondTextPaint.textAlign = Paint.Align.CENTER
        secondTextPaint.style = Paint.Style.FILL_AND_STROKE
        secondTextPaint.strokeWidth = resources.getDimension(R.dimen.circular_chart_text_stroke_width)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val size = width / 2.0f
        val halfStroke = strokeWidth / 2.0f
        canvas.drawCircle(size, size, size - halfStroke, paint)

        val h = width.toFloat() - strokeWidth
        rectF.set(0f + strokeWidth, 0f + strokeWidth, h, h)
        val percentage = Math.floor((percentage * 100.0f).toDouble()).toInt()
        val d = getDegree(percentage)
        canvas.drawArc(rectF, 90.0f - (d / 2.0f), d, false, secondPaint)

        canvas.drawText(resources.getString(R.string.determinate_progress_format_text).format(percentage),
                h / 2.0f, (h / 2.0f) - ((secondTextPaint.ascent() + secondTextPaint.descent()) / 2.0f), secondTextPaint)

        canvas.drawText(resources.getString(R.string.determinate_progress_format_text).format(percentage),
                h / 2.0f, (h / 2.0f) - ((textPaint.ascent() + textPaint.descent()) / 2.0f), textPaint)
    }

    private fun getDegree(percentage: Int): Float {
        val p = percentage / 100.0f
        if (p == 0.0f) {
            return 0.0f
        }
        if (p == 1.0f) {
            return 360.0f
        }
        val r = width - strokeWidth
        val h = r * Math.asin((2 * (p - 0.5f)).toDouble()) / Math.PI + 0.5f
        val d = (Math.toDegrees(Math.acos(h / (r / 2.0f))) * 2).toFloat()
        return 360.0f - d
    }

}
