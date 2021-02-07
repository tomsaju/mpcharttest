package com.mytest.chartspoc

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.listener.PieRadarChartTouchListener
import com.github.mikephil.charting.utils.ColorTemplate


class MainActivity : AppCompatActivity(), OnChartValueSelectedListener {
    var mychart:PieChart? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mychart = findViewById(R.id.my_pie_chart)

        setupPieChart()
    }

    private fun setupPieChart() {
        val entries: MutableList<PieEntry> = ArrayList()
        entries.add(PieEntry(18.5f, "Green"))
        entries.add(PieEntry(26.7f, "Yellow"))
        entries.add(PieEntry(24.0f, "Red"))
        entries.add(PieEntry(30.8f, "Blue"))

        val colorFirst = getColor(R.color.first)
        val colorSecond = getColor(R.color.second)
        val colorThird = getColor(R.color.third)
        val colorFour = getColor(R.color.four)

        val set = PieDataSet(entries, "Election Results")

        set.colors = mutableListOf(colorFirst, colorSecond, colorThird,colorFour) //F88116 , 088004, 002780,B1C31C



        val data = PieData(set)




        mychart?.setData(data)
        mychart?.setDrawEntryLabels(false)
        mychart?.setDrawSliceText(false)
        mychart?.onTouchListener = object : PieRadarChartTouchListener(mychart){
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                super.onSingleTapUp(e)
                return super.onSingleTapUp(e)
            }
        }
        mychart?.setEntryLabelTextSize(0f)
        mychart?.setTransparentCircleColor(Color.WHITE);
        mychart?.setTransparentCircleAlpha(0);
        mychart?.getData()?.setDrawValues(false)
        mychart?.centerText =  generateCenterSpannableText()

        mychart?.invalidate() // refresh
        mychart?.setOnChartValueSelectedListener(this);
        mychart?.getLegend()?.setEnabled(false);



    }

    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }


    override fun onNothingSelected() {

    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        Toast.makeText(this,"ciiked "+e?.data,Toast.LENGTH_LONG).show()
    }
}