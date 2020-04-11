package com.testframwork.async.dto;

public class ResultDataDto {

    private String status;


    private ResultDataDto() {
        this.status = "sucess";
    }


    public static synchronized ResultDataDto addSuccess() {
        ResultDataDto dto = new ResultDataDto();
        if (dto != null) {
            return dto;
        }
        return new ResultDataDto();
    }

    @Override
    public String toString() {
        return "ResultDataDto{" +
                "status='" + status + '\'' +
                '}';
    }
}
