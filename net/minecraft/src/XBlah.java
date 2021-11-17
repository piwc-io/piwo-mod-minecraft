package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.HashMap;
import java.util.Map;

public class XBlah
{

    public XBlah()
    {
        field_25102_a = new HashMap();
        field_25101_b = new HashMap();
    }

    public void func_25100_a(StatBasic statbasic, int i)
    {
        Integer integer = (Integer)field_25101_b.get(statbasic);
        if(integer != null)
        {
            i += integer.intValue();
        }
        field_25101_b.put(statbasic, Integer.valueOf(i));
    }

    private Map field_25102_a;
    private Map field_25101_b;
}
