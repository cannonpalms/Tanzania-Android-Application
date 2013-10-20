package med.africa.UI;

import java.util.ArrayList;
import java.util.List;

import med.africa.pojo.Gender;
import med.africa.pojo.HIVStatus;
import med.africa.pojo.Patient;
import med.africa.pojo.PatientBuilder;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends SherlockFragmentActivity {
	Tab recentTab, allTab;
	final Context context = this;
	String contents, format;
	ActionBar actionBar;
	RecentFragment recentFragment = new RecentFragment();
	AllFragment allFragment = new AllFragment();
	boolean hasInitializedOnce = false;

	private static final List<Patient> ALL_PATIENTS;
	private static final List<Patient> RECENT_PATIENTS;

	static {
		ALL_PATIENTS = new ArrayList<Patient>();
		RECENT_PATIENTS = new ArrayList<Patient>();
	}

	public static List<Patient> allPatients() {
		return ALL_PATIENTS;
	}

	public static List<Patient> recentPatients() {
		return RECENT_PATIENTS;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {

		if(savedInstanceState != null)
		{
			savedInstanceState.remove("android:support:fragments");
		}
		if(!(savedInstanceState!=null)) {
			new NameLoader().execute();

		}
		super.onCreate(savedInstanceState);
		actionBar =  getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setLogo(new ColorDrawable(Color.TRANSPARENT));
		actionBar.setTitle("Please Select A Patient");
		actionBar.setDisplayShowTitleEnabled(true);

		recentTab = actionBar.newTab().setTabListener(recentFragment);
		recentTab.setText("Recent");
		recentTab.setTag("0");
		actionBar.addTab(recentTab);

		allTab = actionBar.newTab().setTabListener(allFragment);
		allTab.setText("All Patients");
		allTab.setTag("1");
		actionBar.addTab(allTab);

		if(savedInstanceState!=null && savedInstanceState.containsKey("CurrentTab")) {
			int currentTab = savedInstanceState.getInt("CurrentTab");
			actionBar.setSelectedNavigationItem(currentTab);
		}


	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		int currentTabIndex = getSupportActionBar().getSelectedNavigationIndex();
		outState.putInt("CurrentTab", currentTabIndex);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Search")
		.setOnMenuItemClickListener(this.SearchButtonClickListener)
		.setIcon(med.africa.UI.R.drawable.abs__ic_search)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return super.onCreateOptionsMenu(menu);
	}

	OnMenuItemClickListener SearchButtonClickListener = new OnMenuItemClickListener() {

		@Override
		public boolean onMenuItemClick(MenuItem item) {

			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
			alertBuilder
			.setTitle("Search")
			.setIcon(med.africa.UI.R.drawable.abs__ic_search)
			.setMessage("How would you like to search?")
			.setCancelable(true)
			.setPositiveButton("Barcode", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					barcodeIntent();
				}
			})
			.setNegativeButton("Name", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					nameIntent();
				}
			});

			AlertDialog alert = alertBuilder.create();
			alert.show();



			return false;
		}
	};   

	public void nameIntent() {
		Intent i = new Intent(this, NameActivity.class);
		startActivity(i);
	}

	public void barcodeIntent() {
		new IntentIntegrator(this).initiateScan();	
	}

	public void onActivtyResult (int request, int result, Intent j) {
		Intent i = new Intent(this, PatientActivity.class);
		i.putExtra("USER_ID", "1232");
		startActivity(i);
	}

	private class NameLoader extends AsyncTask<Void,Void,Void> {

		@Override
		protected Void doInBackground(Void... params) {
			Patient patient = new PatientBuilder()
					.setRefNumber("AD-46DS")
					.setBirthDate("3/10/96")
					.setGender(Gender.MALE)
					.setFirstName("John")
					.setLastName("Doe")
					.setHivStatus(new HIVStatus(false))
					.build();
			MainActivity.allPatients().add(patient);
			return null;
		}

	}
}

