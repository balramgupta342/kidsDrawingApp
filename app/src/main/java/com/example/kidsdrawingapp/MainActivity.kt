package com.example.kidsdrawingapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import com.example.kidsdrawingapp.R.*

class MainActivity : AppCompatActivity() {

    private var drawingView:DrawingView? = null
    private var mImageButtonCurrentPaint:ImageButton?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        drawingView = findViewById(id.drawing_view)
        drawingView?.setSizeBrush(10.toFloat())

        val linearlayoutPaintColors = findViewById<LinearLayout>(R.id.ll_paint_colors)
        mImageButtonCurrentPaint = linearlayoutPaintColors[2] as ImageButton

        val ibEraser : ImageButton= findViewById(R.id.btnEraser)
        ibEraser.setOnClickListener {
//            drawingView!!.eraserButton()
        }


        val ibBrush : ImageButton = findViewById(id.ib_brush)
        ibBrush.setOnClickListener {
            showBrushSizeChooserDialog()
        }
    }

    private fun showBrushSizeChooserDialog(){
        val brushDialog = Dialog(this)
        brushDialog.setContentView(layout.dialoge_brush_size)
        brushDialog.setTitle("Brush Size: ")
        val smallBtn = brushDialog.findViewById<ImageButton>(id.ib_small_brush)
        smallBtn.setOnClickListener{
            drawingView?.setSizeBrush(10.toFloat())
            brushDialog.dismiss()
        }

        val mediumBtn = brushDialog.findViewById<ImageButton>(id.ib_medium_brush)
        mediumBtn.setOnClickListener{
            drawingView?.setSizeBrush(20.toFloat())
            brushDialog.dismiss()
        }

        val largeBtn = brushDialog.findViewById<ImageButton>(id.ib_large_brush)
        largeBtn.setOnClickListener{
            drawingView?.setSizeBrush(30.toFloat())
            brushDialog.dismiss()
        }

        brushDialog.show()
    }

    fun paintClicked(view: View){
        if(view !== mImageButtonCurrentPaint){
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView?.setColor(colorTag)

            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.palette_pressed)
            )

            mImageButtonCurrentPaint!!.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallet_normal)
            )

            mImageButtonCurrentPaint = view
        }
    }



}