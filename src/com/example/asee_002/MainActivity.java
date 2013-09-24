package com.example.asee_002;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.asee_002.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d("mainactivity","Creada Main Activity");
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "Searching "+query, Toast.LENGTH_SHORT).show();
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "Writing on the search bar: "+newText, Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	  @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle presses on the action bar items
	        switch (item.getItemId()) {
	            case R.id.action_search:
//	                openSearch();
	            	Toast.makeText(this, "Searching", Toast.LENGTH_SHORT).show();
	                return true;
	            case R.id.action_compose:
//	                composeMessage();
	                Toast.makeText(this, "Composing", Toast.LENGTH_SHORT).show();
	                return true;
	            case R.id.action_settings:
//	              composeMessage();
	              Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
	              return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	  }
}
