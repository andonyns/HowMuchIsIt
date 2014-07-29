package com.example.howmuchisit;

import java.util.ArrayList;
import java.util.HashMap;

public class InitialSettings{
	
	public InitialSettings(){
		
	}
	
	public void initialLoads(){
		MainActivity.selectedValues  = new ArrayList<String>();
		MainActivity.selectedValues.add("USD $");
		MainActivity.selectedValues.add("EUR €");
	}
	
	public HashMap<String,Double> loadCurrencies(){
		HashMap<String,Double> currencies = new HashMap<String,Double>();
		currencies.put("EUR €", 0.731047591);
		currencies.put("CRC ¢", 530.0);
		currencies.put("MXN $", 13.3320);
		currencies.put("NIO C$", 0.1111);
		currencies.put("GTQ Q", 0.333);
		currencies.put("HNL L", 0.555);
		
		return currencies;
	}
	
	public HashMap<String,String> loadMeanings(){
		HashMap<String,String> meanings = new HashMap<String,String>();
		
		meanings.put("EUR €", "Europe: Euro");
		meanings.put("CRC ¢", "Costa Rica: Colon");
		meanings.put("MXN $", "Mexico: Peso");
		meanings.put("NIO C$", "Nicaragua: Cordoba");
		meanings.put("GTQ Q", "Guatemala: Peso");
		meanings.put("HNL L", "Honduras:Lempira");
		
		return meanings;
	}
}
