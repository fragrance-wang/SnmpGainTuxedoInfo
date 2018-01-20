package cn.com.greattimes.support.bean;

public class IndexObject {
	private String table;
	private String Non_unique;
	private String Key_name;
	private String Seq_in_index;
	private String Column_name;
	private String Collation;
	private String Cardinality;
	private String Sub_part;
	private String Packed;
	private String Null;
	private String Index_type;
	private String Comment;
	private String Index_Comment;
	public IndexObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getNon_unique() {
		return Non_unique;
	}
	public void setNon_unique(String non_unique) {
		Non_unique = non_unique;
	}
	public String getKey_name() {
		return Key_name;
	}
	public void setKey_name(String key_name) {
		Key_name = key_name;
	}
	public String getSeq_in_index() {
		return Seq_in_index;
	}
	public void setSeq_in_index(String seq_in_index) {
		Seq_in_index = seq_in_index;
	}
	public String getColumn_name() {
		return Column_name;
	}
	public void setColumn_name(String column_name) {
		Column_name = column_name;
	}
	public String getCollation() {
		return Collation;
	}
	public void setCollation(String collation) {
		Collation = collation;
	}
	public String getCardinality() {
		return Cardinality;
	}
	public void setCardinality(String cardinality) {
		Cardinality = cardinality;
	}
	public String getSub_part() {
		return Sub_part;
	}
	public void setSub_part(String sub_part) {
		Sub_part = sub_part;
	}
	public String getPacked() {
		return Packed;
	}
	public void setPacked(String packed) {
		Packed = packed;
	}
	public String getNull() {
		return Null;
	}
	public void setNull(String null1) {
		Null = null1;
	}
	public String getIndex_type() {
		return Index_type;
	}
	public void setIndex_type(String index_type) {
		Index_type = index_type;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getIndex_Comment() {
		return Index_Comment;
	}
	public void setIndex_Comment(String index_Comment) {
		Index_Comment = index_Comment;
	}
	@Override
	public String toString() {
		return "IndexObject [table=" + table + ", Non_unique=" + Non_unique + ", Key_name=" + Key_name
				+ ", Seq_in_index=" + Seq_in_index + ", Column_name=" + Column_name + ", Collation=" + Collation
				+ ", Cardinality=" + Cardinality + ", Sub_part=" + Sub_part + ", Packed=" + Packed + ", Null=" + Null
				+ ", Index_type=" + Index_type + ", Comment=" + Comment + ", Index_Comment=" + Index_Comment + "]";
	}
	
	
	
	
	
	
	
}
