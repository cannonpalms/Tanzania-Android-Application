package med.africa.UI;
import java.util.List;

import med.africa.pojo.Patient;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockListFragment;

public class AllFragment extends SherlockListFragment implements
		ActionBar.TabListener {
	private Fragment mFragment;
	public ArrayAdapter<Patient> adapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter= new ArrayAdapter<Patient>(getActivity(), R.layout.textview, MainActivity.allPatients());
		setListAdapter(adapter);
		}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mFragment = new AllFragment();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);
		
		
	}


	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(mFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}
	
	@Override
	public void onListItemClick (ListView l, View v, int position, long id) {
		List<Patient> all = MainActivity.allPatients();
		List<Patient> recent = MainActivity.recentPatients();
		Patient p = all.get(position);
		if(recent.contains(p)) {
			recent.remove(p);
			recent.add(0, p);
		
		}
		else {
			recent.add(0, p);
		}
		if (recent.size() > 10) {
			for (int i = recent.size() - 1; i >= 10; i--) {
				recent.remove(i);
			}
		}
		
		Intent i = new Intent(getActivity(), PatientActivity.class);
		i.putExtra("USER_ID", p.toString());
		getActivity().startActivity(i);
	}

}
