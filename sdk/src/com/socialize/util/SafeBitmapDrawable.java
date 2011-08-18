package com.socialize.util;

import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;


/**
 * Because bitmaps may expire in cache while still attached to a view, we need 
 * to provide a catch-all otherwise the app will crash if a view tries to draw 
 * a recycled bitmap.
 * @author jasonpolites
 */
public class SafeBitmapDrawable extends BitmapDrawable {

	public SafeBitmapDrawable(InputStream is) {
		super(is);
	}
	
	public SafeBitmapDrawable() {
		super();
	}

	public SafeBitmapDrawable(Bitmap bitmap) {
		super(bitmap);
	}

	public SafeBitmapDrawable(Resources res, Bitmap bitmap) {
		super(res, bitmap);
	}

	public SafeBitmapDrawable(Resources res, InputStream is) {
		super(res, is);
	}

	public SafeBitmapDrawable(Resources res, String filepath) {
		super(res, filepath);
	}

	public SafeBitmapDrawable(Resources res) {
		super(res);
	}

	public SafeBitmapDrawable(String filepath) {
		super(filepath);
	}

	@Override
	public void draw(Canvas canvas) {
		Bitmap bmp = getBitmap();
		if(bmp != null && !bmp.isRecycled()) {
			super.draw(canvas);
		}
	}
	
	public boolean isRecycled() {
		Bitmap bmp = getBitmap();
		return (bmp == null || bmp.isRecycled());
	}
	
	public void recycle() {
		Bitmap bmp = getBitmap();
		if(bmp != null) {
			bmp.recycle();
		}
	}
}
