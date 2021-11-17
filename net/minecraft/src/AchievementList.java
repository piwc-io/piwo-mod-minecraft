package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.ArrayList;
import java.util.List;

public class AchievementList
{

    public AchievementList()
    {
    }

    public static List field_25196_a = new ArrayList();
    public static Achievement field_25195_b;
    public static Achievement field_25198_c;
    public static Achievement field_25197_d;

    static 
    {
        field_25195_b = new Achievement(0x500000, StatCollector.func_25200_a("achievement.openInventory"), 0, 0, null);
        field_25198_c = new Achievement(0x500001, StatCollector.func_25200_a("achievement.mineWood"), 4, 1, field_25195_b);
        field_25197_d = new Achievement(0x500001, StatCollector.func_25200_a("achievement.buildWorkBench"), 8, -1, field_25198_c);
    }
}
