
package net.viralpatel.android.speechtotextdemo;

import java.util.ArrayList;

import com.viralpatel.android.speechtotextdemo.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
//import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {    
protected static final int RESULT_SPEECH = 1;   
private Button toggleButton1; 
private TextView textView1;   

@Override   
public void onCreate(Bundle savedInstanceState) {   
    super.onCreate(savedInstanceState);     
    setContentView(R.layout.activity_main);     
    textView1 = (TextView) findViewById(R.id.textView1);    
    toggleButton1 = (Button) findViewById(R.id.toggleButton1);   
    toggleButton1.setOnClickListener(new View.OnClickListener() {        
        
    	
@Override   
        public void onClick(View v) {           
            Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH);          
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");        
            try {       
                startActivityForResult(intent, RESULT_SPEECH);  
                textView1.setText("");        
            } catch (ActivityNotFoundException a) {             
                Toast t = Toast.makeText(getApplicationContext(), "Opps! Your device doesn't support Speech to Text",                           Toast.LENGTH_SHORT);            
                t.show();       
            }       
        }       
    });     
} 
@Override   
public boolean onCreateOptionsMenu(Menu menu) {         
    getMenuInflater().inflate(R.menu.activity_main, menu);      
    return true;    }  

@Override   
protected void onActivityResult(int requestCode, int resultCode, Intent data) {     
    super.onActivityResult(requestCode, resultCode, data);  
    switch (requestCode) {      
    case RESULT_SPEECH: {       
        if (resultCode == RESULT_OK && null != data) {  
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); 
            
            textView1.setText(text.get(0));
        }       
        break;  
    }       
    }   
}
}