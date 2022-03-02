package com.anuththara18.attentionassessment.alternating;

public class Alternating {

    private int id;
    private int childID;
    private int totalCorrectResponses;
    private int noOfCorrectResponses;
    private int noOfCommissionErrors;
    private int noOfOmmissionErrors;
    private int meanReactionTime;
    private int totalDuration;

    public Alternating(int id, int childID, int totalCorrectResponses, int noOfCorrectResponses, int noOfCommissionErrors, int noOfOmmissionErrors, int meanReactionTime, int totalDuration) {
        this.id = id;
        this.childID = childID;
        this.totalCorrectResponses = totalCorrectResponses;
        this.noOfCorrectResponses = noOfCorrectResponses;
        this.noOfCommissionErrors = noOfCommissionErrors;
        this.noOfOmmissionErrors = noOfOmmissionErrors;
        this.meanReactionTime = meanReactionTime;
        this.totalDuration = totalDuration;
    }

    public int getId() {
        return id;
    }

    public int getChildID() {
        return childID;
    }

    public int getTotalCorrectResponses() {
        return totalCorrectResponses;
    }

    public int getNoOfCorrectResponses() {
        return noOfCorrectResponses;
    }

    public int getNoOfCommissionErrors() {
        return noOfCommissionErrors;
    }

    public int getNoOfOmmissionErrors() {
        return noOfOmmissionErrors;
    }

    public int getMeanReactionTime() {
        return meanReactionTime;
    }

    public int getTotalDuration() {
        return totalDuration;
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
