package com.prototype.familypoints.async;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import java.net.URL;

public class AsyncPicturesMock extends AsyncTask<Integer, Void, Bitmap> {

    private ImageView mImageView;
    private Resources mResources;
    private boolean rounded;

    public AsyncPicturesMock(Resources resources, ImageView imageView, boolean rounded) {
        this.mResources = resources;
        this.mImageView = imageView;
        this.rounded = rounded;
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        Bitmap picture = BitmapFactory.decodeResource(mResources, params[0]);
        return picture;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        RoundedBitmapDrawable mRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mResources, bitmap);
        if (rounded) {
            mRoundedBitmapDrawable.setCornerRadius(Math.min(mRoundedBitmapDrawable.getMinimumWidth(), mRoundedBitmapDrawable.getMinimumHeight()));
        }
        mImageView.setImageDrawable(mRoundedBitmapDrawable);
    }
}
