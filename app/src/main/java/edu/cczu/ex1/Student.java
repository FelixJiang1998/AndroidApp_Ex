package edu.cczu.ex1;

import java.io.Serializable;

public class Student implements Serializable {
    protected String[] HobbyName = {"旅游", "运动", "其他"};
    private String Name;
    private int Height;
    private int Weight;
    private String Sex;
    private String Hobby;//用二进制数串表示，顺序为旅游，运动，其他
    private String Major;
    private double BMI;

    public Student(String name, int height, int weight, String sex, String hobby, String major) {
        Name = name;
        Height = height;
        Weight = weight;
        Sex = sex;
        Hobby = hobby;
        Major = major;
        BMI=weight/height/height;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getHobby() {
        return Hobby;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String hobby_toString() {
        char[] hobby = Hobby.toCharArray();
        String res = null;
        for (int i=0;i<hobby.length;i++) {
            if (hobby[i]=='1'){
                if (res != null)
                    res = res + "," + HobbyName[i];
                else res = HobbyName[i];
            }
        }
        return res;
    }

//    public String major_int2String() {
//        switch (Major) {
//            case 1:
//                return "软件工程";
//            case 2:
//                return "物联网";
//            default:
//                return "计算机";//默认为计算机：0
//        }
//    }
//    public String Sex_int2String() {
//        if (Sex == 1) return "男";
//        return "女";
//    }

}
