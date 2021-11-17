package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class BlockPiwo extends Block
{

    protected BlockPiwo(int i, int j)
    {
        super(i, j, Material.ground);
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 0)
        {
            return blockIndexInTexture + 2;
        }
        if(i == 1)
        {
            return blockIndexInTexture + 1;
        } else
        {
            return blockIndexInTexture;
        }
    }
}
