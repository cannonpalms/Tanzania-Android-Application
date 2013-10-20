package med.africa.UI;

import java.util.List;

import med.africa.pojo.Patient;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;

public class NameActivity extends SherlockListActivity {
	ActionBar actionBar;
	Tab searchTab;
	ListView lv = null;
	EditText et = null;
	public ArrayAdapter<Patient> adapter;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setLogo(med.africa.UI.R.drawable.ic_launcher);
		actionBar.setTitle("Please search!");
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		setContentView(R.layout.search_layout);

		et = (EditText) findViewById(R.id.inputText);
		adapter = new ArrayAdapter<Patient>(this, R.layout.textview, MainActivity.allPatients());
		setListAdapter(adapter);
		
		
		et.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				actionBar.setTitle("Searching for: " + cs);	
				NameActivity.this.adapter.getFilter().filter(cs);
			}
		});
	}
	
	@Override
	public boolean onMenuItemSelected(int d, MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}
	
	@Override
	public void onListItemClick (ListView l, View v, int position, long id) {
		List<Patient> recent = MainActivity.recentPatients();
		Patient p = (Patient) (getListView().getItemAtPosition(position));
		if (recent.contains(p)) {
			recent.remove(p);
			recent.add(0, p);
		}
		else {
			recent.add(0, p);
		}
		if (recent.size() > 10) {
			for (int i = recent.size()-1; i>=10; i--) {
				recent.remove(i);
			}
		}
		
		Intent i = new Intent(this, PatientActivity.class);
		i.putExtra("USER_ID", p.toString());
		this.startActivity(i);
		finish();

	}

	
}