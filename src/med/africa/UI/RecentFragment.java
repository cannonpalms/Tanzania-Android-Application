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

public class RecentFragment extends SherlockListFragment implements
		ActionBar.TabListener {
	private Fragment mFragment;
	public ArrayAdapter<Patient> adapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new ArrayAdapter<Patient>(getActivity(), R.layout.textview, MainActivity.recentPatients());
		setEmptyText("No Recent Selections");
		setListAdapter(adapter);
		}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
		mFragment = new RecentFragment();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);
        
	}


	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(mFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		/*mFragment = new AllFragment();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);
		adapter.notifyDataSetChanged();
		*/
	}
	
	@Override
	public void onListItemClick (ListView l, View v, int position, long id) {
		List<Patient> recent = MainActivity.recentPatients();
		Patient p = recent.get(position);
		Intent i = new Intent(getActivity(), PatientActivity.class);
		i.putExtra("USER_ID", p.toString());
		getActivity().startActivity(i);
		
	}

}
