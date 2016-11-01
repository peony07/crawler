package cm.inv.com.crawler.module.demo.util;

public class ThreadPictures extends Thread{

	private boolean flag = true;
	private String picURL = "";
	private String PIC_DIR = "";
	private String pic_name="";
	
	public String getPic_name() {
		return pic_name;
	}

	public void setPic_name(String pic_name) {
		this.pic_name = pic_name;
	}


	private int TIME_OUT = 1000000;

	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	public String getPIC_DIR() {
		return PIC_DIR;
	}

	public void setPIC_DIR(String pIC_DIR) {
		PIC_DIR = pIC_DIR;
	}

	public int getTIME_OUT() {
		return TIME_OUT;
	}

    public void setTIME_OUT(int tIME_OUT) {
		TIME_OUT = tIME_OUT;

	}
	public String domainName;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

	@Override
	public void run() {// 保存图片
		try {
			CommonUtil commonUtil = new CommonUtil();
			String fileName=commonUtil.save(this.picURL, this.PIC_DIR, this.TIME_OUT,this.pic_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
