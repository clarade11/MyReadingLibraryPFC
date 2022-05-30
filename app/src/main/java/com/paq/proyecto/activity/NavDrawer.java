package com.paq.proyecto.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.proyecto.R;
import com.google.android.material.navigation.NavigationView;
import com.paq.proyecto.fragments.Library;
import com.paq.proyecto.fragments.Memories;
import com.paq.proyecto.fragments.WishList;

public class NavDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navdrawer);

        //menu drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Library()).commit();
            navigationView.setCheckedItem(R.id.nav_library);}

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_memories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Memories()).commit();
                break;
            case R.id.nav_library:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Library()).commit();
                break;
            case R.id.nav_wishlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WishList()).commit();
                break;
            case R.id.nav_crearLibro:
                //Toast.makeText(this, "crear libro", Toast.LENGTH_LONG).show();
                Intent i = new Intent(NavDrawer.this, CreacionLibro.class);
                startActivity(i);
                break;
            case R.id.nav_crearRecuerdo:
                Intent i2 = new Intent(NavDrawer.this, CreacionRecuerdo.class);
                startActivity(i2);
                break;
            case R.id.nav_perfil:
                Intent i3 = new Intent(NavDrawer.this, Perfil.class);
                startActivity(i3);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //crear la opcion del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    //si item de menu opciones es pulsado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:
                Intent i = new Intent(NavDrawer.this, Perfil.class); //clase nuestra,clase a la que viajar
                startActivity(i);
                return true;
        }
        return true;
    }




}