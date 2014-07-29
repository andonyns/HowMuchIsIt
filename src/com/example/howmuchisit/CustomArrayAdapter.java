package com.example.howmuchisit;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
 
/**
 * Custom adapter - "View Holder Pattern".
 * 
 * @author danielme.com
 * 
 */
public class CustomArrayAdapter extends ArrayAdapter<Row>
{
    private LayoutInflater layoutInflater;
 
    public CustomArrayAdapter(Context context, List<Row> objects)
    {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // holder pattern
        Holder holder = null;
        if (convertView == null)
        {
            holder = new Holder();
 
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder.setTextViewTitle((TextView) convertView.findViewById(R.id.textViewTitle));
            holder.setTextViewSubtitle((TextView) convertView.findViewById(R.id.textViewSubtitle));
            holder.setCheckBox((CheckBox) convertView.findViewById(R.id.checkBox));
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
 
        final Row row = getItem(position);
        holder.getTextViewTitle().setText(row.getTitle());
        holder.getTextViewSubtitle().setText(row.getSubtitle());
         
        holder.getCheckBox().setTag(row.getTitle());
        holder.getCheckBox().setChecked(row.isChecked());
        
        holder.getCheckBox().setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked)
            {
                //asegura que se modifica la Row originalmente asociado a este checkbox
                //para evitar que al reciclar la vista se reinicie el row que antes se mostraba en esta
                //fila. Es imprescindible tagear el Row antes de establecer el valor del checkbox
                if (row.getTitle().equals(view.getTag().toString()))
                {
                    row.setChecked(isChecked);
                    if(!isChecked){
                    	ArrayList<String> values = MainActivity.selectedValues;
                    	boolean found = false;
                    	for(int i=0;!found||i<values.size();++i){
                    		if(values.get(i)==row.getTitle()){
                    			values.remove(i);
                    			found = true;
                    		}
                    	}
                    		
                    	Toast.makeText(getContext(), "Ya no :( "+values, Toast.LENGTH_SHORT).show();
                    	MainActivity.selectedValues = values;
                    	//Quitar
                    }else{
                    	MainActivity.selectedValues.add(row.getTitle());
                    	Toast.makeText(getContext(), "Si :) "+MainActivity.selectedValues, Toast.LENGTH_SHORT).show();
                    	//Incluir
                    }
                }
                //AQUI DEBE ESTAR EL HANDLER DE QUE EL TIPO DE CAMBIO FUE INCLUIDO
            }
        });
         
         
        return convertView;
    }
 
}
 
class Holder
{
    TextView textViewTitle;
    TextView textViewSubtitle;
    CheckBox checkBox;
 
    public TextView getTextViewTitle()
    {
        return textViewTitle;
    }
 
    public void setTextViewTitle(TextView textViewTitle)
    {
        this.textViewTitle = textViewTitle;
    }
 
    public TextView getTextViewSubtitle()
    {
        return textViewSubtitle;
    }
 
    public void setTextViewSubtitle(TextView textViewSubtitle)
    {
        this.textViewSubtitle = textViewSubtitle;
    }
 
    public CheckBox getCheckBox()
    {
        return checkBox;
    }
 
    public void setCheckBox(CheckBox checkBox)
    {
        this.checkBox = checkBox;
    }   
 
}