package nl.avelon.nl.showpdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	    Button OpenPDF = (Button) findViewById(R.id.button1);
	    OpenPDF.setOnClickListener(new View.OnClickListener()
	    { 
	        @SuppressWarnings("deprecation")
			public void onClick(View v) 
	        {
	        	//Uri uriPdf = Uri.parse("android.resource://nl.avelon.nl.showpdf/pdfFile/extended notifications.pdf");
	        	//File pdfPath = new File(getFilesDir(),"pdfFile");       	
	            //File pdfFile = new File(pdfPath, "extended notifications.pdf"); 


	        	    AssetManager assetManager = getAssets();

	        	    InputStream in = null;
	        	    OutputStream out = null;
	        	    File pdfFile = new File(getFilesDir(), "extended notifications.pdf");
	        	    try
	        	    {
	        	        in = assetManager.open("extended notifications.pdf");
	        	        out = openFileOutput(pdfFile.getName(), Context.MODE_WORLD_READABLE);

	        	        copyFile(in, out);
	        	        in.close();
	        	        in = null;
	        	        out.flush();
	        	        out.close();
	        	        out = null;
	        	    } catch (Exception e)
	        	    {
	        	        Log.e("tag", e.getMessage());
	        	    }

	        	    Intent intent = new Intent(Intent.ACTION_VIEW);
	        	    intent.setDataAndType(
	        	            Uri.parse("file://" + getFilesDir() + "/extended notifications.pdf"),
	        	            "application/pdf");

	        	    startActivity(intent);
	        		        	
	            
	            
/*	            if(pdfFile.exists()) 
	            {
	                Uri path = Uri.fromFile(pdfFile); 
	                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
	                pdfIntent.setDataAndType(path, "application/pdf");
	                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

	                try
	                {
	                    startActivity(pdfIntent);
	                }
	                catch(ActivityNotFoundException e)
	                {
	                    Toast.makeText(MainActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show(); 
	                }
	            }*/

	        }
	    });		
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException
	{
	    byte[] buffer = new byte[1024];
	    int read;
	    while ((read = in.read(buffer)) != -1)
	    {
	        out.write(buffer, 0, read);
	    }
	}	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
