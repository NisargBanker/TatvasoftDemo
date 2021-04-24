package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOk.setOnClickListener {

            if (edtNumber.text.toString() == ""){
                Toast.makeText(this, "Please enter number", Toast.LENGTH_LONG).show()
            }else{
                val data = squareRoot(edtNumber.text.toString().toInt())
                setCube(data.toInt())
            }

        }

        btnNextActivity.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

    }

    fun squareRoot(num: Int): Double {
        var t: Double
        var sqrtroot = (num / 2).toDouble()
        do {
            t = sqrtroot
            sqrtroot = (t + num / t) / 2
        } while (t - sqrtroot != 0.0)
        return sqrtroot
    }

    private fun setCube(count: Int){
        for (i in 1..count){
            val parent = LinearLayout(this)
            parent.layoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            parent.orientation = LinearLayout.HORIZONTAL
            rootLayout.addView(parent)

            for (j in 1..count){
                val btnTag = Button(this)
                btnTag.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
                )
                parent.addView(btnTag)
                btnTag.text = "W"

                if (i == 1 && j == 1){
                    btnTag.setBackgroundColor(resources.getColor(R.color.red))
                    btnTag.text = "R"
                }

                btnTag.setOnClickListener {

                    if (btnTag.text == "W"){
                        btnTag.setBackgroundColor(resources.getColor(R.color.red))
                        btnTag.text = "R"
                    }else if (btnTag.text == "R"){
                        btnTag.setBackgroundColor(resources.getColor(R.color.purple_500))
                        btnTag.text = "B"
                        parent.getAllChildren()
                    }

                }
            }

        }
    }

    fun ViewGroup.getAllChildren(): List<View> {

        var isCompleted: Boolean = true

        val children = ArrayList<View>()
        for (i in 0 until this.childCount) {
            children.add(this.getChildAt(i))
            if ((children[i] as Button).text != "B"){
                isCompleted = false
            }
        }

        if (isCompleted){
            Toast.makeText(this@MainActivity, "Completed", Toast.LENGTH_LONG).show()
        }

        return children
    }

}