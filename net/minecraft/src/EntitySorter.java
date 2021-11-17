package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Comparator;

public class EntitySorter
    implements Comparator
{

    public EntitySorter(Entity entity)
    {
        entityForSorting = entity;
    }

    public int sortByDistanceToEntity(WorldRenderer worldrenderer, WorldRenderer worldrenderer1)
    {
        return worldrenderer.distanceToEntity(entityForSorting) >= worldrenderer1.distanceToEntity(entityForSorting) ? 1 : -1;
    }

    public int compare(Object obj, Object obj1)
    {
        return sortByDistanceToEntity((WorldRenderer)obj, (WorldRenderer)obj1);
    }

    private Entity entityForSorting;
}
