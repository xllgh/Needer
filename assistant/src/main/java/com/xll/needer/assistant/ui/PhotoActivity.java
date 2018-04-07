package com.xll.needer.assistant.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.xll.needer.assistant.R;
import com.xll.needer.assistant.bean.PhotoItemModel;
import com.xll.needer.assistant.bean.PhotoModel;
import com.xll.needer.assistant.databinding.ActivityPhotoBinding;
import com.xll.needer.assistant.eventhandler.PhotoItemHandler;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity  extends BaseActivity{
    private static final String TAG = "PhotoActivity";

    private static final int MY_WRITE_EXTERNAL_STORAGE = 1;

    /**
     * 在Android23开始，用户开始在应用应用时授予权限
     * **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPhotoBinding bind = DataBindingUtil.setContentView(this, R.layout.activity_photo);
        query(bind);
    }

    private void query(final ActivityPhotoBinding bind) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = MediaStore.Images.Media.query(PhotoActivity.this.getContentResolver(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        null, null, null);
                List<List<PhotoItemModel>> photoList = new ArrayList<>();
                List<PhotoItemModel> childList = null;
                List<PhotoItemModel> desList = new ArrayList<>();
                String lastBacketId = null;
                int count = 0;
                if (cursor != null) {
                    for(cursor.moveToFirst(); !cursor.isLast(); cursor.moveToNext()) {
                        String backetId = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_ID));
                        if (TextUtils.isEmpty(backetId)) {
                            continue;
                        }

                        String description = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DESCRIPTION));
                        String picasaId = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.PICASA_ID));
                        String data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                        PhotoItemModel model = new PhotoItemModel();
                        model.setCount(String.valueOf(count));
                        model.setName(data);
                        model.setImgUri(data);

                        if (!backetId.equals(lastBacketId)) {
                            if (childList != null && childList.size() > 0) {
                                photoList.add(childList);
                                desList.add(model);
                            }
                            childList = new ArrayList<PhotoItemModel>();
                            count = 0;
                        }
                        count ++;
                        lastBacketId = backetId;
                        childList.add(model);
                        Log.i(TAG, backetId + "-" + description + "-" + picasaId + "-" + data);
                    }
                }

                PhotoModel model = new PhotoModel();
                model.setList(desList);
                PhotoItemHandler handler = new PhotoItemHandler(PhotoActivity.this);
                bind.setPhotoHandler(handler);
                bind.setPhotoModel(model);
            }
        });
    }

    private void init(final ActivityPhotoBinding bind) {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            query(bind);
            return;
        }
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_WRITE_EXTERNAL_STORAGE);

        /*if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_WRITE_EXTERNAL_STORAGE);
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (MY_WRITE_EXTERNAL_STORAGE == requestCode) {
            if (permissions.length >= 1 && grantResults.length >= 1) {
                if (PackageManager.PERMISSION_GRANTED == grantResults[0]) {
//                    query(bind);
                    return;
                }
            }
        }

        this.finish();
    }
}
