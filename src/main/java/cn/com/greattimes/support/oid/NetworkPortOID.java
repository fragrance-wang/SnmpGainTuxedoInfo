package cn.com.greattimes.support.oid;

public class NetworkPortOID {
	
	//漫步查询所有的信息
	public static final String TOTALOID = "1.3.6.1.2.1.2.2.1";
										 //1.3.6.1.2.1.2.2.1
	
	//网络接口的数目
	public static final String IFNUMBER = "1.3.6.1.2.1.2.1.0";
	
	//接口的索引
	public static final String IFINDEX = "1.3.6.1.2.1.2.2.1.1";
	
	//网络接口信息描述
	public static final String IFDESCR = "1.3.6.1.2.1.2.2.1.2";
	
	//网络接口类型
	public static final String IFTYPE = "1.3.6.1.2.1.2.2.1.3";
										 
	//接口发送和接收的最大IP数据报[BYTE]
	public static final String IFMTU = "1.3.6.1.2.1.2.2.1.4";
	
	//接口当前带宽[bps]
	public static final String IFSPEED = "1.3.6.1.2.1.2.2.1.5";
	
	//接口的物理地址
	public static final String IFPHYSADDRESS = "1.3.6.1.2.1.2.2.1.6";
	
	//人为的调整接口的状态
	public static final String IFADMINSTATUS = "1.3.6.1.2.1.2.2.1.7";
	
	//接口当前操作状态[up|down]
	public static final String IFOPERSTATUS = "1.3.6.1.2.1.2.2.1.8";
	
	//接口最后更新成当前操作状态的时刻
	public static final String IFLASTCHANGE = "1.3.6.1.2.1.2.2.1.9";
	
	//接口收到的字节数
	public static final String IFINOCTET = "1.3.6.1.2.1.2.2.1.10";
	
	//接口收到的数据包个数
	public static final String IFINUCASTPKTS = "1.3.6.1.2.1.2.2.1.11";
	
	//传递给上层网络协议的非单报文数
	public static final String IFINNUCASTPKTS  = "1.3.6.1.2.1.2.2.1.12";
	
	//被丢弃（尽管没有错误）的输入报文数，并且这些报文不会被传递给上层网络协议
	public static final String  IFINDISCARDS = "1.3.6.1.2.1.2.2.1.13";
	
	//流入的错误报文数，由于错误使得这些报文不会被传递给上层网络协议
	public static final String  IFINERRORS = "1.3.6.1.2.1.2.2.1.14";
	
	//由于未知或不支持的网络协议而丢弃的输入报文的数量
	public static final String  IFINUNKNOWNPROTOS = "1.3.6.1.2.1.2.2.1.15";
	
	//接口发送的字节数
	public static final String IFOUTOCTET = "1.3.6.1.2.1.2.2.1.16";
	
	//接口发送的数据包个数
	public static final String IFOUTUCASTPKTS = "1.3.6.1.2.1.2.2.1.17";
	
	//上层协议（如IP）需要发送给一个网络单播地址的报文数，该数量包括丢弃的或未发送的报文数
	public static final String IFOUTNUCASTPKTS = "1.3.6.1.2.1.2.2.1.18";
	
	//该设备上的输出报文队列长度
	public static final String IFOUTQLEN = "1.3.6.1.2.1.2.2.1.21";
	
	//MIB引用定义，指向一个用于实现该网络接口的特定介质类型
	public static final String IFSPECIFIC = "1.3.6.1.2.1.2.2.1.22";
}
