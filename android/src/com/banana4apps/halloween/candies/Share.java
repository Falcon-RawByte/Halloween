package com.banana4apps.halloween.candies;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Share {



    private Context myContext = null;
    private Activity myActivity;
    private String share_link;
    private File imageFile;
    private String FOLDER = "";
    private String TAG = "";
    private byte[] byte_share;

    public Share(Context context)
    {
        myContext = context;
        myActivity = (Activity) context;
        TAG = context.getResources().getString(R.string.log_tag);
        FOLDER = context.getResources().getString(R.string.folder);
        share_link = context.getResources().getString(R.string.share_link);

    }


    private void createShareIntent(Uri uri, String caption) {

        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType("image/*");

        // Add the URI and the caption to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.putExtra(Intent.EXTRA_TEXT, caption);
        myContext.startActivity(share);
    }

    public void createInstagramIntent(Uri uri, String caption) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");

        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.putExtra(Intent.EXTRA_TEXT, caption);

        boolean sel = false;
        List<ResolveInfo> matches = myContext.getPackageManager().queryIntentActivities(share, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.instagram.android")) {
                share.setPackage(info.activityInfo.packageName);
                sel = true;
                    break;
            }
        }
        if (sel == false) {
            myContext.startActivity(Intent.createChooser(share, "Share to"));
        } else {
            myContext.startActivity(share);
        }
    }

    private void createGalleryIntent(Uri uri) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(uri);
        myContext.sendBroadcast(mediaScanIntent);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(myContext);
        builder1.setMessage(myContext.getString(R.string.saved));
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    public void shareButtons(Bitmap bitmap_share, String shareName, String sharerText)
    {
        if(sharerText.equals(""))
        {
            sharerText = myContext.getString(R.string.iplay) + " " + myContext.getString(R.string.inapp) + " " + myContext.getString(R.string.app_name) + " " + myContext.getString(R.string.hash) + " " + share_link;
        }

        if(shareName.equals("sh"))
        {
            bitmap_share = BitmapFactory.decodeResource(myContext.getResources(), R.drawable.ic_launcher);
        }

        else {
            if (bitmap_share != null) {


                OutputStream fout = null;

                File wallpaperDirectory = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES) + "/" + FOLDER + "/");
                wallpaperDirectory.mkdirs();

                String mPath = "share_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
                imageFile = new File(wallpaperDirectory, mPath);

                try {
                    fout = new FileOutputStream(imageFile);
                    bitmap_share.compress(Bitmap.CompressFormat.JPEG, 80, fout);
                    fout.flush();
                    fout.close();

                    if(shareName.equals("sh"))
                    {
                        createShareIntent(Uri.fromFile(imageFile), sharerText);
                    }
                    else if(shareName.equals("share"))
                    {
                        createShareIntent(Uri.fromFile(imageFile), sharerText);
                    }
                    else if(shareName.equals("in"))
                    {
                        createInstagramIntent(Uri.fromFile(imageFile), sharerText);
                    }
                    else if(shareName.equals("save"))
                    {
                        createGalleryIntent(Uri.fromFile(imageFile));
                    }

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(myActivity, (String)e.getMessage(),
                            Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(myActivity, (String)e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
