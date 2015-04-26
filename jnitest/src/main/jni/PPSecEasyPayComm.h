#ifndef PPSEC_EASYPAY_COMM_H
#define PPSEC_EASYPAY_COMM_H


typedef enum {
    DataEncType_CLIENT = 0,
    DataEncType_SERVER
}DataEncType_t;
typedef enum {
    EncryptSensitiveDataType_ChineseCardID = 0,/*身份证*/
    EncryptSensitiveDataType_BankCardID,/*银行信用卡或借记卡*/
	EncryptSensitiveDataType_PhoneNum,/*手机号*/
	EncryptSensitiveDataType_ClientAPPFinger,/*客户端应用指纹*/
	EncryptSensitiveDataType_IMSI,
	EncryptSensitiveDataType_IMEI,
	EncryptSensitiveDataType_IPAddress,/*用于基于地址定位*/
	EncryptSensitiveDataType_DeviceID,
	EncryptSensitiveDataType_BaseStationID,/*用于基于基站定位*/
	EncryptSensitiveDataType_EMAIL,/*电子邮箱*/
	EncryptSensitiveDataType_Address,/*家庭住址*/
	EncryptSensitiveDataType_QQ,/*QQ号码*/
	EncryptSensitiveDataType_CreditExpire,/*信用卡过期时间*/
	EncryptSensitiveDataType_CVV2,
	EncryptSensitiveDataType_GameCardID,/*游戏卡卡号*/
	EncryptSensitiveDataType_RechargeableCardID,/*充值卡卡号*/
	EncryptSensitiveDataType_Password,/*密码*/
	EncryptSensitiveDataType_UserTrueName,/*用户的真实姓名*/
	EncryptSensitiveDataType_PPShortMessage,/*Paypalm 自定义短信内容*/
	EncryptSensitiveDataType_others
}EncryptSensitiveDatatype_t;

#define FINGER_PKT_LEN HASH_LEN
#define RSA_BIT_NUM 2048
#define HASH_LEN 20
#define KEY_LEN 16
#define CCM_TAG_LEN 4
#define CCM_IV_LEN 8 
#define PASSWD_LEN  32
#define SEED_LEN  (PASSWD_LEN+CCM_TAG_LEN+CCM_IV_LEN)
#define SESSIONID_LEN 32
#define PKT_ID_LEN 4
#define PKT_MAC_LEN 8
#define PKT_LEN_SIZE 4
#define PKT_HEADER_LEN (CCM_IV_LEN+SESSIONID_LEN+PKT_LEN_SIZE+PKT_MAC_LEN)
#define R2_SESSIONID_PKT_SIZE (KEY_LEN*2+SESSIONID_LEN+SEED_LEN)
#define SessionKey_BACKUP_ENC_SIZE (CCM_IV_LEN+CCM_TAG_LEN+SESSIONID_LEN+4*2+KEY_LEN)
#define CLIENT_FINGER_SIZE (FINGER_PKT_LEN+CCM_TAG_LEN+CCM_IV_LEN)
#define FILE_NAME_MAX_SIZE 512

typedef struct {
    uint16_t tag;
	uint8_t rnd[2];
	uint32_t id;
	uint8_t sessionid[SESSIONID_LEN];
	uint32_t data_length;
	uint8_t mac[PKT_MAC_LEN];

} packet_header;

#define C_CONTEX_LEN   648
#define S_CONTEX_LEN 728

void ppsec_sha1( const uint8_t *input, int ilen, uint8_t output[HASH_LEN] );

/*
与encrypt_sensitive_data配合使用，该函数用于产生敏感数据的hash值（摘要），用于敏感数据的检索
datatype:EncryptSensitiveDatatype_t
data:[in]输入数据
digest:[out]摘要
返回值：0 成功， <0 失败	
*/
int digest_sensitive_data(int datatype,const uint8_t* data,int datalen, uint8_t digest[HASH_LEN]);

/*
data:[in]应用自定义数据，对应报文中的Data字段
packet：[out]完整报文
packetlen:[in/out] = datalen+PKT_HEADER_LEN
返回值：0 成功， <0 失败	
*/
int packet_encode(uint8_t ctx[C_CONTEX_LEN],const uint8_t* data,int datalen,uint8_t* packet,int *packetlen);

/*
data:[out]应用自定义数据，对应报文中的Data字段
packet：[in]完整报文
datalen:[in/out] = packetlen-PKT_HEADER_LEN
返回值：0 成功， <0 失败	
*/
int packet_decode(uint8_t ctx[C_CONTEX_LEN],const uint8_t* packet,int packetlen,uint8_t* data,int* datalen);


/*
client                                   								server

A:
	1 
						-------------dataA------------>
																	A: 
																		
																		
						<------------dataB-------------
																		
						
B:
	over
						--------app-status-------------->

*/

#endif

