package com.example.ltjlapplication

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class HGoogleMapsActivity : AppCompatActivity() {
    private  lateinit var mapa: GoogleMap

    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hgoogle_maps2)
        solicitarPermisos()
        iniciarLogicaMapa()

        val boton = findViewById<Button>(R.id.btn_ir_carolina)
        boton.setOnClickListener {
            irCarolina()
        }
    }

    fun irCarolina(){
        val carolina = LatLng(-0.1836334776902332, -78.48690577512814)
        val zoom = 17f
        moverCamaraConZoom(carolina, zoom)

    }
    fun iniciarLogicaMapa(){
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync{googleMap ->
            if(googleMap != null){
                mapa = googleMap
                establecerConfiguracionMapa()

                val quicentro = LatLng(-0.17513793360715552, -78.47960183809467)
                val titulo = "Quicentro"
                val markQuicentro = anadirMarcador(quicentro, titulo)
                markQuicentro.tag = titulo//cuando da clic en Aquicentro entonces se muestra el marcador

                val poliLineaUno = googleMap
                    .addPolyline(
                        PolylineOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.17196221276428617, -78.48432252591819),
                                LatLng(-0.17024560668268, -78.4814686555521),
                                LatLng(-0.17037435214412405, -78.47666213704088)
                            )
                    )
                poliLineaUno.tag = "Linea-1"

                //Polígono
                val poligonoUno = googleMap
                    .addPolygon(
                        PolygonOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.15316536809625367, -78.48271320052375),
                                LatLng(-0.14883093346574694, -78.49056670844834),
                                LatLng(-0.15749980185021223, -78.48455856030931)
                            )
                    )
                poligonoUno.fillColor = -0xc771c4
                poligonoUno.tag = "poligono-2" // ID
                escucharListeners()
            }
        }
    }
    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true //tenemos permisos
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun escucharListeners(){
        mapa.setOnPolygonClickListener{
            Log.i("mapa", "setOnPlygonClickListener ${it}")
            it.tag //ID
        }
        mapa.setOnPolylineClickListener{
            Log.i("mapa", "setOnMarkerClickListener ${it}")
            it.tag //Id
        }
        mapa.setOnMarkerClickListener {
            Log.i("mapa","setOnMarkerClickListener ${it}")
            it.tag //ID
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa","setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa","setOnCameraMoveStartedListener ${it}")
        }
        mapa.setOnCameraIdleListener {
            Log.i("mapa","setOnCameraIdleListener")
        }
    }
    fun anadirMarcador(latLng: LatLng, title:String):Marker{
        return mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )!!
    }
    fun moverCamaraConZoom(latLng: LatLng, zoom:Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }
    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION //permiso que va a verificar
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if(tienePermisos){
            permisos = true
        }else{
            ActivityCompat.requestPermissions(
                this, //contexto
                arrayOf( //arreglo permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1//código de petición de los prmisos
            )
        }
    }
}