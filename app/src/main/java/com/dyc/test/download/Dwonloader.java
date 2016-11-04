/*
package com.dyc.test.download;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.StatFs;
import android.support.annotation.Nullable;

import com.dyc.test.tools.L;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

*/
/**
 * Created by win7 on 2016/8/12.
 *//*

public class Dwonloader extends Service {

    public final String TAG = "Dwonloader";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.v(TAG,"onBind");
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        L.v(TAG,"onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.v(TAG,"onStartCommand");
      String url = intent.getStringExtra("url");
        HttpClient client = new DefaultHttpClient();
                client.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
        // 读取超时
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                60000);
        InputStream instream = null;
        DownloadItem downloadItem = new DownloadItem();
        try {
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                // SimpleResponse simpleResponse = AppAplication.sApiClient
                // .doRequest(simpleRequest);
                long lenth = entity.getContentLength();

                instream = entity.getContent();

                if (sdCardIsAvailable()) {
                    File dirFile = new File(APK_PATH);

                    if (!dirFile.exists()) {
                        dirFile.mkdirs();
                    }
                    File myCaptureFile = new File(APK_PATH + url.hashCode()
                            + ".fonedw");
fonedw
                    if (!myCaptureFile.exists()) {
                        myCaptureFile.createNewFile();
                    }
                    // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
                    if (getFreeDiskSpace() > lenth) {
                        try {
                            //
                            if (readInputStream(instream, lenth, showInfo,
                                    url, myCaptureFile)) {
                                L.v("download", "downApk", "success");
                                client.getConnectionManager().shutdown();
                            } else {
                                downloadItem.url = url;
                                sendDwnloadFailMessage(downloadItem);
                                L.v("download", "downApk", "fail");
                            }
                        } catch (Exception e) {
                            //
                            L.v("download", "downApk", "shutdown fail fail");
                            downloadItem.url = url;
                            sendDwnloadFailMessage(downloadItem);

                            if (instream != null)
                                instream.close();
                            client.getConnectionManager().shutdown();
                            return;
                        }
                    } else {
                        //
//                        message = new Message();
//                        message.what = SDCARD_NOSPACE;
//                        message.obj = downloadItem;
//                        downloadItem.url = url;
//                        mHandler.sendMessage(message);
//                        urlList.remove(url);
                        if (instream != null)
                            instream.close();
                        client.getConnectionManager().shutdown();
                        return;
                    }
                    // byte[] data = null;
                    // try {
                    // data =
                    // } catch (Exception e) {
                    // e.printStackTrace();
                    // urlList.remove(url);
                    // return;
                    // }
                    // // new一个文件对象用来保存图片，默认保存当前工程根目录
                    //
                    // // 创建输出流
                    //
                    // outStream.write(data);
                    // outStream.close();

                    // String filename =
                    // url.substring(url.lastIndexOf("/"));
                    L.v("changName", "downApk", "to create new file");
                    File newFile = new File(myCaptureFile.getParent()
                            + File.separator
                            + url.substring(url.lastIndexOf("/") + 1));
                    L.v("changName", "downApk", "to create new file2"
                            + newFile.getAbsolutePath());
                    if (newFile.exists()) {
                        newFile.delete();
                    }
                    try {
                        newFile.createNewFile();
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        downloadItem.url = url;
                        sendDwnloadFailMessage(downloadItem);

                        if (instream != null)
                            instream.close();
                        client.getConnectionManager().shutdown();
                        return;

                    }

                    L.v("changName", "downApk", newFile.getName());
                    myCaptureFile.renameTo(newFile);

                    downloadItem.name = showInfo;
                    downloadItem.itemPercent = 0;
                    downloadItem.url = url;
                    downloadItem.localFilePath = newFile.getAbsolutePath();
                    message = new Message();
                    message.what = DOWNLOAD_COMPLETE;
                    message.obj = downloadItem;
                    // message =
                    // mHandler.obtainMessage(DOWNLOAD_COMPLETE,downloadItem);
                    mHandler.sendMessage(message);
                    urlList.remove(url);

                } else {

                    message = new Message();
                    message.what = SDCARD_NOSPACE;
                    message.obj = downloadItem;
                    downloadItem.url = url;

                    mHandler.sendMessage(message);
                    urlList.remove(url);
                    if (instream != null)
                        try {
                            instream.close();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    client.getConnectionManager().shutdown();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            downloadItem.url = url;
            sendDwnloadFailMessage(downloadItem);
            if (instream != null)
                try {
                    instream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            client.getConnectionManager().shutdown();
        }




    return super.onStartCommand(intent, flags, startId);
    }
    */
/**
     * 检测sdcard是否可用
     *
     * @return true为可用，否则为不可用
     *//*

    public boolean sdCardIsAvailable() {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED))
            return false;
        return true;
    }
    */
/**
     * 计算SD卡的剩余空间
     *
     * @return 返回-1，说明没有安装sd卡
     *//*

    public static long getFreeDiskSpace() {
        String status = Environment.getExternalStorageState();
        long freeSpace = 0;
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getAvailableBlocks();
                freeSpace = availableBlocks * blockSize;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return -1;
        }
        return (freeSpace);
    }


    public void downloadItem(){


    }

    public boolean readInputStream(InputStream inStream, long lenth,
                                   String title, String url, File file) throws Exception {
        // ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        FileOutputStream outStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int mDownloadPrecent = 0;
        int read;
        long count = 0;
        int precent = 0;
        DownloadItem downitem = new DownloadItem();
        while ((read = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, read);
            count += read;
            precent = (int) (((double) count / lenth) * 100);
            // 每下载完成5%就通知任务栏进行修改下载进度
            if (precent - mDownloadPrecent >= 5) {
                mDownloadPrecent = precent;
                downitem.name = title;
                downitem.url = url;
                // downitem.type = type;
                Message message = new Message();
                message.what = DOWNLOAD_UPDATE;
                message.obj = downitem;
                L.v("loadpercentloadpercent:", "readInputStream", precent + "");
                mHandler.sendMessage(message);
            }
        }
        if (precent != 100)
            return false;

        outStream.close();
        inStream.close();
        return true;

        // return outStream.toByteArray();
    }
   public void sendDwnloadFailMessage(){

   }

}
*/
