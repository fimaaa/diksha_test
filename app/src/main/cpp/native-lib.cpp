#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_basedagger_di_ExternalDataKt_getStagingBaseUrl(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("https://jsonplaceholder.typicode.com/");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_basedagger_di_ExternalDataKt_getReleaseBaseUrl(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("https://jsonplaceholder.typicode.com/");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_basedagger_di_ExternalDataKt_getDebugBaseUrl(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("https://jsonplaceholder.typicode.com/");
}