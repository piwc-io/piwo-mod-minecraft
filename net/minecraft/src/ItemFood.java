package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ItemFood extends Item
{

    public ItemFood(int i, int j, boolean flag)
    {
        super(i);
        healAmount = j;
        field_25013_bi = flag;
        maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        entityplayer.heal(healAmount);
        return itemstack;
    }

    public int func_25011_l()
    {
        return healAmount;
    }

    public boolean func_25012_m()
    {
        return field_25013_bi;
    }

    private int healAmount;
    private boolean field_25013_bi;
}
