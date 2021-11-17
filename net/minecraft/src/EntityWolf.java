package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.*;

public class EntityWolf extends EntityAnimals
{

    public EntityWolf(World world)
    {
        super(world);
        field_25049_a = false;
        texture = "/mob/wolf.png";
        setSize(0.8F, 0.8F);
        moveSpeed = 1.1F;
        health = 8;
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, Byte.valueOf((byte)0));
        dataWatcher.addObject(17, "");
        dataWatcher.addObject(18, new Integer(health));
    }

    protected boolean func_25021_m()
    {
        return false;
    }

    public String getEntityTexture()
    {
        if(func_25047_D())
        {
            return "/mob/wolf_tame.png";
        }
        if(func_25040_C())
        {
            return "/mob/wolf_angry.png";
        } else
        {
            return super.getEntityTexture();
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Angry", func_25040_C());
        nbttagcompound.setBoolean("Sitting", func_25034_B());
        if(func_25045_A() == null)
        {
            nbttagcompound.setString("Owner", "");
        } else
        {
            nbttagcompound.setString("Owner", func_25045_A());
        }
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        func_25041_c(nbttagcompound.getBoolean("Angry"));
        func_25046_b(nbttagcompound.getBoolean("Sitting"));
        String s = nbttagcompound.getString("Owner");
        if(s.length() > 0)
        {
            func_25036_a(s);
            func_25038_d(true);
        }
    }

    protected boolean func_25023_u()
    {
        return !func_25047_D();
    }

    protected String getLivingSound()
    {
        if(func_25040_C())
        {
            return "mob.wolf.growl";
        }
        if(rand.nextInt(3) == 0)
        {
            if(func_25047_D() && health < 10)
            {
                return "mob.wolf.whine";
            } else
            {
                return "mob.wolf.panting";
            }
        } else
        {
            return "mob.wolf.bark";
        }
    }

    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected int getDropItemId()
    {
        return -1;
    }

    protected void updatePlayerActionState()
    {
        super.updatePlayerActionState();
        if(!hasAttacked && !func_25031_E() && func_25047_D())
        {
            EntityPlayer entityplayer = worldObj.func_25099_a(func_25045_A());
            if(entityplayer != null)
            {
                float f = entityplayer.getDistanceToEntity(this);
                if(f > 5F)
                {
                    func_25044_b(entityplayer, f);
                }
            } else
            if(!handleWaterMovement())
            {
                func_25046_b(true);
            }
        } else
        if(playerToAttack == null && !func_25031_E() && !func_25047_D() && worldObj.rand.nextInt(100) == 0)
        {
            List list = worldObj.getEntitiesWithinAABB(EntitySheep.class, AxisAlignedBB.getBoundingBoxFromPool(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(16D, 4D, 16D));
            if(!list.isEmpty())
            {
                func_25032_c((Entity)list.get(worldObj.rand.nextInt(list.size())));
            }
        }
        if(handleWaterMovement())
        {
            func_25046_b(false);
        }
        if(!worldObj.multiplayerWorld)
        {
            dataWatcher.updateObject(18, Integer.valueOf(health));
        }
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        field_25049_a = false;
        if(func_25025_V() && !func_25031_E() && !func_25040_C())
        {
            Entity entity = func_25024_W();
            if(entity instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entity;
                ItemStack itemstack = entityplayer.inventory.getCurrentItem();
                if(itemstack != null)
                {
                    if(!func_25047_D() && itemstack.itemID == Item.bone.shiftedIndex)
                    {
                        field_25049_a = true;
                    } else
                    if(func_25047_D() && (Item.itemsList[itemstack.itemID] instanceof ItemFood))
                    {
                        field_25049_a = ((ItemFood)Item.itemsList[itemstack.itemID]).func_25012_m();
                    }
                }
            }
        }
        if(!field_9343_G && field_25053_f && !field_25052_g && !func_25031_E())
        {
            field_25052_g = true;
            field_25051_h = 0.0F;
            field_25050_i = 0.0F;
            worldObj.func_9425_a(this, (byte)8);
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        field_25054_c = field_25048_b;
        if(field_25049_a)
        {
            field_25048_b = field_25048_b + (1.0F - field_25048_b) * 0.4F;
        } else
        {
            field_25048_b = field_25048_b + (0.0F - field_25048_b) * 0.4F;
        }
        if(field_25049_a)
        {
            field_4127_c = 10;
        }
        if(handleWaterMovement())
        {
            field_25053_f = true;
            field_25052_g = false;
            field_25051_h = 0.0F;
            field_25050_i = 0.0F;
        } else
        if((field_25053_f || field_25052_g) && field_25052_g)
        {
            if(field_25051_h == 0.0F)
            {
                worldObj.playSoundAtEntity(this, "mob.wolf.shake", getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            }
            field_25050_i = field_25051_h;
            field_25051_h += 0.05F;
            if(field_25050_i >= 2.0F)
            {
                field_25053_f = false;
                field_25052_g = false;
                field_25050_i = 0.0F;
                field_25051_h = 0.0F;
            }
            if(field_25051_h > 0.4F)
            {
                float f = (float)boundingBox.minY;
                int i = (int)(MathHelper.sin((field_25051_h - 0.4F) * 3.141593F) * 7F);
                for(int j = 0; j < i; j++)
                {
                    float f1 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
                    float f2 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
                    worldObj.spawnParticle("splash", posX + (double)f1, f + 0.8F, posZ + (double)f2, motionX, motionY, motionZ);
                }

            }
        }
    }

    public boolean func_25039_v()
    {
        return field_25053_f;
    }

    public float func_25043_b_(float f)
    {
        return 0.75F + ((field_25050_i + (field_25051_h - field_25050_i) * f) / 2.0F) * 0.25F;
    }

    public float func_25042_a(float f, float f1)
    {
        float f2 = (field_25050_i + (field_25051_h - field_25050_i) * f + f1) / 1.8F;
        if(f2 < 0.0F)
        {
            f2 = 0.0F;
        } else
        if(f2 > 1.0F)
        {
            f2 = 1.0F;
        }
        return MathHelper.sin(f2 * 3.141593F) * MathHelper.sin(f2 * 3.141593F * 11F) * 0.15F * 3.141593F;
    }

    public float func_25033_c(float f)
    {
        return (field_25054_c + (field_25048_b - field_25054_c) * f) * 0.15F * 3.141593F;
    }

    public float getEyeHeight()
    {
        return height * 0.8F;
    }

    protected int func_25026_x()
    {
        if(func_25034_B())
        {
            return 20;
        } else
        {
            return super.func_25026_x();
        }
    }

    private void func_25044_b(Entity entity, float f)
    {
        PathEntity pathentity = worldObj.getPathToEntity(this, entity, 16F);
        if(pathentity == null && f > 12F)
        {
            int i = MathHelper.floor_double(entity.posX) - 2;
            int j = MathHelper.floor_double(entity.posZ) - 2;
            int k = MathHelper.floor_double(entity.boundingBox.minY);
            for(int l = 0; l <= 4; l++)
            {
                for(int i1 = 0; i1 <= 4; i1++)
                {
                    if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && worldObj.isBlockOpaqueCube(i + l, k - 1, j + i1) && !worldObj.isBlockOpaqueCube(i + l, k, j + i1) && !worldObj.isBlockOpaqueCube(i + l, k + 1, j + i1))
                    {
                        setLocationAndAngles((float)(i + l) + 0.5F, k, (float)(j + i1) + 0.5F, rotationYaw, rotationPitch);
                        return;
                    }
                }

            }

        } else
        {
            func_25029_a(pathentity);
        }
    }

    protected boolean func_25028_d_()
    {
        return func_25034_B() || field_25052_g;
    }

    public boolean attackEntityFrom(Entity entity, int i)
    {
        func_25046_b(false);
        if(entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
        {
            i = (i + 1) / 2;
        }
        if(super.attackEntityFrom(entity, i))
        {
            if(!func_25047_D() && !func_25040_C())
            {
                if(entity instanceof EntityPlayer)
                {
                    func_25041_c(true);
                    playerToAttack = entity;
                }
                if((entity instanceof EntityArrow) && ((EntityArrow)entity).field_682_g != null)
                {
                    entity = ((EntityArrow)entity).field_682_g;
                }
                if(entity instanceof EntityLiving)
                {
                    List list = worldObj.getEntitiesWithinAABB(EntityWolf.class, AxisAlignedBB.getBoundingBoxFromPool(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(16D, 4D, 16D));
                    Iterator iterator = list.iterator();
                    do
                    {
                        if(!iterator.hasNext())
                        {
                            break;
                        }
                        Entity entity1 = (Entity)iterator.next();
                        EntityWolf entitywolf = (EntityWolf)entity1;
                        if(!entitywolf.func_25047_D() && entitywolf.playerToAttack == null)
                        {
                            entitywolf.playerToAttack = entity;
                            if(entity instanceof EntityPlayer)
                            {
                                entitywolf.func_25041_c(true);
                            }
                        }
                    } while(true);
                }
            } else
            if(entity != this && entity != null)
            {
                if(func_25047_D() && (entity instanceof EntityPlayer) && ((EntityPlayer)entity).username.equals(func_25045_A()))
                {
                    return true;
                }
                playerToAttack = entity;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected Entity findPlayerToAttack()
    {
        if(func_25040_C())
        {
            return worldObj.getClosestPlayerToEntity(this, 16D);
        } else
        {
            return null;
        }
    }

    protected void attackEntity(Entity entity, float f)
    {
        if(f > 2.0F && f < 6F && rand.nextInt(10) == 0)
        {
            if(onGround)
            {
                double d = entity.posX - posX;
                double d1 = entity.posZ - posZ;
                float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
                motionX = (d / (double)f1) * 0.5D * 0.80000001192092896D + motionX * 0.20000000298023224D;
                motionZ = (d1 / (double)f1) * 0.5D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
                motionY = 0.40000000596046448D;
            }
        } else
        if((double)f < 1.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            byte byte0 = 2;
            if(func_25047_D())
            {
                byte0 = 4;
            }
            entity.attackEntityFrom(this, byte0);
        }
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if(!func_25047_D())
        {
            if(itemstack != null && itemstack.itemID == Item.bone.shiftedIndex && !func_25040_C())
            {
                itemstack.stackSize--;
                if(itemstack.stackSize <= 0)
                {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                }
                if(!worldObj.multiplayerWorld)
                {
                    if(rand.nextInt(3) == 0)
                    {
                        func_25038_d(true);
                        func_25029_a(null);
                        func_25046_b(true);
                        health = 20;
                        func_25036_a(entityplayer.username);
                        func_25035_a(true);
                        worldObj.func_9425_a(this, (byte)7);
                    } else
                    {
                        func_25035_a(false);
                        worldObj.func_9425_a(this, (byte)6);
                    }
                }
                return true;
            }
        } else
        {
            if(itemstack != null && (Item.itemsList[itemstack.itemID] instanceof ItemFood))
            {
                ItemFood itemfood = (ItemFood)Item.itemsList[itemstack.itemID];
                if(itemfood.func_25012_m() && dataWatcher.func_25115_b(18) < 20)
                {
                    itemstack.stackSize--;
                    if(itemstack.stackSize <= 0)
                    {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                    heal(((ItemFood)Item.porkRaw).func_25011_l());
                    return true;
                }
            }
            if(entityplayer.username.equals(func_25045_A()))
            {
                if(!worldObj.multiplayerWorld)
                {
                    func_25046_b(!func_25034_B());
                    isJumping = false;
                    func_25029_a(null);
                }
                return true;
            }
        }
        return false;
    }

    void func_25035_a(boolean flag)
    {
        String s = "heart";
        if(!flag)
        {
            s = "smoke";
        }
        for(int i = 0; i < 7; i++)
        {
            double d = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            worldObj.spawnParticle(s, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
        }

    }

    public void handleHealthUpdate(byte byte0)
    {
        if(byte0 == 7)
        {
            func_25035_a(true);
        } else
        if(byte0 == 6)
        {
            func_25035_a(false);
        } else
        if(byte0 == 8)
        {
            field_25052_g = true;
            field_25051_h = 0.0F;
            field_25050_i = 0.0F;
        } else
        {
            super.handleHealthUpdate(byte0);
        }
    }

    public float func_25037_z()
    {
        if(func_25040_C())
        {
            return 1.53938F;
        }
        if(func_25047_D())
        {
            return (0.55F - (float)(20 - dataWatcher.func_25115_b(18)) * 0.02F) * 3.141593F;
        } else
        {
            return 0.6283185F;
        }
    }

    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    public String func_25045_A()
    {
        return dataWatcher.func_25116_c(17);
    }

    public void func_25036_a(String s)
    {
        dataWatcher.updateObject(17, s);
    }

    public boolean func_25034_B()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void func_25046_b(boolean flag)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);
        if(flag)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 | 1)));
        } else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & -2)));
        }
    }

    public boolean func_25040_C()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    public void func_25041_c(boolean flag)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);
        if(flag)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 | 2)));
        } else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & -3)));
        }
    }

    public boolean func_25047_D()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 4) != 0;
    }

    public void func_25038_d(boolean flag)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);
        if(flag)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 | 4)));
        } else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & -5)));
        }
    }

    private boolean field_25049_a;
    private float field_25048_b;
    private float field_25054_c;
    private boolean field_25053_f;
    private boolean field_25052_g;
    private float field_25051_h;
    private float field_25050_i;
}
