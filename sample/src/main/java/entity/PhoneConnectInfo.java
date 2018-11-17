package entity;

/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
public class PhoneConnectInfo {
    private String name;        //联系人姓名
    private String telPhone;    //电话号码


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public PhoneConnectInfo() {
    }

    public PhoneConnectInfo(String name, String telPhone) {
        this.name = name;
        this.telPhone = telPhone;
    }

}
