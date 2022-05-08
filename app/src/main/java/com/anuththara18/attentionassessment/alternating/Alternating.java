package com.anuththara18.attentionassessment.alternating;

public class Alternating {

    private int id;
    private int childID;
    private String sequence_of_responses;
    private String sequence_of_stimuli;
    private String sequence_of_sides;
    private int totalCorrectResponses;
    private int noOfCorrectResponses;
    private int noOfCommissionErrors;
    private int noOfOmmissionErrors;
    private int meanReactionTime;
    private int totalDuration;
    private String diagnosis;

    public Alternating(int id, int childID, String sequence_of_responses, String sequence_of_stimuli, String sequence_of_sides, int totalCorrectResponses, int noOfCorrectResponses, int noOfCommissionErrors, int noOfOmmissionErrors, int meanReactionTime, int totalDuration, String diagnosis) {
        this.id = id;
        this.childID = childID;
        this.sequence_of_responses = sequence_of_responses;
        this.sequence_of_stimuli = sequence_of_stimuli;
        this.sequence_of_sides = sequence_of_sides;
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

    public String getSequence_of_responses() {
        return sequence_of_responses;
    }

    public void setSequence_of_responses(String sequence_of_responses) {
        this.sequence_of_responses = sequence_of_responses;
    }

    public String getSequence_of_stimuli() {
        return sequence_of_stimuli;
    }

    public void setSequence_of_stimuli(String sequence_of_stimuli) {
        this.sequence_of_stimuli = sequence_of_stimuli;
    }

    public String getSequence_of_sides() {
        return sequence_of_sides;
    }

    public void setSequence_of_sides(String sequence_of_sides) {
        this.sequence_of_sides = sequence_of_sides;
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
