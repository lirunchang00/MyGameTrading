package com.example.administrator.gametrading.Bean;

public class Girls {
    private String Name;        //名字
    private int No;             //编号
    private int Start;          //星级
    private String Type;        //种类
    private String Portrait;    //立绘
    private String Head;
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
    private String Aura;        //光环
    private String Introduction;//简介
    private String Skin;        //皮肤
    private String Painter;     //画师
    private String Dubbing;     //配音
    private String Skill;       //技能

    /**
    *AR HG 构造方法
    * */
    public Girls(){}
    public Girls(String Name, int No, int Start, String Type, String Portrait,
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
    }

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

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
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

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }
}