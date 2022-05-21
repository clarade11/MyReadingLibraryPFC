package com.example.proyecto.adapter;

import android.content.Context;
import android.widget.Toast;

public class Seguridad {



        public static String introduccionURL(String urlUsuario, Context context){
            String url = "";

//            if(urlUsuario.contains("https://www.amazon.es/")){
//                url="";
//                Toast.makeText(context, "Url incorrecta ", Toast.LENGTH_SHORT).show();
//            } else if(urlUsuario.contains("https://www.google.com/search?")){
//                url="";
//                Toast.makeText(context, "Url incorrecta ", Toast.LENGTH_SHORT).show();
//            } else if(urlUsuario.contains(".png")){
//                url=urlUsuario;
//            } else if (urlUsuario.contains((".jpg"))){
//                url=urlUsuario;
//            }

            if (urlUsuario.contains(".jpg") || urlUsuario.contains(".png")){
                url=urlUsuario;
            } else {
                url="";
                Toast.makeText(context, "Url incorrecta ", Toast.LENGTH_SHORT).show();
            }


            return url;
        }

    public static Double introduccionPrecio(String precio) {
            Double p=0.0;
        if(precio==null || precio.equals("")){
            p=0.0;
        } else if(precio.contains(",")){ //asegurarnos que el valor introducido es correcto
            p = Double.parseDouble(precio.replace(",","."));
        }
            return p;
    }
}
