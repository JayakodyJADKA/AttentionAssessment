package com.anuththara18.attentionassessment.consentform;

public class ConsentForm {

    private String title;
    private String description;
    private boolean expanded;

    public ConsentForm(String title, String description) {
        this.title = title;
        this.description = description;
        this.expanded = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "ConsentForm{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", expanded=" + expanded +
                '}';
    }

}
