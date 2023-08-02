package com.example.trainingandroid

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.ListView
import android.widget.MediaController
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.SearchView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import com.example.trainingandroid.databinding.ActivityMainBinding
import com.example.trainingandroid.databinding.ActivityWidgetsDBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.Calendar

class WidgetsD : AppCompatActivity() {
    private lateinit var activityContext: Context
    // el tipo es de tipo Activity y el nombre de esta clase + Binding
    private lateinit var binding: ActivityWidgetsDBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // layout binding
        binding = ActivityWidgetsDBinding.inflate(layoutInflater)
        //entonces acá cambiaremos lo que normalmente es setContentView(R.layout.activity_widgets_d)
        setContentView(binding.root)
        activityContext = this
//        var ivEjemplo = findViewById<ImageView>(R.id.ivEjemplo)
//        val imageURL = "http://jotajotavm.com/img/PREMIUM-AndroidDevelopment.gif"
//        Picasso.get().load(imageURL).into(ivEjemplo)

        var webView = findViewById<WebView>(R.id.webView)
//        si no se usa esto va a cargar instagram pero va a querer cargarlo desde afeura de ela aplicacion por lo que hay que darle permisos de javascript
        var webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl("http://instagram.com")
        // Video View with url
        var videoViewWeb = findViewById<VideoView>(R.id.videoWeb)
        //direccion video web
        var mcWeb = MediaController(this)
        mcWeb.setAnchorView(videoViewWeb)
        videoViewWeb.setVideoPath("https://jotajotavm.com/img/video.mp4")
        videoViewWeb.setMediaController(mcWeb)

        var videoViewLocal = findViewById<VideoView>(R.id.videoLocal)
        var mcLocal = MediaController(this)
        mcLocal.setAnchorView(videoViewWeb)
        var path = "android.resource://" + packageName + "/" + R.raw.chef_herrera
        videoViewLocal.setVideoURI(Uri.parse(path))
        videoViewLocal.setMediaController(mcWeb)
        //para que inicia apenas se abre la actividad
        videoViewLocal.start()

        var calendarExample = findViewById<CalendarView>(R.id.cvEjemplo)
        var textViewCalendar = findViewById<TextView>(R.id.tvFecha)
        //evento de calendario
        // en orden es el objeto calendar View, year ,month , day ->
        calendarExample.setOnDateChangeListener { calendarView, year, month, day ->
            var date = "$day/${month + 1}/$year"
            textViewCalendar.text = "Fecha seleccionada: $date"
        }

        //para restringir un calendar view para futuras fechas y que no tome anteriores
        var calendar = Calendar.getInstance()
        calendar.set(2026, 5, 14)
        calendarExample.date = calendar.timeInMillis
        // se puede cambiar la forma de como se que este empieza por default en domingo de izquierda a derecha
        var fecha = calendarExample.date
        var dayCalendar = calendarExample.firstDayOfWeek
        calendarExample.firstDayOfWeek = (dayCalendar + 1) % 7
        // de esta manera estaria de lunes a domingo de izquierda a derecha

        var pbDeterminado = findViewById<ProgressBar>(R.id.pbDeterminate)
        var pbSecundario = findViewById<ProgressBar>(R.id.pbSecundaria)
        pbDeterminado.max = 200
        pbDeterminado.progress = 0
        pbSecundario.progress = 0
        pbSecundario.secondaryProgress = 0
//        para saber el avanze de la barra ya ese cargada porque en on create no se ve y toca en otro hilo
//                para eso se usa la corutinas

        // seekbar

        var sbNormal = findViewById<SeekBar>(R.id.sbNormal)
        sbNormal.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
//                    el activity context es el contexto apenas inicia el oncreate el cual da el contexto general de toda la app
                    Toast.makeText(activityContext,"Tu lo cambiaste", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        GlobalScope.launch {
            progressManager(pbDeterminado)
            progressManager(pbSecundario)
            seekBarManager(sbNormal)
        }

        var ratingBarExample = findViewById<RatingBar>(R.id.rbExpample)
        var textViewRatingBar = findViewById<TextView>(R.id.tvRating)
        ratingBarExample.rating = 4.5f
        // esta lambda primer parametro es el rating bar, el segundo el rating (numero), el b es un booleano
        ratingBarExample.setOnRatingBarChangeListener { ratingBar, rating, b ->
            textViewRatingBar.text = "Estrellas seleccionadas: ${rating}/${ratingBar.numStars}"
        }
        var numberPickerEx = findViewById<NumberPicker>(R.id.npExample)
        // viewbindng referencia al xml de una con el objeto binding del comienzo y el id especifico que pusimos en el xml

//        var numberPickerTextView = findViewById<TextView>(R.id.tvPicker)
        numberPickerEx.minValue = 1
        numberPickerEx.maxValue = 99
        numberPickerEx.value = 5
        numberPickerEx.wrapSelectorWheel = true
        numberPickerEx.setFormatter { i -> String.format("%02d",i) }
        numberPickerEx.setOnValueChangedListener { numberPicker, oldValue, newValue ->
            binding.tvPicker.text = "numero de antes $oldValue , numero nuevo $newValue"
        }


    }

    private fun seekBarManager(sbNormal: SeekBar) {
        while (true) {
            sleep(100L)
            sbNormal.incrementProgressBy(5)
        }
    }

    private fun progressManager(pbDeterminado: ProgressBar) {
        while (pbDeterminado.progress < pbDeterminado.max) {
//            para que se vea que esta incrementando
            sleep(100L)
//            pbDeterminado.progress +=5
//            o se puede usar incremetprogressby
            pbDeterminado.incrementProgressBy(5)
//            IMPORTANTISIMO ESTE IF PARA QUE SEA ESCALABLE EL CODIGO Y OTROS OBJETOS PUEDAN ENTRAR Y HACER COSAS DISTINTAS ENTRANDO A UNA MISMA FUNCION
            if (pbDeterminado.id == R.id.pbSecundaria) pbDeterminado.incrementSecondaryProgressBy(10)
        }
    }
}