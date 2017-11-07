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

static const char* STRING_FIELDS[] = {
	"userID",
	"accessToken",
	"userName",
	"phone",
	"groupID",
	"groupName",
	"userAvatar",
	"groupAvatar"
};

static const char* LONG_FIELDS[] = {
	"createTime",
	"updateTime"
};

static const char* JSON_FIELDS[] = {
	"ui",
	"at",
	"un",
	"pn",
	"gi",
	"gn",
	"ua",
	"ga",
	"ct",
	"ut"
};

static const int STRING_FIELD_SIZE = 8;
static const int LONG_FIELD_SIZE = 2;
static const int JSON_FIELD_SIZE = 10;

const char* getJSONString(JNIEnv*, jobject );
const char* getStringField(JNIEnv*, jobject, const char* );
const long getLongField(JNIEnv*, jobject, const char*);
char* getJSONElement(const char*, const char*);


JNIEXPORT void JNICALL Java_op_sample_jni_JNIServiceImpl_invokeJNI
	(JNIEnv* jniEnv, jobject jObj1, jobjectArray jArray) {
//	printf("Start of C program\n");

	//getchar();

	jsize len = (*jniEnv)->GetArrayLength(jniEnv, jArray);
//	printf("Size of Array: %d\n", len);


	int index = 0;
	while (index < len) {
		jobject dataObj = (*jniEnv)->GetObjectArrayElement(jniEnv, jArray, index);
		const char* userIDString = getJSONString(jniEnv, dataObj);
		index++;
	}

	return;
}

const char* getJSONString(JNIEnv* jniEnv, jobject jObj) {
	char** charPtrHolder = (char**)malloc(sizeof(char*) * 10);
	int holderIndex = 0;

	int index = 0;
	for (; index < STRING_FIELD_SIZE; index++) {
		const char* stringValue = getStringField(jniEnv, jObj, STRING_FIELDS[index]);
		//printf("FieldName: %s\nFieldValue: %s\n", STRING_FIELDS[index], stringValue);

		char* jsonElement = getJSONElement(JSON_FIELDS[holderIndex], stringValue);
		//printf("JSONString: %s\n", jsonElement);
		charPtrHolder[holderIndex++] = jsonElement;
	}

	index = 0;
	for (; index < LONG_FIELD_SIZE; index++) {
		long lValue = getLongField(jniEnv, jObj, LONG_FIELDS[index]);
		//printf("FieldName: %s\nFieldValue: %ld\n", LONG_FIELDS[index], lValue);
		char str[256];
		sprintf(str, "%lu", lValue);
		//printf("LongString: %s\n", str);

		char* jsonElement = getJSONElement(JSON_FIELDS[holderIndex], str);
		//printf("JSONFormat: %s\n", jsonElement);
		charPtrHolder[holderIndex++] = jsonElement;
	}
/*
	printf("{");
	index = 0;
	while (index < holderIndex) {
		printf("%s", charPtrHolder[index]);
		printf(",");
		index++;
	}
	printf("}\n");
*/
	return "";
}

// "ui":"6c409c3e-3199-4339-8054-ad5d2469db28"
char* getJSONElement(const char* jsonKey, const char* jsonValue) {
	size_t keySize = 0;
	size_t valueSize = 0;

	while ((*jsonKey++) != '\0') {
		keySize++;
	}
	jsonKey -= keySize;
	jsonKey--;

	while ((*jsonValue++) != '\0') {
		valueSize++;
	}
	jsonValue -= valueSize;
	jsonValue--;

	size_t elementSize = keySize + valueSize + 2 + 1 + 2 + 1;
	char* jsonElement = (char*)malloc(sizeof(char) * elementSize);

	//printf("KeySize: %lu\n", keySize);
	//printf("ValueSize: %lu\n", valueSize);
	//printf("ElementSize: %lu\n", elementSize);
	
	int index = 0;
	jsonElement[index++] = '\"';
	while (keySize-- > 0) {
		jsonElement[index++] = (*jsonKey++);
	}
	jsonElement[index++] = '\"';

	jsonElement[index++] = ':';

	jsonElement[index++] = '\"';
	while (valueSize-- > 0) {
		jsonElement[index++] = (*jsonValue++);
	}
	jsonElement[index++] = '\"';
	jsonElement[index++] = '\0';

	//printf("SizeOfJSON: %d\n", index);
	return jsonElement;
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

const long getLongField(JNIEnv* jniEnv, jobject jObj, const char* fieldName) {
	jclass objClass = (*jniEnv)->GetObjectClass(jniEnv, jObj);
	jfieldID fID = (*jniEnv)->GetFieldID(jniEnv, objClass, fieldName, "J");

	if (fID == NULL) {
		return 0;
	} else {
		jlong fValue = (*jniEnv)->GetLongField(jniEnv, jObj, fID);
		//printf("Long: %ld\n", (long)fValue);
		//const char* fString = (*jniEnv)->GetStringUTFChars(jniEnv, fValue, NULL);
		return fValue;
	}
}
