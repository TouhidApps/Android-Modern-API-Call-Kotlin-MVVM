#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_touhidapps_modernApi_networkService_AllApi_baseUrlFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string mUrl = "aHR0cHM6Ly90b3VoaWRhcHBzLmNvbS8="; // https://touhidapps.com/
    return env->NewStringUTF(mUrl.c_str());
}


