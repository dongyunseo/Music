package com.Music.dto;

import java.util.Date;

public class View_CountVo {
    private String visitIp;
    private String visitId;
    private Date visitTime;
    
    
    public View_CountVo() {}
    
    // 생성자
    public View_CountVo(String visitIp, String visitId, Date visitTime) {
        this.visitIp = visitIp;
        this.visitId = visitId;
        this.visitTime = visitTime;
    }

    // Getter 및 Setter
    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    // toString 메서드 (디버깅을 위해)
    @Override
    public String toString() {
        return "ViewCountDTO{" +
                "visitIp='" + visitIp + '\'' +
                ", visitId='" + visitId + '\'' +
                ", visitTime=" + visitTime +
                '}';
    }
}
