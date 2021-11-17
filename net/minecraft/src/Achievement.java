package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class Achievement extends StatBasic
{

    public Achievement(int i, String s, int j, int k, Achievement achievement)
    {
        super(i, s);
        field_25075_a = j + 46;
        field_25074_b = k + 23;
        field_25076_c = achievement;
    }

    public boolean func_25067_a()
    {
        return true;
    }

    public final int field_25075_a;
    public final int field_25074_b;
    public final Achievement field_25076_c;
}
