#ifndef PPSEC_EASYPAY_CLIENTAPI_H
#define PPSEC_EASYPAY_CLIENTAPI_H

#include"PPSecEasyPayComm.h"

/*
ctx:[in/out]调用者分配的一段内存，由初始化函数做初始化。
root_path[in]一个app可写的路径
*/
int ppsec_easypay_init_client(uint8_t ctx[C_CONTEX_LEN],const char* root_path);

/*
packet =  Tag（1bytes）	Len(2 bytes)	HttpSessionID（32bytes）	app_id（4types）	Data	Mac(8 bytes)
app_id：应用ID，用于在数据库中检索，每个app有一个唯一的ID。
返回值：0 成功， <0 失败	
*/
int exchange_key_client_A(uint8_t ctx[C_CONTEX_LEN],uint32_t app_id,uint8_t packet[RSA_BIT_NUM/8+PKT_HEADER_LEN]);

/*
packet:服务端密钥交换报文
返回值：0 成功， <0 失败	
*/
int exchange_key_client_B(uint8_t ctx[C_CONTEX_LEN],const uint8_t packet[PKT_HEADER_LEN+R2_SESSIONID_PKT_SIZE]);


/*
datatype:EncryptSensitiveDatatype_t
data:[in]输入数据
outdata:[out]加密结果
outlen:=[in/out]
		datalen+CCM_IV_LEN+CCM_TAG_LEN
返回值：0 成功， <0 失败		
*/
int encrypt_sensitive_data_client(uint8_t ctx[C_CONTEX_LEN],int datatype,const uint8_t* data,int datalen, uint8_t* outdata,int* outlen);

#endif

