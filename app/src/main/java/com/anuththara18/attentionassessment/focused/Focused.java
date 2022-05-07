package com.anuththara18.attentionassessment.focused;

public class Focused {

    private int id;
    private int childID;
    private String stimulus;
    private String colour;
    private String sequence_of_responses;
    private int totalCorrectResponses;
    private int noOfCorrectResponses;
    private int noOfCommissionErrors;
    private int noOfOmmissionErrors;
    private int meanReactionTime;
    private int totalDuration;
    private String diagnosis;

    public Focused(int id, int childID, String stimulus, String colour, String sequence_of_responses, int totalCorrectResponses, int noOfCorrectResponses, int noOfCommissionErrors, int noOfOmmissionErrors, int meanReactionTime, int totalDuration, String diagnosis) {
        this.id = id;
        this.childID = childID;
        this.stimulus = stimulus;
        this.colour = colour;
        this.sequence_of_responses = sequence_of_responses;
        this.totalCorrectResponses = totalCorrectResponses;
        this.noOfCorrectResponses = noOfCorrectResponses;
        this.noOfCommissionErrors = noOfCommissionErrors;
        this.noOfOmmissionErrors = noOfOmmissionErrors;
        this.meanReactionTime = meanReactionTime;
        this.totalDuration = totalDuration;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildID() {
        return childID;
    }

    public void setChildID(int childID) {
        this.childID = childID;
    }

    public String getStimulus() {
        return stimulus;
    }

    public void setStimulus(String stimulus) {
        this.stimulus = stimulus;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSequence_of_responses() {
        return sequence_of_responses;
    }

    public void setSequence_of_responses(String sequence_of_responses) {
        this.sequence_of_responses = sequence_of_responses;
    }

    public int getTotalCorrectResponses() {
        return totalCorrectResponses;
    }

    public void setTotalCorrectResponses(int totalCorrectResponses) {
        this.totalCorrectResponses = totalCorrectResponses;
    }

    public int getNoOfCorrectResponses() {
        return noOfCorrectResponses;
    }

    public void setNoOfCorrectResponses(int noOfCorrectResponses) {
        this.noOfCorrectResponses = noOfCorrectResponses;
    }

    public int getNoOfCommissionErrors() {
        return noOfCommissionErrors;
    }

    public void setNoOfCommissionErrors(int noOfCommissionErrors) {
        this.noOfCommissionErrors = noOfCommissionErrors;
    }

    public int getNoOfOmmissionErrors() {
        return noOfOmmissionErrors;
    }

    public void setNoOfOmmissionErrors(int noOfOmmissionErrors) {
        this.noOfOmmissionErrors = noOfOmmissionErrors;
    }

    public int getMeanReactionTime() {
        return meanReactionTime;
    }

    public void setMeanReactionTime(int meanReactionTime) {
        this.meanReactionTime = meanReactionTime;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /*
    CREATE TABLE sustainedAttention (
        id int NOT NULL AUTO_INCREMENT,
        childID int NOT NULL,
        totalCorrectResponses int NOT NULL,
        noOfCorrectResponses int NOT NULL,
        noOfCommissionErrors int NOT NULL,
        noOfOmmissionErrors int NOT NULL,
        meanReactionTime int NOT NULL,
        totalDuration int NOT NULL,
        CONSTRAINT sustainedAttention_pk PRIMARY KEY (id)
    );
    */

}
