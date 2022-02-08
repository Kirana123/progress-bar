package com.example.menuoptions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    private var i = 0
    private var txtView: TextView? = null
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seek = findViewById<SeekBar>(R.id.seekBar)

        progressBar = findViewById<ProgressBar>(R.id.progress_Bar) as ProgressBar

        txtView = findViewById<TextView>(R.id.text_view)

        val btn = findViewById<Button>(R.id.show_button)

        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seek: SeekBar,progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Toast.makeText(this@MainActivity,
                    "Progress is: " + seek.progress + "%",
                    Toast.LENGTH_SHORT).show()
            }
        })

        btn.setOnClickListener {
            progressBar!!.visibility = View.VISIBLE

            i = progressBar!!.progress

            Thread(Runnable {
                while (i < 100) {
                    i += 1
                    handler.post(Runnable {
                        progressBar!!.progress = i
                        txtView!!.text = i.toString() + "/" + progressBar!!.max
                    })
                    try {
                        Thread.sleep(20)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                progressBar!!.visibility = View.INVISIBLE

            }).start()
        }
    }
}
