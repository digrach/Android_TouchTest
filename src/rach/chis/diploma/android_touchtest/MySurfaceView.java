package rach.chis.diploma.android_touchtest;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements PropertyChangeListener {
	
	List<Float> xList = new ArrayList<Float>();
	List<Float> yList = new ArrayList<Float>();
	DigSingleTouchListener touchListener;

	public MySurfaceView(Context context) {
		super(context);
		touchListener = new DigSingleTouchListener(this);
		touchListener.addChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("x")) {
			String xs = event.getNewValue().toString();
			double dx = Double.parseDouble(xs);
			float fx = (float) dx;
			xList.add(fx);
		}
		if (event.getPropertyName().equals("y")) {
			String ys = event.getNewValue().toString();
			double dy = Double.parseDouble(ys);
			float fy = (float) dy;
			yList.add(fy);
		}
		

		Log.d("++ >>> Adding to list",Integer.toString(xList.size()));
		if (xList.size() == 1000) {
			xList.remove(0);
			yList.remove(0);
			Log.d("-->>> removing from list",Integer.toString(xList.size()));
		}
		
		drawStuff();

	}
	
	public void drawStuff() {
		SurfaceHolder holder = getHolder();
		Canvas canvas = holder.lockCanvas();
		canvas.drawColor(Color.WHITE);  
		
		Paint p = new Paint(Color.BLACK);
		p.setStyle(Style.FILL_AND_STROKE);
		for (int j = 0; j < yList.size(); j ++) {
			try {
				if(xList.get(j) > 0 && yList.get(j) > 0) {
					canvas.drawCircle(xList.get(j),yList.get(j),20,p);
				}
			} catch (IndexOutOfBoundsException ex) {
				ex.printStackTrace();
			}
		}

		holder.unlockCanvasAndPost(canvas);
	}

}
