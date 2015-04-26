#ifndef PPSEC_EASYPAY_ADV_COMM_H
#define PPSEC_EASYPAY_ADV_COMM_H
#include <stdint.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "PPSecEasyPayComm.h"


int binary_to_hexstring( uint8_t *src, int srclen, char *dist, int *len );
int hexstring_to_binary( char *src, int srclen, uint8_t *dist, int *len );

/*
datatype:EncryptSensitiveDatatype_t
data:[in]输入数据
digest:[out]摘要
返回值：0 成功， <0 失败	
*/
int PPSecEp_DigestSensitiveData( int datatype, const char *data, int datalen, char digest[HASH_LEN * 2] );

/*
data:[in]应用自定义数据，对应报文中的Data字段
packet：[out]完整报文
packetlen:[in/out] = (datalen+PKT_HEADER_LEN) * 2
返回值：0 成功， <0 失败	
*/
int PPSecEp_PacketEncode( uint8_t ctx[C_CONTEX_LEN], const char *data, int datalen, char *packet, int *packetlen );

/*
packet：[in]完整报文
data:[out]应用自定义数据，对应报文中的Data字段
datalen:[in/out] = packetlen-PKT_HEADER_LEN
返回值：0 成功， <0 失败	
*/
int PPSecEp_PacketDecode( uint8_t ctx[C_CONTEX_LEN], const char *packet, int packetlen, char *data, int *datalen );


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

