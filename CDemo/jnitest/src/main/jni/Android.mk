LOCAL_PATH := $(call my-dir)
#$(info $(LOCAL_PATH))


#ppsec
include $(CLEAR_VARS)
LOCAL_MODULE := ppsec
LOCAL_SRC_FILES := PPSec.c md5.c des.c

LOCAL_LDLIBS += -llog

include $(BUILD_SHARED_LIBRARY)
