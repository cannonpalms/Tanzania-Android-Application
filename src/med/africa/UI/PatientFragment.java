package med.africa.UI;
import med.africa.pojo.Patient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;

public class PatientFragment extends SherlockFragment implements
ActionBar.TabListener {
	private Fragment mFragment;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//inflater.inflate(med.africa.UI.R.layout.patient_layout, container);
		/*

		TextView t = new TextView(getActivity());
		s.addView(t);
		t.setText("hello!!!!!!!!!!");
		 */
		View view = inflater.inflate(R.layout.patient_layout, container, false);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		Patient patient = MainActivity.recentPatients().get(0);
		new PatientLoader().execute(patient);

	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {

		mFragment = new PatientFragment();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);


	}


	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.hide(mFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		/*if (mFragment.isVisible()) {

		}
		else {
			mFragment = new PatientFragment();
			ft.add(android.R.id.content, mFragment);
			ft.attach(mFragment);

		}
		 */
	}


	private class PatientLoader extends AsyncTask<Patient,Void,Void> {

		private Patient patient;

		protected Void doInBackground(Patient... params) {
			this.patient = params[0];
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			TextView ref = (TextView) getActivity().findViewById(R.id.referencenumber);
			ref.setText(patient.getRefNumber());
			TextView dob = (TextView) getActivity().findViewById(R.id.dateofbirth);
			dob.setText(patient.getBirthDate());
			TextView gen = (TextView) getActivity().findViewById(R.id.gender);
			gen.setText(patient.getGender().toString());
			TextView treat = (TextView) getActivity().findViewById(R.id.treatmentcodes);
			treat.setText(patient.getTreatmentCodeStr());
			TextView cond = (TextView) getActivity().findViewById(R.id.condition);
			cond.setText(patient.getConditionCodeStr());
		}
	}




}
