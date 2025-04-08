package com.example.atividadecoding

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botão "Abrir Navegador"
        val btnOpenBrowser = findViewById<Button>(R.id.btnOpenBrowser)
        btnOpenBrowser.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, "http://www.google.com".toUri())
            startActivity(browserIntent)
        }

        // Botão "Fazer Ligação"
        val btnMakeCall = findViewById<Button>(R.id.btnMakeCall)
        val edtPhoneNumber = findViewById<EditText>(R.id.edtPhoneNumber)
        btnMakeCall.setOnClickListener {
            val phone = edtPhoneNumber.text.toString()
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = "tel:$phone".toUri()
            startActivity(dialIntent)
        }
        // Botão "Enviar SMS"
        val btnSendSMS = findViewById<Button>(R.id.btnSendSMS)
        btnSendSMS.setOnClickListener {
            val smsNumber = "81998486942"
            val smsText = "Olá! Essa é uma mensagem enviada pelo app :)"
            val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = "sms:$smsNumber".toUri()
                putExtra("sms_body", smsText)
            }
            startActivity(smsIntent)
        }
        // Botão "Abrir Mapa"
        val btnOpenMap = findViewById<Button>(R.id.btnOpenMap)
        btnOpenMap.setOnClickListener {
            val locationUri = "geo:-8.0476,-34.8770?q=Recife, Brasil".toUri()
            val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
        // Botão "Compartilhar Texto"
        val btnShareText = findViewById<Button>(R.id.btnShareText)
        btnShareText.setOnClickListener {
            val shareText = "Olá, estou aprendendo Android!"
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Compartilhar via"))
        }
    }
}
