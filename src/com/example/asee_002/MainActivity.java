package com.example.asee_002;



import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	public final static String EXTRA_MESSAGE = "com.example.asee_002.MESSAGE";
	
	private ShareActionProvider mShareActionProvider;
	private List<String> mListaEjemplo;
	private ListView mListView;
	private MultiChoiceModeListener mMiListaListener = new MultiChoiceModeListener() {

	    @Override
	    public void onItemCheckedStateChanged(ActionMode mode, int position,
	                                          long id, boolean checked) {
	        // Here you can do something when items are selected/de-selected,
	        // such as update the title in the CAB
//	    	mode.setTitle(mListView.getCheckedItemCount() + " seleccionado(s)");
	    	int count = mListView.getCheckedItemCount();
	    	mode.setTitle(getResources().getQuantityString(R.plurals.selected_count,  count, count));
	    }

	    @Override
	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	        // Respond to clicks on the actions in the CAB
	        switch (item.getItemId()) {
	            case R.id.action_delete:
//	                deleteSelectedItems();
	                mode.finish(); // Action picked, so close the CAB
	                return true;
	            default:
	                return false;
	        }
	    }

	    @Override
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	        // Inflate the menu for the CAB
	        MenuInflater inflater = mode.getMenuInflater();
	        inflater.inflate(R.menu.context, menu);
	        return true;
	    }

	    @Override
	    public void onDestroyActionMode(ActionMode mode) {
	        // Here you can make any necessary updates to the activity when
	        // the CAB is removed. By default, selected items are deselected/unchecked.
	    }

	    @Override
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	        // Here you can perform updates to the CAB due to
	        // an invalidate() request
	        return false;
	    }
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mListaEjemplo = new ArrayList<String>();
		mListaEjemplo.add("Elemento 1");
		mListaEjemplo.add("Elemento 2");
		mListaEjemplo.add("Elemento 3");
		mListaEjemplo.add("Elemento 4");
        setListAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
        		R.layout.list_item,
        		R.id.titulo,
                mListaEjemplo));
		
        mListView = getListView();
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mListView.setMultiChoiceModeListener(mMiListaListener);
        
		Log.d("mainactivity","Creada Main Activity");
	}
	
	@Override
	protected void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
//		Object o = getListAdapter().getItem(position);
//		Toast.makeText(
//				this,
//				"Seleccionado: " + Integer.toString(position) + " - "
//						+ o.toString(), Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, DetailActivity.class);
		startActivity(intent);
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
        
        // Set up ShareActionProvider's default share intent
        mShareActionProvider = (ShareActionProvider) menu.findItem(R.id.action_share).getActionProvider();
        mShareActionProvider.setShareIntent(getDefaultIntent());

		return super.onCreateOptionsMenu(menu);
	}
	
	
	/** Defines a default (dummy) share intent to initialize the action provider.
	  * However, as soon as the actual content to be used in the intent
	  * is known or changes, you must update the share intent by again calling
	  * mShareActionProvider.setShareIntent()
	  */
	private Intent getDefaultIntent() {
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("image/*");
	    return intent;
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
