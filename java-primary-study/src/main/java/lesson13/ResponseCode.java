package lesson13;

// 响应码
public enum ResponseCode {
    SUCCESS(200, "成功"),
    ERROR(300, "失败");

    int code;
    String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
