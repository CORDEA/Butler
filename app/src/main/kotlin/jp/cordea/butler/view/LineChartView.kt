package jp.cordea.butler.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import jp.cordea.butler.R

/**
 * Created by Yoshihiro Tanaka on 2016/07/29.
 */
class LineChartView : View {

    private val paint = Paint()
    private val path = Path()
    private val strokeWidth = resources.getDimension(R.dimen.line_chart_stroke_width)

    private var lines = FloatArray(0)
    private var coords = FloatArray(0)

    fun setLines(list: List<Float>) {
        this.lines = list.toFloatArray()
        this.coords = FloatArray(lines.size * 2)
        invalidate()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        paint.isAntiAlias = true
        paint.color = ContextCompat.getColor(context, R.color.colorAccent)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(size, size / 2)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (lines.size == 0) {
            return
        }
        val ls: MutableList<Float> = mutableListOf()
        for ((i, line) in lines.withIndex()) {
            if (i % 2 == 0) {
                ls.add(line)
            }
        }
        lines = ls.toFloatArray()
        lines.max()?.let { mx ->
            lines.min()?.let { mn ->
                var max = mx
                var min = mn
                if (mx == mn) {
                    max = mn + 1
                }
                if (mn > 0.0f) {
                    min = 0.0f
                }
                path.reset()
                val h = height - strokeWidth
                val dx = width.toFloat() / lines.size
                val d = max - min
                for ((i, line) in lines.withIndex()) {
                    val x = dx * (i + 1)
                    val y = (h - (((line - min).toFloat() / d) * h)) + (strokeWidth / 2.0f)
                    if (i == 0) {
                        path.moveTo(x, y)
                    }
                    path.lineTo(x, y)
                }
                canvas.drawPath(path, paint)
            }
        }
    }

}