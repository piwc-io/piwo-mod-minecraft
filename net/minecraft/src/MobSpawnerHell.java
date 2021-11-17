package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

public class MobSpawnerHell extends MobSpawnerBase
{

    public MobSpawnerHell()
    {
        field_25066_r.clear();
        field_25065_s.clear();
        field_25064_t.clear();
        field_25066_r.add(new SpawnListEntry(EntityGhast.class, 10));
        field_25066_r.add(new SpawnListEntry(EntityPigZombie.class, 10));
    }
}
