package com.jcnetwork.iotums.common;

public class CommonEnum {
	public enum RespCode {
        SUCCESS(0, "成功"),
        Failure(1,"失败");
        /**
         * The code.
         */
        private Integer code;

        /**
         * The name.
         */
        private String name;

        RespCode(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
