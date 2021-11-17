package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class SlotCrafting extends Slot
{

    public SlotCrafting(EntityPlayer entityplayer, IInventory iinventory, IInventory iinventory1, int i, int j, int k)
    {
        super(iinventory1, i, j, k);
        field_25015_e = entityplayer;
        craftMatrix = iinventory;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }

    public void onPickupFromSlot(ItemStack itemstack)
    {
        field_25015_e.func_25058_a(StatList.field_25158_z[itemstack.itemID], 1);
        if(itemstack.itemID == Block.workbench.blockID)
        {
            field_25015_e.func_25058_a(AchievementList.field_25197_d, 1);
        }
        for(int i = 0; i < craftMatrix.getSizeInventory(); i++)
        {
            ItemStack itemstack1 = craftMatrix.getStackInSlot(i);
            if(itemstack1 == null)
            {
                continue;
            }
            craftMatrix.decrStackSize(i, 1);
            if(itemstack1.getItem().func_21014_i())
            {
                craftMatrix.setInventorySlotContents(i, new ItemStack(itemstack1.getItem().getContainerItem()));
            }
        }

    }

    public boolean func_25014_f()
    {
        return true;
    }

    private final IInventory craftMatrix;
    private EntityPlayer field_25015_e;
}
