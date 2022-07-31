package lesson10.lesson1002;

// 父类
public class People {
    public String country;
    protected String province;
    private String street;

    public void setCountry(String country) {
        this.country = country;
    }

    protected void setProvince(String province) {
        this.province = province;
    }

    private void setStreet(String street) {
        this.street = street;
    }
}
