/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_liuhong_app_NativeUtil */

#ifndef _Included_com_liuhong_app_NativeUtil
#define _Included_com_liuhong_app_NativeUtil
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_liuhong_app_NativeUtil
 * Method:    getServerUrl
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_liuhong_app_NativeUtil_getServerUrl
  (JNIEnv *, jclass);

/*
 * Class:     com_liuhong_app_NativeUtil
 * Method:    getMd5
 * Signature: (ILjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_liuhong_app_NativeUtil_getMd5
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     com_liuhong_app_NativeUtil
 * Method:    encrypt
 * Signature: ([B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_liuhong_app_NativeUtil_encrypt
  (JNIEnv *, jclass, jbyteArray, jbyteArray);

/*
 * Class:     com_liuhong_app_NativeUtil
 * Method:    decrypt
 * Signature: ([B[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_liuhong_app_NativeUtil_decrypt
  (JNIEnv *, jclass, jbyteArray, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif
