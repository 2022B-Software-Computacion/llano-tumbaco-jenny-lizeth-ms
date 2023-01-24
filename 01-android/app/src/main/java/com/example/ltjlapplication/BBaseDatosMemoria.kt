package com.example.ltjlapplication

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador.add(BEntrenador(1,"Jenny","a@a.com"))
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Lizeth","b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Carolina","c@c.com")
                )
        }
    }
}
