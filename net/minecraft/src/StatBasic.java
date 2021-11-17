package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class StatBasic
{

    public StatBasic(int i, String s)
    {
        field_25071_d = i;
        field_25070_e = s;
    }

    public StatBasic func_25068_c()
    {
        StatList.func_25152_a(this);
        return this;
    }

    public boolean func_25067_a()
    {
        return false;
    }

    public final int field_25071_d;
    public final String field_25070_e;
    public String field_25069_f;
}
