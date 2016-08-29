package com.prototype.familypoints.async;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;

public class AsyncPicturesDownload extends AsyncTask<URL, Void, Bitmap> {

    private ImageView mImageView;

    public AsyncPicturesDownload(ImageView mImageView) {
        this.mImageView = mImageView;
    }

    @Override
    protected Bitmap doInBackground(URL... params) {
        //TODO: Fazer o download e o cache da imagem, usar getCacheDir() e getExternalCacheDir()
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        mImageView.setImageBitmap(bitmap);
    }
}
