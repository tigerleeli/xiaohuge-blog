package com.llh.springbootpage.param;

/**
 * @author admin
 */
public class UserInfoPageParam extends BasePageParam {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "UserInfoPageParam{" +
                "keyword='" + keyword + '\'' +
                "} " + super.toString();
    }
}
