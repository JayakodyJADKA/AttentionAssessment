package com.anuththara18.attentionassessment.db;

public class Api {

    private static final String ROOT_URL = "http://192.168.43.104/AttentionAssessment/SustainedAttention/v1/Api.php?apicall=";
    private static final String ROOT_URL2 = "http://192.168.43.104/AttentionAssessment/FocusedAttention/v1/Api.php?apicall=";
    private static final String ROOT_URL3 = "http://192.168.43.104/AttentionAssessment/DividedAttention/v1/Api.php?apicall=";
    private static final String ROOT_URL4 = "http://192.168.43.104/AttentionAssessment/SelectiveAttention/v1/Api.php?apicall=";

    public static final String URL_SUSTAINED_ATTENTION = ROOT_URL + "insertdata";
    public static final String URL_FOCUSED_ATTENTION = ROOT_URL2 + "insertdata";
    public static final String URL_DIVIDED_ATTENTION = ROOT_URL3 + "insertdata";
    public static final String URL_SELECTIVE_ATTENTION = ROOT_URL4 + "insertdata";

}
