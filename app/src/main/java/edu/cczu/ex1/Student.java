package edu.cczu.ex1;

public class Student {
    protected String[] HobbyName = {"旅游", "运动", "其他"};
    private String Name;
    private int Height;
    private int Weight;
    private int Sex;//男：1，女：0
    private String Hobby;//用二进制数串表示，顺序为旅游，运动，其他
    private int Major;//计算机：0，软件工程：1，物联网：2

    public Student(String name, int height, int weight, int sex, String hobby, int major) {
        Name = name;
        Height = height;
        Weight = weight;
        Sex = sex;
        Hobby = hobby;
        Major = major;
    }

    public String major_int2String() {
        switch (Major) {
            case 1:
                return "软件工程";
            case 2:
                return "物联网";
            default:
                return "计算机";//默认为计算机：0
        }
    }

    public String Sex_int2String() {
        if (Sex == 1) return "男";
        return "女";
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

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public String getHobby() {
        return Hobby;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }

    public int getMajor() {
        return Major;
    }

    public void setMajor(int major) {
        Major = major;
    }
}
