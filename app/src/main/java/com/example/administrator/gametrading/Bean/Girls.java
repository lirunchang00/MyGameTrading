package com.example.administrator.gametrading.Bean;

import java.io.Serializable;

public class Girls implements Serializable{
    private String Name;        //名字
    private String No;             //编号
    private int Start;          //星级
    private String Type;        //种类
    private String TypePic;     //种类图片
    private String Portrait;    //立绘
    private String BreakPortrait;//大破
    private String Head;        //头像
    private int Damage;         //伤害
    private int Hit;            //命中
    private int Avoid;          //回避
    private int Shooting;       //射速
    private int Hp;             //生命值
    private int CirtRate;       //暴击率
    private int CirtDamage;     //暴击伤害
    private int Piercing;       //穿甲
    private int Armor;          //护甲
    private int Chain;          //弹链
    private String Method;      //获取方式
    private String Aura;        //光环
    private String AuraIntroduction;//光环介绍
    private String Introduction;//简介
    private String Skin;        //皮肤
    private String Painter;     //画师
    private String Dubbing;     //配音
    private String SkillIntroduction; //技能简介
    private String SkillName;       //技能
    private String SkillCD;     //技能CD
    private String SkillPic;    //技能图片

    /**
    *AR HG 构造方法
    * */
    public Girls(){}
    /*public Girls(String Name, int No, int Start, String Type, String Portrait,
                 int Damage, int Hit, int Avoid, int Shooting, int Hp,
                 int CirtRate, int CirtDamage,int Piercing,int Armor,int Chain, String Aura,
                 String Introduction, String Skin, String Painter, String Dubbing, String Skill)
    {
        this.Name =Name;
        this.No = No;
        this.Start = Start;
        this.Type = Type;
        this.Portrait = Portrait;
        this.Damage = Damage;
        this.Hit = Hit;
        this.Avoid = Avoid;
        this.Shooting = Shooting;
        this.Hp = Hp;
        this.CirtRate =CirtRate;
        this.CirtDamage = CirtDamage;
        this.Piercing = Piercing;
        this.Armor =Armor;
        this.Chain = Chain;
        this.Aura = Aura;
        this.Introduction = Introduction;
        this.Skin = Skin;
        this.Painter = Painter;
        this.Dubbing = Dubbing;
        this.Skill = Skill;
    }*/

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public int getStart() {
        return Start;
    }

    public void setStart(int start) {
        Start = start;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getPortrait() {
        return Portrait;
    }

    public void setPortrait(String portrait) {
        Portrait = portrait;
    }

    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }

    public int getHit() {
        return Hit;
    }

    public void setHit(int hit) {
        Hit = hit;
    }

    public int getAvoid() {
        return Avoid;
    }

    public void setAvoid(int avoid) {
        Avoid = avoid;
    }

    public int getShooting() {
        return Shooting;
    }

    public void setShooting(int shooting) {
        Shooting = shooting;
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
    }

    public int getCirtRate() {
        return CirtRate;
    }

    public void setCirtRate(int cirtRate) {
        CirtRate = cirtRate;
    }

    public int getCirtDamage() {
        return CirtDamage;
    }

    public void setCirtDamage(int cirtDamage) {
        CirtDamage = cirtDamage;
    }

    public int getPiercing() {
        return Piercing;
    }

    public void setPiercing(int piercing) {
        Piercing = piercing;
    }

    public int getArmor() {
        return Armor;
    }

    public void setArmor(int armor) {
        Armor = armor;
    }

    public int getChain() {
        return Chain;
    }

    public void setChain(int chain) {
        Chain = chain;
    }

    public String getAura() {
        return Aura;
    }

    public void setAura(String aura) {
        Aura = aura;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public String getSkin() {
        return Skin;
    }

    public void setSkin(String skin) {
        Skin = skin;
    }

    public String getPainter() {
        return Painter;
    }

    public void setPainter(String painter) {
        Painter = painter;
    }

    public String getDubbing() {
        return Dubbing;
    }

    public void setDubbing(String dubbing) {
        Dubbing = dubbing;
    }

    public String getTypePic() {
        return TypePic;
    }

    public void setTypePic(String typePic) {
        TypePic = typePic;
    }

    @Override
    public String toString() {
        return "Girls{" +
                "Name='" + Name + '\'' +
                ", No='" + No + '\'' +
                ", Start=" + Start +
                ", Type='" + Type + '\'' +
                ", TypePic='" + TypePic + '\'' +
                ", Portrait='" + Portrait + '\'' +
                ", BreakPortrait='" + BreakPortrait + '\'' +
                ", Head='" + Head + '\'' +
                ", Damage=" + Damage +
                ", Hit=" + Hit +
                ", Avoid=" + Avoid +
                ", Shooting=" + Shooting +
                ", Hp=" + Hp +
                ", CirtRate=" + CirtRate +
                ", CirtDamage=" + CirtDamage +
                ", Piercing=" + Piercing +
                ", Armor=" + Armor +
                ", Chain=" + Chain +
                ", Method='" + Method + '\'' +
                ", Aura='" + Aura + '\'' +
                ", AuraIntroduction='" + AuraIntroduction + '\'' +
                ", Introduction='" + Introduction + '\'' +
                ", Skin='" + Skin + '\'' +
                ", Painter='" + Painter + '\'' +
                ", Dubbing='" + Dubbing + '\'' +
                ", SkillIntroduction='" + SkillIntroduction + '\'' +
                ", SkillName='" + SkillName + '\'' +
                ", SkillCD='" + SkillCD + '\'' +
                ", SkillPic='" + SkillPic + '\'' +
                '}';
    }

    public String getBreakPortrait() {
        return BreakPortrait;
    }

    public void setBreakPortrait(String breakPortrait) {
        BreakPortrait = breakPortrait;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getAuraIntroduction() {
        return AuraIntroduction;
    }

    public void setAuraIntroduction(String auraIntroduction) {
        AuraIntroduction = auraIntroduction;
    }

    public String getSkillIntroduction() {
        return SkillIntroduction;
    }

    public void setSkillIntroduction(String skillIntroduction) {
        SkillIntroduction = skillIntroduction;
    }

    public String getSkillName() {
        return SkillName;
    }

    public void setSkillName(String skillName) {
        SkillName = skillName;
    }

    public String getSkillCD() {
        return SkillCD;
    }

    public void setSkillCD(String skillCD) {
        SkillCD = skillCD;
    }

    public String getSkillPic() {
        return SkillPic;
    }

    public void setSkillPic(String skillPic) {
        SkillPic = skillPic;
    }


}