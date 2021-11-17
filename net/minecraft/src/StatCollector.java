package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class StatCollector
{

    public StatCollector()
    {
    }

    public static String func_25200_a(String s)
    {
        return field_25201_a.translateKey(s);
    }

    public static String func_25199_a(String s, Object aobj[])
    {
        return field_25201_a.translateKeyFormat(s, aobj);
    }

    private static StringTranslate field_25201_a = StringTranslate.getInstance();

}
