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
 *         Author:  Option0417 
 *   Organization:  
 *
 * =====================================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "JNIService.h"

static const char* FIELDS[] = {
	"userID",
	"accessToken",
	"userName",
	"phone",
	"groupID",
	"groupName",
	"userAvatar",
	"groupAvatar",
	"createTime",
	"updateTime",
};

static const int SIZE = 10;

const char* getJSONString(JNIEnv* , jobject );
const char* getStringField(JNIEnv* , jobject , const char* );

JNIEXPORT void JNICALL Java_op_sample_jni_JNIServiceImpl_invokeJNI
	(JNIEnv* jniEnv, jobject jObj1, jobjectArray jArray) {
	printf("Start of C program\n");

	getchar();

	jsize len = (*jniEnv)->GetArrayLength(jniEnv, jArray);
	printf("Size of Array: %d\n", len);

	jobject dataObj = (*jniEnv)->GetObjectArrayElement(jniEnv, jArray, 0); 

//	jclass dataObjClass = (*jniEnv)->GetObjectClass(jniEnv, dataObj);
//	jfieldID userIDField = (*jniEnv)->GetFieldID(jniEnv, dataObjClass, "userID", "Ljava/lang/String;");
//	jstring userID = (*jniEnv)->GetObjectField(jniEnv, dataObj, userIDField);
//	const char* userIDString = (*jniEnv)->GetStringUTFChars(jniEnv, userID, NULL);
//	const char* userIDString = getJSONString(jniEnv, dataObj);
//	printf("UserID: %s\n", userIDString);
	const char* userIDString = getJSONString(jniEnv, dataObj);

	return;
}

const char* getJSONString(JNIEnv* jniEnv, jobject jObj) {
	int index = 0;
	for (; index < SIZE; index++) {
		printf("FieldName: %s\nFieldValue: %s\n", FIELDS[index], getStringField(jniEnv, jObj, FIELDS[index]));
//		printf("Index: %d\n", index);
	}
	return "";
}

const char* getStringField(JNIEnv* jniEnv, jobject jObj, const char* fieldName) {
	jclass objClass = (*jniEnv)->GetObjectClass(jniEnv, jObj);
	jfieldID fID = (*jniEnv)->GetFieldID(jniEnv, objClass, fieldName, "Ljava/lang/String;");

	if (fID == NULL) {
		return "NULL";
	} else {
		jstring fValue = (*jniEnv)->GetObjectField(jniEnv, jObj, fID);
		const char* fString = (*jniEnv)->GetStringUTFChars(jniEnv, fValue, NULL);
		return fString;
	}
}
