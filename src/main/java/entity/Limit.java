package entity;

public class Limit {

	private int limitid;

	private String limitname;

	private String limiturl;

	private int ismenu;

	private int parentid;

	private String limitinfo;

	public int getLimitid() {
		return limitid;
	}

	public void setLimitid(int limitid) {
		this.limitid = limitid;
	}

	public String getLimitname() {
		return limitname;
	}

	public void setLimitname(String limitname) {
		this.limitname = limitname;
	}

	public String getLimiturl() {
		return limiturl;
	}

	public void setLimiturl(String limiturl) {
		this.limiturl = limiturl;
	}

	public int getIsmenu() {
		return ismenu;
	}

	public void setIsmenu(int ismenu) {
		this.ismenu = ismenu;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getLimitinfo() {
		return limitinfo;
	}

	public void setLimitinfo(String limitinfo) {
		this.limitinfo = limitinfo;
	}

	@Override
	public String toString() {
		return "Limit [limitid=" + limitid + ", limitname=" + limitname + ", limiturl=" + limiturl + ", ismenu="
				+ ismenu + ", parentid=" + parentid + ", limitinfo=" + limitinfo + "]";
	}

}
