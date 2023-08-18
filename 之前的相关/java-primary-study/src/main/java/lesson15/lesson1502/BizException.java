package lesson15.lesson1502;

public class BizException extends RuntimeException {
    // 错误码
    private Integer code;

    // 错误信息
    private String msg;

    public BizException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // setter getter
}
