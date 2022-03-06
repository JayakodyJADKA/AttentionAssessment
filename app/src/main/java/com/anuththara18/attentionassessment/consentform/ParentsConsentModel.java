package com.anuththara18.attentionassessment.consentform;

import java.io.Serializable;

public class ParentsConsentModel implements Serializable {

    private byte[] signature;

    public ParentsConsentModel(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

}
