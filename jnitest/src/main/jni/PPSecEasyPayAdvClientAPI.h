#ifndef PPSEC_EASYPAY_ADV_CLIENTAPI_H
#define PPSEC_EASYPAY_ADV_CLIENTAPI_H

#include "PPSecEasyPayAdvComm.h"
#include "PPSecEasyPayClientAPI.h"

/*
datatype:EncryptSensitiveDatatype_t
data:[in]输入数据
outdata:[out]加密结果
outlen:=[in/out]
		datalen+CCM_IV_LEN+CCM_TAG_LEN
返回值：0 成功， <0 失败		
*/
int PPSecEp_EncryptSensitiveDataClient(uint8_t ctx[C_CONTEX_LEN], int datatype, const uint8_t *data, int datalen, char *outdata, int *outlen);

#endif

