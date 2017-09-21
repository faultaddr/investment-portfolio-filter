package com.example.demo.entity;

/**
 * Created by panyunyi on 2017/9/19.
 * CUFE cs14
 */
public class UserEntity {
    private int userName;
    private String passWord;
    private String role;
    private Integer sex;
    private Integer age;
    private Integer marry;
    private Integer family;
    private Integer education;
    private Integer residence;
    private Integer creditcard;
    private Integer worklife;
    private Double loan;
    private Integer huankuan;
    private Double income;
    private Integer yongtu;
    private Double house;
    private Double car;
    private Double diyawu;
    private int id;

    public int getUserName() {
        return userName;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMarry() {
        return marry;
    }

    public void setMarry(Integer marry) {
        this.marry = marry;
    }

    public Integer getFamily() {
        return family;
    }

    public void setFamily(Integer family) {
        this.family = family;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getResidence() {
        return residence;
    }

    public void setResidence(Integer residence) {
        this.residence = residence;
    }

    public Integer getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(Integer creditcard) {
        this.creditcard = creditcard;
    }

    public Integer getWorklife() {
        return worklife;
    }

    public void setWorklife(Integer worklife) {
        this.worklife = worklife;
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Integer getHuankuan() {
        return huankuan;
    }

    public void setHuankuan(Integer huankuan) {
        this.huankuan = huankuan;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getYongtu() {
        return yongtu;
    }

    public void setYongtu(Integer yongtu) {
        this.yongtu = yongtu;
    }

    public Double getHouse() {
        return house;
    }

    public void setHouse(Double house) {
        this.house = house;
    }

    public Double getCar() {
        return car;
    }

    public void setCar(Double car) {
        this.car = car;
    }

    public Double getDiyawu() {
        return diyawu;
    }

    public void setDiyawu(Double diyawu) {
        this.diyawu = diyawu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userName != that.userName) return false;
        if (id != that.id) return false;
        if (passWord != null ? !passWord.equals(that.passWord) : that.passWord != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (marry != null ? !marry.equals(that.marry) : that.marry != null) return false;
        if (family != null ? !family.equals(that.family) : that.family != null) return false;
        if (education != null ? !education.equals(that.education) : that.education != null) return false;
        if (residence != null ? !residence.equals(that.residence) : that.residence != null) return false;
        if (creditcard != null ? !creditcard.equals(that.creditcard) : that.creditcard != null) return false;
        if (worklife != null ? !worklife.equals(that.worklife) : that.worklife != null) return false;
        if (loan != null ? !loan.equals(that.loan) : that.loan != null) return false;
        if (huankuan != null ? !huankuan.equals(that.huankuan) : that.huankuan != null) return false;
        if (income != null ? !income.equals(that.income) : that.income != null) return false;
        if (yongtu != null ? !yongtu.equals(that.yongtu) : that.yongtu != null) return false;
        if (house != null ? !house.equals(that.house) : that.house != null) return false;
        if (car != null ? !car.equals(that.car) : that.car != null) return false;
        if (diyawu != null ? !diyawu.equals(that.diyawu) : that.diyawu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName;
        result = 31 * result + (passWord != null ? passWord.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (marry != null ? marry.hashCode() : 0);
        result = 31 * result + (family != null ? family.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (residence != null ? residence.hashCode() : 0);
        result = 31 * result + (creditcard != null ? creditcard.hashCode() : 0);
        result = 31 * result + (worklife != null ? worklife.hashCode() : 0);
        result = 31 * result + (loan != null ? loan.hashCode() : 0);
        result = 31 * result + (huankuan != null ? huankuan.hashCode() : 0);
        result = 31 * result + (income != null ? income.hashCode() : 0);
        result = 31 * result + (yongtu != null ? yongtu.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (diyawu != null ? diyawu.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
