package com.example.howmuchisit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Settings extends ListActivity {
	
	List<Row> rows;
	
	@SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //setContentView(R.layout.activity_settings);

        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        rows = new ArrayList<Row>(30);
        Row row = null;
        
        InitialSettings iS = new InitialSettings();
        final HashMap<String,Double> values = iS.loadCurrencies();
        final HashMap<String,String> meanings = iS.loadMeanings();
        ArrayList<String> selected = MainActivity.selectedValues;
        List<String> tableValues = new ArrayList<String>();
        Iterator<String> it = values.keySet().iterator();
        //Iterador por llaves
        
        while(it.hasNext()){
        	String key = it.next();
        	row = new Row();
        	tableValues.add(key);
        	row.setTitle(key);
        	row.setSubtitle(meanings.get(key));
        	
        	//CORREGIR
        	for(int i=0;i<tableValues.size();++i){
        		for(int j=0;j<selected.size();++j){
                	if(tableValues.get(i)==selected.get(j)){
                		row.setChecked(true);
                	}
                }
        	}
            rows.add(row);
        	
        }
        
        setListAdapter(new CustomArrayAdapter(this, rows));
        getListView().setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(Settings.this, getResources().getString(R.string.oneDollar) + "aqui poner arreglo", Toast.LENGTH_SHORT).show();
            }
        });
    } 

	//Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
	//Imprime en pantalla android como un System.out...
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
