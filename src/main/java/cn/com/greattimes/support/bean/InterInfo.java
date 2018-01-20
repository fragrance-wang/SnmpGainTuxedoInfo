package cn.com.greattimes.support.bean;


public class InterInfo {
	private String ifIndex;// 端口号
	private String ifDescr;
	private String ifType;
	private String ifMtu;
	private String ifSpeed;
	private String ifPhysAddress;
	private String ifAdminStatus;
	private String ifOperStatus;
	private String ifLastChange;
	private String ifInOctets;
	private String ifInUcastPkts;
	private String ifInNUcastPkts;
	private String ifInDiscards;
	private String ifInErrors;
	private String ifInUnknownProtos;
	private String ifOutOctets;
	private String ifOutUcastPkts;
	private String ifOutNUcastPkts;
	private String ifOutDiscards;
	private String ifOutErrors;
	private String ifOutQLen;
	private String ifSpecific;
	private String nowTime;
	private Long nowTimeForLong;

	public InterInfo() {
		super();
	}
	
	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}


	public String getIfIndex() {
		return ifIndex;
	}

	public void setIfIndex(String ifIndex) {
		this.ifIndex = ifIndex;
	}

	public String getIfDescr() {
		return ifDescr;
	}

	public void setIfDescr(String ifDescr) {
		this.ifDescr = ifDescr;
	}

	public String getIfType() {
		return ifType;
	}

	public void setIfType(String ifType) {
		this.ifType = ifType;
	}

	public String getIfMtu() {
		return ifMtu;
	}

	public void setIfMtu(String ifMtu) {
		this.ifMtu = ifMtu;
	}

	public String getIfSpeed() {
		return ifSpeed;
	}

	public void setIfSpeed(String ifSpeed) {
		this.ifSpeed = ifSpeed;
	}

	public String getIfPhysAddress() {
		return ifPhysAddress;
	}

	public void setIfPhysAddress(String ifPhysAddress) {
		this.ifPhysAddress = ifPhysAddress;
	}

	public String getIfAdminStatus() {
		return ifAdminStatus;
	}

	public void setIfAdminStatus(String ifAdminStatus) {
		this.ifAdminStatus = ifAdminStatus;
	}

	public String getIfOperStatus() {
		return ifOperStatus;
	}

	public void setIfOperStatus(String ifOperStatus) {
		this.ifOperStatus = ifOperStatus;
	}

	public String getIfLastChange() {
		return ifLastChange;
	}

	public void setIfLastChange(String ifLastChange) {
		this.ifLastChange = ifLastChange;
	}

	public String getIfInOctets() {
		return ifInOctets;
	}

	public void setIfInOctets(String ifInOctets) {
		this.ifInOctets = ifInOctets;
	}

	public String getIfInUcastPkts() {
		return ifInUcastPkts;
	}

	public void setIfInUcastPkts(String ifInUcastPkts) {
		this.ifInUcastPkts = ifInUcastPkts;
	}

	public String getIfInNUcastPkts() {
		return ifInNUcastPkts;
	}

	public void setIfInNUcastPkts(String ifInNUcastPkts) {
		this.ifInNUcastPkts = ifInNUcastPkts;
	}

	public String getIfInDiscards() {
		return ifInDiscards;
	}

	public void setIfInDiscards(String ifInDiscards) {
		this.ifInDiscards = ifInDiscards;
	}

	public String getIfInErrors() {
		return ifInErrors;
	}

	public void setIfInErrors(String ifInErrors) {
		this.ifInErrors = ifInErrors;
	}

	public String getIfInUnknownProtos() {
		return ifInUnknownProtos;
	}

	public void setIfInUnknownProtos(String ifInUnknownProtos) {
		this.ifInUnknownProtos = ifInUnknownProtos;
	}

	public String getIfOutOctets() {
		return ifOutOctets;
	}

	public void setIfOutOctets(String ifOutOctets) {
		this.ifOutOctets = ifOutOctets;
	}

	public String getIfOutUcastPkts() {
		return ifOutUcastPkts;
	}

	public void setIfOutUcastPkts(String ifOutUcastPkts) {
		this.ifOutUcastPkts = ifOutUcastPkts;
	}

	public String getIfOutNUcastPkts() {
		return ifOutNUcastPkts;
	}

	public void setIfOutNUcastPkts(String ifOutNUcastPkts) {
		this.ifOutNUcastPkts = ifOutNUcastPkts;
	}

	public String getIfOutDiscards() {
		return ifOutDiscards;
	}

	public void setIfOutDiscards(String ifOutDiscards) {
		this.ifOutDiscards = ifOutDiscards;
	}

	public String getIfOutErrors() {
		return ifOutErrors;
	}

	public void setIfOutErrors(String ifOutErrors) {
		this.ifOutErrors = ifOutErrors;
	}

	public String getIfOutQLen() {
		return ifOutQLen;
	}

	public void setIfOutQLen(String ifOutQLen) {
		this.ifOutQLen = ifOutQLen;
	}

	public String getIfSpecific() {
		return ifSpecific;
	}

	public void setIfSpecific(String ifSpecific) {
		this.ifSpecific = ifSpecific;
	}

	public Long getNowTimeForLong() {
		return nowTimeForLong;
	}

	public void setNowTimeForLong(Long nowTimeForLong) {
		this.nowTimeForLong = nowTimeForLong;
	}

	@Override
	public String toString() {
		return "InterInfo [ifIndex=" + ifIndex + ", ifDescr=" + ifDescr + ", ifType=" + ifType + ", ifMtu=" + ifMtu
				+ ", ifSpeed=" + ifSpeed + ", ifPhysAddress=" + ifPhysAddress + ", ifAdminStatus=" + ifAdminStatus
				+ ", ifOperStatus=" + ifOperStatus + ", ifLastChange=" + ifLastChange + ", ifInOctets=" + ifInOctets
				+ ", ifInUcastPkts=" + ifInUcastPkts + ", ifInNUcastPkts=" + ifInNUcastPkts + ", ifInDiscards="
				+ ifInDiscards + ", ifInErrors=" + ifInErrors + ", ifInUnknownProtos=" + ifInUnknownProtos
				+ ", ifOutOctets=" + ifOutOctets + ", ifOutUcastPkts=" + ifOutUcastPkts + ", ifOutNUcastPkts="
				+ ifOutNUcastPkts + ", ifOutDiscards=" + ifOutDiscards + ", ifOutErrors=" + ifOutErrors + ", ifOutQLen="
				+ ifOutQLen + ", ifSpecific=" + ifSpecific + ", nowTime=" + nowTime + ", nowTimeForLong="
				+ nowTimeForLong + "]";
	}
	
	
}
