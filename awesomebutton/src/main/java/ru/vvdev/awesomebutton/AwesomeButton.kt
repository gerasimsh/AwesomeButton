package ru.vvdev.awesomebutton

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.annotation.Dimension
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by alexanderklimov on 6/3/18.
 */

class AwesomeButton(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    internal lateinit var nullView: CardView
    internal lateinit var firstView: CardView
    internal lateinit var secondView: CardView
    internal lateinit var button: RelativeLayout
    internal lateinit var buttonView: CardView
    internal lateinit var textView: TextView
    internal var backColor: Int = 0
    internal var backColor1: Int = 0
    internal var backColor2: Int = 0
    internal var backColor3: Int = 0
    internal var text: String? = null
    internal var textSize: Float? = null
    internal var textColor: Int = 0xFFFFFF

    init {
        init(attrs)
    }


    private fun init(attrs: AttributeSet) {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.view_button_layout, this)
        button = v.findViewById(R.id.button)
        nullView = v.findViewById(R.id.null_outline)
        buttonView = v.findViewById(R.id.button_view)
        secondView = v.findViewById(R.id.second_outline)
        firstView = v.findViewById(R.id.first_outline)
        textView = v.findViewById(R.id.text)
        button.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> animateDown()
                MotionEvent.ACTION_UP -> animateUp()
            }
            true
        }

        nullView.animate().alpha(0.0f).start()
        secondView.animate().alpha(0.0f).start()
        firstView.animate().alpha(0.0f).start()

        setStyle(attrs)

    }

    @SuppressLint("CustomViewStyleable", "Recycle")
    private fun setStyle(attrs: AttributeSet) {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.awesome_button)
        backColor = arr.getColor(R.styleable.awesome_button_background_view, resources.getColor(R.color.colorPrimary))
        backColor1 = arr.getColor(R.styleable.awesome_button_background_first_view, resources.getColor(R.color.colorPrimary))
        backColor2 = arr.getColor(R.styleable.awesome_button_background_second_view, resources.getColor(R.color.colorPrimary))
        backColor3 = arr.getColor(R.styleable.awesome_button_background_third_view, resources.getColor(R.color.colorPrimary))
        if (backColor1 == resources.getColor(R.color.colorPrimary))
            backColor1 = backColor
        if (backColor2 == resources.getColor(R.color.colorPrimary))
            backColor2 = backColor
        if (backColor3 == resources.getColor(R.color.colorPrimary))
            backColor3 = backColor

        text = arr.getString(R.styleable.awesome_button_text_view)
        textColor = arr.getColor(R.styleable.awesome_button_text_color, resources.getColor(R.color.colorPrimary))
        textSize = arr.getDimension(R.styleable.awesome_button_text_size, 32F)

        buttonView.setCardBackgroundColor(backColor)
        secondView.setCardBackgroundColor(backColor1)
        firstView.setCardBackgroundColor(backColor2)
        nullView.setCardBackgroundColor(backColor3)

        textView.text = text
        textView.setTextColor(textColor)
        textView.textSize = textSize as Float


    }

    fun animateUp() {
        nullView.animate().alpha(0.0f).setDuration(50).start()
        secondView.animate().alpha(0.0f).setDuration(200).start()
        firstView.animate().alpha(0.0f).setDuration(100).start()
    }

    fun animateDown() {
        secondView.visibility = View.VISIBLE
        firstView.visibility = View.VISIBLE
        nullView.visibility = View.VISIBLE
        secondView.animate().alpha(0.3f).setDuration(100).start()
        firstView.animate().alpha(0.2f).setDuration(150).start()
        nullView.animate().alpha(0.1f).setDuration(300).start()

    }

    fun setText(text: String) {
        textView.text = text
    }

    private fun setBackground(color: Int) {
        buttonView.setCardBackgroundColor(color)
        secondView.setCardBackgroundColor(color)
        firstView.setCardBackgroundColor(color)
        nullView.setCardBackgroundColor(color)
    }

    fun setBackground(color: String) {
        setBackground(Color.parseColor(color))
    }


}
