package com.dg.main.util;



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MongoDbUtil {
//    //mongodb connection properties
//    public static String ip = "192.168.2.172";
//    public static int port = 27017;//default port is 27017
//    public static String database = "pic";
//    //mongodb connection pool properties
//    public static int connectionsPerHost = 10;
//    public static int maxWaitTime = 120000;
//    public static int connectTimeout = 0;
//    public static MongoClientOptions options = null;
//    //author
//    public static List<MongoCredential> credential = new ArrayList<>();
//    static {
////        ip = properties.getProperty("mongo_ip");
////        database = properties.getProperty("mongo_database");
////        int _port = CommonUtil.getIntValue(properties.getProperty("mongo_port"));
////        if (_port != -1) port = _port;
////        int _conn = CommonUtil.getIntValue(properties.getProperty("connections_per_host"));
////        if (_conn != -1) connectionsPerHost = _conn;
////        int _waittime = CommonUtil.getIntValue(properties.getProperty("max_wait_time"));
////        if (_waittime != -1) maxWaitTime = _waittime;
////        int _timeout = CommonUtil.getIntValue(properties.getProperty("connect_timeout"));
////        if (_timeout != -1) connectTimeout = _timeout;
////        options = MongoClientOptions.builder().connectTimeout(connectTimeout)
////                .maxWaitTime(maxWaitTime).connectionsPerHost(connectionsPerHost).build();
////        MongoCredential credential1 = MongoCredential.createCredential(properties.getProperty("mongo_user"),
////                database, properties.getProperty("mongo_pass").toCharArray());
////        credential.add(credential1);
//    }
//
//
//private static final class MongoInstance {
//    public final static MongoClient client;
//    static {
//        client = new MongoClient(new ServerAddress(MongoDbUtil.ip, MongoDbUtil.port), MongoDbUtil.credential, MongoDbUtil.options);
//    }
//}
//
//    /**
//     * destroy pool
//     */
//    public static final void destroy() {
//        MongoInstance.client.close();
//    }
//
//    /**
//     *  get a MongoDatabase
//     * @return
//     */
//    public static MongoDatabase getDatabase() {
//        return MongoInstance.client.getDatabase(database);
//    }
//
//    /**
//     * get a MongoDatabase by Name
//     * @param databaseName
//     * @return
//     */
//    public static MongoDatabase getDatabase(String databaseName) {
//        return MongoInstance.client.getDatabase(databaseName);
//    }
//
//    /**
//     * upload file to mongo
//     * @param filename
//     * @param in
//     * @return
//     */
//    public static String uploadFileToGridFS(String filename, InputStream in) {
//        //default bucket name is fs
//        GridFSBucket bucket = GridFSBuckets.create(getDatabase());
//        ObjectId fileId = bucket.uploadFromStream(filename, in);
//        return fileId.toHexString();
//    }
//
//    /**
//     * upload file to mongo, if close is true then close the inputstream
//     * @param filename
//     * @param in
//     * @param close
//     * @return
//     */
//    public static String uploadFileToGridFS(String filename, InputStream in, boolean close) {
//        String returnId = null;
//        try {
//            returnId = uploadFileToGridFS(filename, in);
//        } finally {
//            if (close) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                   // log.info("close inputstream fail:" + e);
//                }
//            }
//        }
//        return returnId;
//    }
//
//    /**
//     * upload file to mongo
//     * @param fileName
//     * @param file
//     * @return
//     */
//    public static String uploadFileToGridFs(String fileName, File file) {
//        InputStream in = null;
//        try {
//            in = new FileInputStream(file);
//            String returnId = uploadFileToGridFS(fileName, in, true);
//            return returnId;
//        } catch (IOException e) {
//            //log.info("upload fail:" + e);
//        }
//        return null;
//    }
//
//    /**
//     * set filename = file name
//     * @param file
//     * @return
//     */
//    public static String uploadFileToGridFs(File file) {
//        return uploadFileToGridFs(file.getName(), file);
//    }
//
//    /**
//     * set filename = uuid
//     * @param file
//     * @return
//     */
//    public static String uploadFileToGridFSByUUID(File file) {
//        return uploadFileToGridFs(UUID.randomUUID().toString(), file);
//    }
//
//    /**
//     * download file for gridfs by objectid
//     * @param objectId
//     * @param out
//     */
//    public static void downloadFile(String objectId, OutputStream out) {
//        GridFSBucket bucket = GridFSBuckets.create(getDatabase());
//        bucket.downloadToStream(new ObjectId(objectId), out);
//    }
//
//    /**
//     * download file for gridfs by objectid
//     * @param objectId
//     * @param file
//     */
//    public static void downloadFile(String objectId, File file) {
//        OutputStream os = null;
//        try {
//            os = new FileOutputStream(file);
//            downloadFile(objectId, os);
//        } catch (IOException e) {
//          //  log.info("download fail:" + e);
//        } finally {
//            if (os != null) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                 //   log.info("close outputstream fail:" + e);
//                }
//            }
//        }
//    }
//
//    /**
//     * download file for gridfs by objectid
//     * @param objectId
//     * @param filename
//     */
//    public static void downloadFile(String objectId, String filename) {
//        File file = new File(filename);
//        downloadFile(objectId, file);
//    }
//
//    /**
//     * download file for gridfs by filename
//     * @param filename
//     * @param out
//     */
//    public static void downloadFileByName(String filename, OutputStream out) {
//        GridFSBucket bucket = GridFSBuckets.create(getDatabase());
//        bucket.downloadToStreamByName(filename, out);
//    }
//
//    /**
//     * download file for gridfs use stream
//     * 如果一次性读取所有字节，大于chunk size的可能会出现乱序，导致文件损坏
//     * @param objectId
//     * @param out
//     */
//    public static void downloadFileUseStream(String objectId, OutputStream out) {
//        GridFSBucket bucket = GridFSBuckets.create(getDatabase());
//        GridFSDownloadStream stream = null;
//        try {
//            stream = bucket.openDownloadStream(new ObjectId(objectId));
//            /** gridfs file */
//            GridFSFile file = stream.getGridFSFile();
//            /** chunk size */
//            int size = file.getChunkSize();
//            int len = (int)file.getLength();
//            /** loop time */
//            int cnt = len / size + (len % size == 0 ? 0 : 1);
//            byte[] bts = new byte[Math.min(len, size)];
//            try {
//                while (cnt-- > 0) {
//                    int tmp = stream.read(bts);
//                    out.write(bts, 0, tmp);
//                }
//                out.flush();
//            } catch (IOException e) {
//               // log.info("download fail:");
//            }
//        } finally {
//            if (stream != null) stream.close();
//        }
//    }
//
//
//    /**
//     * download file for gridfs use stream
//     * @param objectId
//     * @param fileName
//     */
//    public static void downloadFileUseStream(String objectId, String fileName) {
//        File file = new File(fileName);
//        downloadFileUseStream(objectId, file);
//    }
//
//    /**
//     * download file for gridfs use stream
//     * @param objectId
//     * @param file
//     */
//    public static void downloadFileUseStream(String objectId, File file) {
//        OutputStream os = null;
//        try {
//            os = new FileOutputStream(file);
//            downloadFileUseStream(objectId, os);
//        } catch (IOException e) {
//            //log.info("download fail:" + e);
//        } finally {
//            if (os != null) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    // skip
//                }
//            }
//        }
//    }
//
//    /**
//     * 将mongo gridfs的文件下载到内存
//     * @param objectId
//     * @return
//     */
//    public static byte[] downloadFileUseStream(String objectId) {
//        GridFSBucket bucket = GridFSBuckets.create(getDatabase());
//        GridFSDownloadStream stream = null;
//        try {
//            stream = bucket.openDownloadStream(new ObjectId(objectId));
//            /** gridfs file */
//            GridFSFile file = stream.getGridFSFile();
//            /** chunk size */
//            int size = file.getChunkSize();
//            int len = (int)file.getLength();
//            int readSize = Math.min(len, size);
//            byte[] returnBts = new byte[len];
//            /** offset num */
//            int offset = 0;
//            while (len > 0) {
//                int tmp;
//                if (len > readSize) {
//                    tmp = stream.read(returnBts, offset, readSize);
//                    offset += tmp;
//                } else {
//                    tmp = stream.read(returnBts, offset, len);
//                }
//                len -= tmp;
//            }
//            return returnBts;
//        } finally {
//            if (stream != null) stream.close();
//        }
//    }
//
//    /**
//     * delete file from gridfs by objectId
//     * @param objectId
//     */
//    public static void deleteByObjectId(String objectId) {
//        GridFSBucket bucket = GridFSBuckets.create(getDatabase());
//        bucket.delete(new ObjectId(objectId));
//    }

}
