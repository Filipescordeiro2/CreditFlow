package com.CrediFlow.ClientCardCredit.enums;

public enum BlockingReason {
    FRAUD("The card was blocked due to suspected fraudulent activity"),
    LOST("The card was reported as lost"),
    STOLEN("The card was reported as stolen"),
    UNAUTHORIZED_USE("The card was blocked due to unauthorized use"),
    OTHER("The card was blocked for other reasons not specified");

    private final String description;

    BlockingReason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
