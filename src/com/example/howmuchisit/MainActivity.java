package com.example.howmuchisit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;



public class MainActivity extends Activity {
	
	EditText enteredText;
    EditText showText;
    public static ArrayList<String> selectedValues;
    Spinner s1;
    Spinner s2;
    HashMap<String,Double> currencyValues = null;
    
	
	public MainActivity(){
		InitialSettings initialSet = new InitialSettings();
		if(selectedValues == null){
			initialSet.initialLoads();
			currencyValues = initialSet.loadCurrencies();
		}		
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        // Get the Reference of EditText
        enteredText=(EditText)findViewById(R.id.sent);
        showText=(EditText)findViewById(R.id.received);
        
        // Attach TextWatcher to EditText
        enteredText.addTextChangedListener(mTextEditorWatcher);
        
        s1 = (Spinner)findViewById(R.id.source);
    	s2 = (Spinner)findViewById(R.id.destiny);
        fillSpinner();
        
        spinnerListener();
        imageListener1();
        imageListener2();
    }
    
    private void imageListener1(){
    	ImageButton imageButton = (ImageButton) findViewById(R.id.switchButton1);
    	 
		imageButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				swap();
			}
 
		});
    }
    
    private void imageListener2(){
    	ImageButton imageButton = (ImageButton) findViewById(R.id.switchButton2);
    	 
		imageButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				swap();
			}
 
		});
    }
    
    private void spinnerListener(){
    	final Spinner spinner = (Spinner)findViewById(R.id.source);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                convertir();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        
        final Spinner spinner2 = (Spinner)findViewById(R.id.destiny);
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                convertir();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
    
    // EditTextWacther  Implementation
	private final TextWatcher  mTextEditorWatcher = new TextWatcher() {                    

    	public void beforeTextChanged(CharSequence s, int start, int count, int after){
    	}
    	
    	public void onTextChanged(CharSequence s, int start, int before, int count){
    		convertir();
    	}

    	public void afterTextChanged(Editable s){
    		convertir();
    	}
	};
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void openSettings(){
    	Intent intent = new Intent(this, Settings.class);
    	intent.putExtra("SELECTED_VALUES", selectedValues);
        //startActivity(intent);
        startActivityForResult(intent, 0);
    }
    
    
    
    public void swap(){
    	Spinner s1 = (Spinner)findViewById(R.id.source);
    	Spinner s2 = (Spinner)findViewById(R.id.destiny);
    	
    	
    	int a = s1.getSelectedItemPosition();
    	int b = s2.getSelectedItemPosition();
    	s1.setSelection(b);
    	s2.setSelection(a);
    }
    
    public void convertir(){
    	//Valor ingresado
    	String temp = enteredText.getText().toString();
    	if(!temp.equals("")){
    		
	    	double value = Double.parseDouble(temp); 
	    	
	    	//Obtiene el valor del spinner seleccionado y el destino
	    	String i1 = s1.getSelectedItem().toString();
	    	String i2 = s2.getSelectedItem().toString();
	    	
	    	//Resultado de la conversión
	    	double result = 0.0;
	    	//REVISAR
	    	if(i1.equals(i2)){
	    		result = value;
	    	}else{
	    		if(i1.equals("USD $")){
	    			result = fromUSD(value,i2);
	    		}
	    		else {
	    			if(i2.equals("USD $")){
	    				result = toUSD(value,i1);
	    			}
	    			else {
	    				double usdVal = toUSD(value,i1);
	    		    	result = fromUSD(usdVal,i2); 
	    			}
	    		}
	    	}
	    	result = Math.round(result * 100.0) / 100.0;
	    	showText.setText(result+"", TextView.BufferType.EDITABLE);
    	}
    }
    
    private double fromUSD(double value,String i2){
    	return(value * currencyValues.get(i2));
    }
    
    private double toUSD(double value,String i1){
    	return(value / currencyValues.get(i1));
    }
    
    public void fillSpinner(){
    	 
         // Creating adapter for spinner
         ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        		 android.R.layout.simple_spinner_item, selectedValues);
  
         // Drop down layout style - list view with radio button
         dataAdapter
                 .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  
         // attaching data adapter to spinner
         
         s1.setAdapter(dataAdapter);
         s2.setAdapter(dataAdapter);
    }
    
    
    
}
