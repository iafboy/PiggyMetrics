package com.cncustompoc.license.domain;

public class CheckResult {

    public CheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public boolean getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    private boolean checkResult;
}
