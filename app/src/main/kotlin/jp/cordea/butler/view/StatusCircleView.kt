package jp.cordea.butler.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import jp.cordea.butler.Color
import jp.cordea.butler.R

/**
 * Created by Yoshihiro Tanaka on 2016/07/15.
 */

class StatusCircleView : View {

    private var color: Int = R.color.statusUnknown
    private var text: Color = Color.NONE
    private var isFullText: Boolean = true

    fun setColor(color: Int) {
        this.color = color
        invalidate()
    }

    fun setText(text: Color) {
        this.text = text
        invalidate()
    }

    fun setFullText(isFullText: Boolean) {
        this.isFullText = isFullText
    }

    var charDpSize = resources.getDimension(R.dimen.char_circle_font_size_small)

    private var paint = Paint()
    private var textPaint = Paint()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        paint.isAntiAlias = true

        textPaint.isAntiAlias = true
        textPaint.textSize = charDpSize
        textPaint.textAlign = Paint.Align.CENTER
        val type = Typeface.create("sans-serif-light", Typeface.NORMAL)
        textPaint.typeface = type
        textPaint.color = ContextCompat.getColor(context, android.R.color.white)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = ContextCompat.getColor(context, color)

        val size = width / 2.0f
        canvas.drawCircle(size, size, size, paint)

        if (text != Color.NONE) {
            var t = text.name[0].toString()
            if (isFullText) {
                t = text.name
            }
            canvas.drawText(t, size, size - ((textPaint.ascent() + textPaint.descent()) / 2.0f), textPaint)
        }
    }

}
