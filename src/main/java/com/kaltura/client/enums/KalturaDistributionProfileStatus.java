package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaDistributionProfileStatus {
    DISABLED (1),
    ENABLED (2),
    DELETED (3);

    int hashCode;

    KalturaDistributionProfileStatus(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public static KalturaDistributionProfileStatus get(int hashCode) {
        switch(hashCode) {
            case 1: return DISABLED;
            case 2: return ENABLED;
            case 3: return DELETED;
            default: return DISABLED;
        }
    }
}
