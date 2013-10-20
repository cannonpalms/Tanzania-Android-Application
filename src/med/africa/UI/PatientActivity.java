package med.africa.UI;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;

public class PatientActivity extends SherlockFragmentActivity {
	Tab adultTab, childTab;
	final Context context = this;
	String ID;
	ActionBar actionBar;
	String SCAN_CHILD = "";
	static ArrayList<String> allNames = new ArrayList<String>();
	static ArrayList<String> recentNames = new ArrayList<String>();
	PatientFragment adultFragment = new PatientFragment();
	PatientFragment childFragment = new PatientFragment();

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		ID = i.getStringExtra("USER_ID");
		actionBar =  getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setLogo(med.africa.UI.R.drawable.ic_launcher);
		actionBar.setTitle(ID.toString());
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		adultTab = actionBar.newTab().setTabListener(adultFragment);
		adultTab.setText("Adult");
		adultTab.setTag(0);
		actionBar.addTab(adultTab);

		new InfoLoader().execute();

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Add")
		.setOnMenuItemClickListener(this.AddButtonClickListener)
		.setIcon(med.africa.UI.R.drawable.abs__ic_menu_share_holo_dark)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int d, MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			finish();
			break;
		}
		
		//update database
		return true;
	}
	
	OnMenuItemClickListener AddButtonClickListener = new OnMenuItemClickListener() {
		
		@Override
		public boolean onMenuItemClick(MenuItem item) {
						
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
			alertBuilder
			.setTitle("Add")
			.setIcon(med.africa.UI.R.drawable.abs__ab_share_pack_holo_dark)
			.setMessage("Add child?")
			.setCancelable(true)
			.setPositiveButton("Barcode", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//Toast.makeText(context, "Not implemented yet", Toast.LENGTH_SHORT).show();
					barcodeIntent();
					childTab = actionBar.newTab().setTabListener(childFragment);
					childTab.setText("Child: " + SCAN_CHILD);
					childTab.setTag(1);
					actionBar.addTab(childTab);
					
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}});
			
			AlertDialog alert = alertBuilder.create();
			alert.show();
			
			
			
			return false;
		}
	};   
	public void barcodeIntent() {
		Intent i = new Intent("com.google.zxing.client.android.SCAN");
		i.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(i, 0);
	}

	public void onActivtyResult (int requestCode, int resultCode, Intent i) {
		if (requestCode ==0) {
			if (resultCode == RESULT_OK) {
				SCAN_CHILD  = i.getStringExtra("SCAN_RESULT");


				
			}
		}
	}
	



 private class InfoLoader extends AsyncTask<Void,Void,Void> {

	@Override
	protected Void doInBackground(Void... params) {
		return null;
	}

}
}
