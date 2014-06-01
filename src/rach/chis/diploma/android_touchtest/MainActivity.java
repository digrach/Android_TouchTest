package rach.chis.diploma.android_touchtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i =	MenuMaker.getAct(this, item);
		if (i != null) {
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		TextView txtCurrentX;
		TextView txtCurrentY;

		List<Float> xList = new ArrayList<Float>();
		List<Float> yList = new ArrayList<Float>();

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			txtCurrentX = (TextView)rootView.findViewById(R.id.txtcurrentx);
			txtCurrentY = (TextView)rootView.findViewById(R.id.txtcurrenty);

			rootView.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						Log.d("---------------->","down + " + Float.toString(event.getX()));
						txtCurrentX.setText(Float.toString(event.getX()));
						txtCurrentY.setText(Float.toString(event.getY()));
						break;
					case MotionEvent.ACTION_MOVE:
						Log.d("---------------->","move");
						txtCurrentX.setText(Float.toString(event.getX()));
						txtCurrentY.setText(Float.toString(event.getY()));
						xList.add(event.getX());
						yList.add(event.getY());
						Log.d("-----> xList size: ", Integer.toString(xList.size()));
						Log.d("-----> yList size: ", Integer.toString(yList.size()));
						break;
					case MotionEvent.ACTION_CANCEL:
						Log.d("---------------->","cancel");
						txtCurrentX.setText(Float.toString(event.getX()));
						txtCurrentY.setText(Float.toString(event.getY()));
						break;
					case MotionEvent.ACTION_UP:
						Log.d("---------------->","up");
						txtCurrentX.setText(Float.toString(event.getX()));
						txtCurrentY.setText(Float.toString(event.getY()));
						break;
					}
					return true;
				}

			});


			return rootView;
		}


	}

}
