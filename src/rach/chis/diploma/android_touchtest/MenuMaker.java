package rach.chis.diploma.android_touchtest;


import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

public class MenuMaker {

	public static Intent getAct(Context c, MenuItem mi) { // http://developer.android.com/guide/topics/resources/menu-resource.html
		int id = mi.getItemId();
		Intent i = null;
		if (id == R.id.action_drawingactivity) {
			i = new Intent(c,DrawingActivity.class);
		}
		if (id == R.id.action_settings) {
			i = null;
		}

		return i;

	}

}
