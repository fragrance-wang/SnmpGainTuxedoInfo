package cn.com.greattimes.support.bean;

public class Demand {
	private String id;
	private String ifDescr;
	private String ifAlias;
	private Integer available;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIfDescr() {
		return ifDescr;
	}
	public void setIfDescr(String ifDescr) {
		this.ifDescr = ifDescr;
	}
	public String getIfAlias() {
		return ifAlias;
	}
	public void setIfAlias(String ifAlias) {
		this.ifAlias = ifAlias;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Demand() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Demand [id=" + id + ", ifDescr=" + ifDescr + ", ifAlias=" + ifAlias + ", available=" + available + "]";
	}
	
	
	
}
