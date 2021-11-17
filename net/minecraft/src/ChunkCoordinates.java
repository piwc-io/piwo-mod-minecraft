package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ChunkCoordinates
    implements Comparable
{

    public ChunkCoordinates()
    {
    }

    public ChunkCoordinates(int i, int j, int k)
    {
        x = i;
        y = j;
        z = k;
    }

    public ChunkCoordinates(ChunkCoordinates chunkcoordinates)
    {
        x = chunkcoordinates.x;
        y = chunkcoordinates.y;
        z = chunkcoordinates.z;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof ChunkCoordinates))
        {
            return false;
        } else
        {
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates)obj;
            return x == chunkcoordinates.x && y == chunkcoordinates.y && z == chunkcoordinates.z;
        }
    }

    public int hashCode()
    {
        return x + z << 8 + y << 16;
    }

    public int func_22393_a(ChunkCoordinates chunkcoordinates)
    {
        if(y == chunkcoordinates.y)
        {
            if(z == chunkcoordinates.z)
            {
                return x - chunkcoordinates.x;
            } else
            {
                return z - chunkcoordinates.z;
            }
        } else
        {
            return y - chunkcoordinates.y;
        }
    }

    public int compareTo(Object obj)
    {
        return func_22393_a((ChunkCoordinates)obj);
    }

    public int x;
    public int y;
    public int z;
}
