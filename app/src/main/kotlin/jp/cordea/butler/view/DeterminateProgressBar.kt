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
 * Created by Yoshihiro Tanaka on 2016/07/15.
 */
class DeterminateProgressBar : View {

    private val color = ContextCompat.getColor(context, R.color.colorAccent)

    private var paint = Paint()
    private var backgroundPaint = Paint()
    private var textPaint = Paint()

    private var rectF = RectF()

    private var progress = 0.0f
    private var progressText = "0 %"

    private var isTextVisible = false

    fun setProgress(progress: Int) {
        progressText = resources.getString(R.string.determinate_progress_format_text).format(progress)
        this.progress = (progress / 100.0f) * 360.0f
        invalidate()
    }

    fun setTextVisible(isTextVisible: Boolean) {
        this.isTextVisible = isTextVisible
        invalidate()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = color

        backgroundPaint.isAntiAlias = true
        backgroundPaint.style = Paint.Style.STROKE
        backgroundPaint.color = ContextCompat.getColor(context, R.color.statusUnknown)

        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.CENTER
        val type = Typeface.create("sans-serif-light", Typeface.NORMAL)
        textPaint.typeface = type
        textPaint.color = ContextCompat.getColor(context, R.color.primaryText)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val size = width.toFloat()
        paint.strokeWidth = size / 10.0f
        backgroundPaint.strokeWidth = paint.strokeWidth
        val halfStroke = paint.strokeWidth / 2.0f
        rectF.set(0f + halfStroke, 0f + halfStroke, size - halfStroke, size - halfStroke)
        canvas.drawArc(rectF, 0.0f, 360.0f, false, backgroundPaint)
        canvas.drawArc(rectF, 270.0f, progress, false, paint)

        if (isTextVisible) {
            textPaint.textSize = size / 4.0f
            canvas.drawText(progressText, size / 2.0f, (size / 2.0f) - ((textPaint.ascent() + textPaint.descent()) / 2.0f), textPaint)
        }
    }

}