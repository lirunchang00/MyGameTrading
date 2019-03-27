package com.example.administrator.gametrading.util;

public class Feedbacks {
    public String feedbackContent;
    public String teleContent;

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public String getTeleContent() {
        return teleContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public void setTeleContent(String teleContent) {
        this.teleContent = teleContent;
    }

    @Override
    public String toString() {
        return "Feedbacks{" +
                "feedbackContent='" + feedbackContent + '\'' +
                ", teleContent='" + teleContent + '\'' +
                '}';
    }
}
