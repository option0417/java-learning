/*
 * =====================================================================================
 *
 *       Filename:  JNIService.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  02/26/2014 10:15:15 PM
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  YOUR NAME (), 
 *   Organization:  
 *
 * =====================================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "JNIService.h"

// jobject GetObjectArrayElement(JNIEnv *env, jobjectArray array, jsize index);
JNIEXPORT void JNICALL Java_op_sample_jni_JNIServiceImpl_invokeJNI
	(JNIEnv* jniEnv, jobject jObj1, jobjectArray jArray) {
	printf("Start of C program\n");

	getchar();

	jsize len = (*jniEnv)->GetArrayLength(jniEnv, jArray);
	printf("Size of Array: %d\n", len);

	jobject dataObj = (*jniEnv)->GetObjectArrayElement(jniEnv, jArray, 0); 

	jclass dataObjClass = (*jniEnv)->GetObjectClass(jniEnv, dataObj);
	jfieldID userIDField = (*jniEnv)->GetFieldID(jniEnv, dataObjClass, "userID", "Ljava/lang/String;");
	jstring userID = (*jniEnv)->GetObjectField(jniEnv, dataObj, userIDField);
	const char* userIDString = (*jniEnv)->GetStringUTFChars(jniEnv, userID, NULL);

	printf("UserID: %s\n", userIDString);

	return;
}
