package com.touhidapps.modernApi.networkService

import android.os.Handler
import android.os.Looper
import android.util.Log
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream
import java.io.IOException

open class ProgressRequestBody(val mFile: File, val mListener: UploadCallbacks) : RequestBody() {

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 2048
    }

    public interface UploadCallbacks {
        fun onProgressUpdate(percentage: Int)
        fun onError()
        fun onFinish()
    }

    override fun contentType(): MediaType? {
        // i want to upload only video/mp4
        //        return MediaType.parse("video/mp4");
        //        return MediaType.parse("multipart/form-data");
        return "multipart/form-data".toMediaTypeOrNull()
    }

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return mFile.length()
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        val fileLength = mFile.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val input = FileInputStream(mFile)
        var uploaded: Long = 0

        try {
            var read: Int
            val handler = Handler(Looper.getMainLooper())
            while (true) {

                read = input.read(buffer)
                if (read == -1) break

                uploaded += read.toLong()
                sink.write(buffer, 0, read)

                // Update progress on UI thread
                handler.post(ProgressUpdater(uploaded, fileLength))

                // Timeout
                //  sink.timeout().timeout(30000, TimeUnit.MILLISECONDS);

            }
        } catch (e: Exception) {
            mListener.onError()
        } finally {
            input.close()
            mListener.onFinish()
        }
    } // writeTo

    private inner class ProgressUpdater(private val mUploaded: Long, private val mTotal: Long) :
        Runnable {
        override fun run() {
            mListener.onProgressUpdate((100 * mUploaded / mTotal).toInt())
            Log.d("ttt", "run: $mUploaded---$mTotal")
        }
    }


}
