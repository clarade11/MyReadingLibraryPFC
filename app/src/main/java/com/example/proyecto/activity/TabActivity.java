package com.example.proyecto.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.proyecto.R;
import com.example.proyecto.adapter.PagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity  {

    TabLayout tablayout;
    ViewPager viewpager;
    TabItem tab1,tab2,tab3;


    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        //funcionamiento del tablayout
        tab();


    }


    //crear la opcion del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    //si item de menu es pulsado
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.perfil:
                Intent i  = new Intent( TabActivity.this, Perfil.class); //clase nuestra,clase a la que viajar
                startActivity(i);
                return true;
            case R.id.crearLibro:
                Intent i2  = new Intent( TabActivity.this, CreacionLibro.class); //clase nuestra,clase a la que viajar
                startActivity(i2);
                return true;
            case R.id.crearRecuerdo:
                Intent i3  = new Intent( TabActivity.this, CreacionRecuerdo.class); //clase nuestra,clase a la que viajar
                startActivity(i3);
                return true;
        }
        return true;
    }

    //menu tablayout
    private void tab() {
        tablayout=findViewById(R.id.tablayout);
        viewpager=findViewById(R.id.viewpager);
        tab1=findViewById(R.id.tabMemories);
        tab2=findViewById(R.id.tabLibrary);
        tab3=findViewById(R.id.tabWishList);


        pagerAdapter =new PagerAdapter(getSupportFragmentManager(),tablayout.getTabCount());
        viewpager.setAdapter(pagerAdapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0 || tab.getPosition()==1 || tab.getPosition()==2){
                    pagerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }


}