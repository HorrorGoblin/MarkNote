package me.shouheng.notepal.async.onedrive;

import android.os.AsyncTask;

import com.onedrive.sdk.extensions.Item;

import java.io.File;

import me.shouheng.notepal.manager.onedrive.OneDriveManager;
import me.shouheng.notepal.util.LogUtils;

/**
 * Created by shouh on 2018/3/31.*/
public class FileUploadTask extends AsyncTask<File, Integer, String> {

    private String itemId;

    private ConflictBehavior conflictBehavior;

    private OneDriveManager.UploadProgressCallback<Item> uploadProgressCallback;

    FileUploadTask(String itemId,
                   ConflictBehavior conflictBehavior,
                   OneDriveManager.UploadProgressCallback<Item> uploadProgressCallback) {
        this.itemId = itemId;
        this.conflictBehavior = conflictBehavior;
        this.uploadProgressCallback = uploadProgressCallback;
    }

    @Override
    protected String doInBackground(File... files) {
        LogUtils.d(files.length);
        for (File file : files) {
            OneDriveManager.getInstance().upload(itemId, file, conflictBehavior, uploadProgressCallback);
        }
        return "executed";
    }
}
