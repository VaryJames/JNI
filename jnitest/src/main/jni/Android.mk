LOCAL_PATH := $(call my-dir)
#$(info $(LOCAL_PATH))

#get ppsec_c2_arm7_32
include $(CLEAR_VARS)
LOCAL_MODULE := ppsec_c
LOCAL_SRC_FILES := lib/$(TARGET_ARCH_ABI)/libppsec_c.so
include $(PREBUILT_SHARED_LIBRARY)

#get ppsec_c2_arm7_32
include $(CLEAR_VARS)
LOCAL_MODULE := ppsec_c2
LOCAL_SRC_FILES := lib/$(TARGET_ARCH_ABI)/libppsec_c2.so
include $(PREBUILT_SHARED_LIBRARY)

#ppsec
include $(CLEAR_VARS)
LOCAL_MODULE := ppsec
LOCAL_SRC_FILES := PPSec.c

LOCAL_LDLIBS += -llog
LOCAL_SHARED_LIBRARIES := ppsec_c ppsec_c2

include $(BUILD_SHARED_LIBRARY)
