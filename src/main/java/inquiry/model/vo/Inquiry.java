package inquiry.model.vo;

import java.sql.Timestamp;

public class Inquiry {
	
	private int inquiryNo;
	private String inquirySubject;
	private String inquiryContent;
	private String inquiryWriter;
	private Timestamp inquiryDate;
	private Timestamp updateDate;
	private int viewCount;
	
	public Inquiry() {}
	
	
	
	public Inquiry(String inquirySubject, String inquiryContent, String inquiryWriter) {
		super();
		this.inquirySubject = inquirySubject;
		this.inquiryContent = inquiryContent;
		this.inquiryWriter = inquiryWriter;
	}



	public Inquiry(int inquiryNo, String inquirySubject, String inquiryContent) {
		super();
		this.inquiryNo = inquiryNo;
		this.inquirySubject = inquirySubject;
		this.inquiryContent = inquiryContent;
	}



	public int getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}
	public String getInquirySubject() {
		return inquirySubject;
	}
	public void setInquirySubject(String inquirySubject) {
		this.inquirySubject = inquirySubject;
	}
	public String getInquiryContent() {
		return inquiryContent;
	}
	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}
	public String getInquiryWriter() {
		return inquiryWriter;
	}
	public void setInquiryWriter(String inquiryWriter) {
		this.inquiryWriter = inquiryWriter;
	}
	public Timestamp getInquiryDate() {
		return inquiryDate;
	}
	public void setInquiryDate(Timestamp inquiryDate) {
		this.inquiryDate = inquiryDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "Inquiry [inquiryNo=" + inquiryNo + ", inquirySubject=" + inquirySubject + ", inquiryContent="
				+ inquiryContent + ", inquiryWriter=" + inquiryWriter + ", inquiryDate=" + inquiryDate + ", updateDate="
				+ updateDate + ", viewCount=" + viewCount + "]";
	}

}
