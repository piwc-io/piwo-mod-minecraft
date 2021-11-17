package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.*;

public abstract class EntityPlayer extends EntityLiving
{

    public EntityPlayer(World world)
    {
        super(world);
        inventory = new InventoryPlayer(this);
        field_9371_f = 0;
        score = 0;
        isSwinging = false;
        swingProgressInt = 0;
        damageRemainder = 0;
        fishEntity = null;
        inventorySlots = new CraftingInventoryPlayerCB(inventory, !world.multiplayerWorld);
        craftingInventory = inventorySlots;
        yOffset = 1.62F;
        ChunkCoordinates chunkcoordinates = world.getSpawnPoint();
        setLocationAndAngles((double)chunkcoordinates.x + 0.5D, chunkcoordinates.y + 1, (double)chunkcoordinates.z + 0.5D, 0.0F, 0.0F);
        health = 20;
        field_9351_C = "humanoid";
        field_9353_B = 180F;
        fireResistance = 20;
        texture = "/mob/char.png";
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    public void onUpdate()
    {
        if(isPlayerSleeping())
        {
            sleepTimer++;
            if(sleepTimer > 100)
            {
                sleepTimer = 100;
            }
            if(!isBedChunkCoordinatesABedBlock())
            {
                wakeUpPlayer(true, true, false);
            } else
            if(!worldObj.multiplayerWorld && worldObj.isDaytime())
            {
                wakeUpPlayer(false, true, true);
            }
        } else
        if(sleepTimer > 0)
        {
            sleepTimer++;
            if(sleepTimer >= 110)
            {
                sleepTimer = 0;
            }
        }
        super.onUpdate();
        if(!worldObj.multiplayerWorld && craftingInventory != null && !craftingInventory.isUsableByPlayer(this))
        {
            func_20059_m();
            craftingInventory = inventorySlots;
        }
        field_20066_r = field_20063_u;
        field_20065_s = field_20062_v;
        field_20064_t = field_20061_w;
        double d = posX - field_20063_u;
        double d1 = posY - field_20062_v;
        double d2 = posZ - field_20061_w;
        double d3 = 10D;
        if(d > d3)
        {
            field_20066_r = field_20063_u = posX;
        }
        if(d2 > d3)
        {
            field_20064_t = field_20061_w = posZ;
        }
        if(d1 > d3)
        {
            field_20065_s = field_20062_v = posY;
        }
        if(d < -d3)
        {
            field_20066_r = field_20063_u = posX;
        }
        if(d2 < -d3)
        {
            field_20064_t = field_20061_w = posZ;
        }
        if(d1 < -d3)
        {
            field_20065_s = field_20062_v = posY;
        }
        field_20063_u += d * 0.25D;
        field_20061_w += d2 * 0.25D;
        field_20062_v += d1 * 0.25D;
        func_25058_a(StatList.field_25179_j, 1);
    }

    protected boolean healthBelowOrAtZero()
    {
        return health <= 0 || isPlayerSleeping();
    }

    protected void func_20059_m()
    {
        craftingInventory = inventorySlots;
    }

    public void updateCloak()
    {
        field_20067_q = (new StringBuilder()).append("http://s3.amazonaws.com/MinecraftCloaks/").append(username).append(".png").toString();
        cloakUrl = field_20067_q;
    }

    public void updateRidden()
    {
        super.updateRidden();
        field_775_e = field_774_f;
        field_774_f = 0.0F;
    }

    public void preparePlayerToSpawn()
    {
        yOffset = 1.62F;
        setSize(0.6F, 1.8F);
        super.preparePlayerToSpawn();
        health = 20;
        deathTime = 0;
    }

    protected void updatePlayerActionState()
    {
        if(isSwinging)
        {
            swingProgressInt++;
            if(swingProgressInt == 8)
            {
                swingProgressInt = 0;
                isSwinging = false;
            }
        } else
        {
            swingProgressInt = 0;
        }
        swingProgress = (float)swingProgressInt / 8F;
    }

    public void onLivingUpdate()
    {
        if(worldObj.difficultySetting == 0 && health < 20 && (ticksExisted % 20) * 12 == 0)
        {
            heal(1);
        }
        inventory.decrementAnimations();
        field_775_e = field_774_f;
        super.onLivingUpdate();
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        float f1 = (float)Math.atan(-motionY * 0.20000000298023224D) * 15F;
        if(f > 0.1F)
        {
            f = 0.1F;
        }
        if(!onGround || health <= 0)
        {
            f = 0.0F;
        }
        if(onGround || health <= 0)
        {
            f1 = 0.0F;
        }
        field_774_f += (f - field_774_f) * 0.4F;
        field_9328_R += (f1 - field_9328_R) * 0.8F;
        if(health > 0)
        {
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
            if(list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    Entity entity = (Entity)list.get(i);
                    if(!entity.isDead)
                    {
                        func_451_h(entity);
                    }
                }

            }
        }
    }

    private void func_451_h(Entity entity)
    {
        entity.onCollideWithPlayer(this);
    }

    public int getScore()
    {
        return score;
    }

    public void onDeath(Entity entity)
    {
        super.onDeath(entity);
        setSize(0.2F, 0.2F);
        setPosition(posX, posY, posZ);
        motionY = 0.10000000149011612D;
        if(username.equals("Notch"))
        {
            dropPlayerItemWithRandomChoice(new ItemStack(Item.appleRed, 1), true);
        }
        inventory.dropAllItems();
        if(entity != null)
        {
            motionX = -MathHelper.cos(((attackedAtYaw + rotationYaw) * 3.141593F) / 180F) * 0.1F;
            motionZ = -MathHelper.sin(((attackedAtYaw + rotationYaw) * 3.141593F) / 180F) * 0.1F;
        } else
        {
            motionX = motionZ = 0.0D;
        }
        yOffset = 0.1F;
        func_25058_a(StatList.field_25163_u, 1);
    }

    public void addToPlayerScore(Entity entity, int i)
    {
        score += i;
        if(entity instanceof EntityPlayer)
        {
            func_25058_a(StatList.field_25161_w, 1);
        } else
        {
            func_25058_a(StatList.field_25162_v, 1);
        }
    }

    public void dropCurrentItem()
    {
        dropPlayerItemWithRandomChoice(inventory.decrStackSize(inventory.currentItem, 1), false);
    }

    public void dropPlayerItem(ItemStack itemstack)
    {
        dropPlayerItemWithRandomChoice(itemstack, false);
    }

    public void dropPlayerItemWithRandomChoice(ItemStack itemstack, boolean flag)
    {
        if(itemstack == null)
        {
            return;
        }
        EntityItem entityitem = new EntityItem(worldObj, posX, (posY - 0.30000001192092896D) + (double)getEyeHeight(), posZ, itemstack);
        entityitem.delayBeforeCanPickup = 40;
        float f = 0.1F;
        if(flag)
        {
            float f2 = rand.nextFloat() * 0.5F;
            float f4 = rand.nextFloat() * 3.141593F * 2.0F;
            entityitem.motionX = -MathHelper.sin(f4) * f2;
            entityitem.motionZ = MathHelper.cos(f4) * f2;
            entityitem.motionY = 0.20000000298023224D;
        } else
        {
            float f1 = 0.3F;
            entityitem.motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f1;
            entityitem.motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f1;
            entityitem.motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F) * f1 + 0.1F;
            f1 = 0.02F;
            float f3 = rand.nextFloat() * 3.141593F * 2.0F;
            f1 *= rand.nextFloat();
            entityitem.motionX += Math.cos(f3) * (double)f1;
            entityitem.motionY += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
            entityitem.motionZ += Math.sin(f3) * (double)f1;
        }
        joinEntityItemWithWorld(entityitem);
        func_25058_a(StatList.field_25168_r, 1);
    }

    protected void joinEntityItemWithWorld(EntityItem entityitem)
    {
        worldObj.entityJoinedWorld(entityitem);
    }

    public float getCurrentPlayerStrVsBlock(Block block)
    {
        float f = inventory.getStrVsBlock(block);
        if(isInsideOfMaterial(Material.water))
        {
            f /= 5F;
        }
        if(!onGround)
        {
            f /= 5F;
        }
        return f;
    }

    public boolean canHarvestBlock(Block block)
    {
        return inventory.canHarvestBlock(block);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Inventory");
        inventory.readFromNBT(nbttaglist);
        dimension = nbttagcompound.getInteger("Dimension");
        sleeping = nbttagcompound.getBoolean("Sleeping");
        sleepTimer = nbttagcompound.getShort("SleepTimer");
        if(sleeping)
        {
            bedChunkCoordinates = new ChunkCoordinates(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
            wakeUpPlayer(true, true, false);
        }
        if(nbttagcompound.hasKey("SpawnX") && nbttagcompound.hasKey("SpawnY") && nbttagcompound.hasKey("SpawnZ"))
        {
            field_24900_bi = new ChunkCoordinates(nbttagcompound.getInteger("SpawnX"), nbttagcompound.getInteger("SpawnY"), nbttagcompound.getInteger("SpawnZ"));
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setTag("Inventory", inventory.writeToNBT(new NBTTagList()));
        nbttagcompound.setInteger("Dimension", dimension);
        nbttagcompound.setBoolean("Sleeping", sleeping);
        nbttagcompound.setShort("SleepTimer", (short)sleepTimer);
        if(field_24900_bi != null)
        {
            nbttagcompound.setInteger("SpawnX", field_24900_bi.x);
            nbttagcompound.setInteger("SpawnY", field_24900_bi.y);
            nbttagcompound.setInteger("SpawnZ", field_24900_bi.z);
        }
    }

    public void displayGUIChest(IInventory iinventory)
    {
    }

    public void displayWorkbenchGUI(int i, int j, int k)
    {
    }

    public void onItemPickup(Entity entity, int i)
    {
    }

    public float getEyeHeight()
    {
        return 0.12F;
    }

    protected void func_22058_C()
    {
        yOffset = 1.62F;
    }

    public boolean attackEntityFrom(Entity entity, int i)
    {
        field_9344_ag = 0;
        if(health <= 0)
        {
            return false;
        }
        if(isPlayerSleeping())
        {
            wakeUpPlayer(true, true, false);
        }
        if((entity instanceof EntityMobs) || (entity instanceof EntityArrow))
        {
            if(worldObj.difficultySetting == 0)
            {
                i = 0;
            }
            if(worldObj.difficultySetting == 1)
            {
                i = i / 3 + 1;
            }
            if(worldObj.difficultySetting == 3)
            {
                i = (i * 3) / 2;
            }
        }
        if(i == 0)
        {
            return false;
        }
        Object obj = entity;
        if((obj instanceof EntityArrow) && ((EntityArrow)obj).field_682_g != null)
        {
            obj = ((EntityArrow)obj).field_682_g;
        }
        if(obj instanceof EntityLiving)
        {
            func_25055_a((EntityLiving)obj, false);
        }
        func_25058_a(StatList.field_25165_t, i);
        return super.attackEntityFrom(entity, i);
    }

    protected void func_25055_a(EntityLiving entityliving, boolean flag)
    {
        if((entityliving instanceof EntityCreeper) || (entityliving instanceof EntityGhast))
        {
            return;
        }
        if(entityliving instanceof EntityWolf)
        {
            EntityWolf entitywolf = (EntityWolf)entityliving;
            if(entitywolf.func_25047_D() && username.equals(entitywolf.func_25045_A()))
            {
                return;
            }
        }
        List list = worldObj.getEntitiesWithinAABB(EntityWolf.class, AxisAlignedBB.getBoundingBoxFromPool(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(16D, 4D, 16D));
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            Entity entity = (Entity)iterator.next();
            EntityWolf entitywolf1 = (EntityWolf)entity;
            if(entitywolf1.func_25047_D() && entitywolf1.func_25030_F() == null && username.equals(entitywolf1.func_25045_A()) && (!flag || !entitywolf1.func_25034_B()))
            {
                entitywolf1.func_25046_b(false);
                entitywolf1.func_25032_c(entityliving);
            }
        } while(true);
    }

    protected void damageEntity(int i)
    {
        int j = 25 - inventory.getTotalArmorValue();
        int k = i * j + damageRemainder;
        inventory.damageArmor(i);
        i = k / 25;
        damageRemainder = k % 25;
        super.damageEntity(i);
    }

    public void displayGUIFurnace(TileEntityFurnace tileentityfurnace)
    {
    }

    public void displayGUIDispenser(TileEntityDispenser tileentitydispenser)
    {
    }

    public void displayGUIEditSign(TileEntitySign tileentitysign)
    {
    }

    public void useCurrentItemOnEntity(Entity entity)
    {
        if(entity.interact(this))
        {
            return;
        }
        ItemStack itemstack = getCurrentEquippedItem();
        if(itemstack != null && (entity instanceof EntityLiving))
        {
            itemstack.useItemOnEntity((EntityLiving)entity);
            if(itemstack.stackSize <= 0)
            {
                itemstack.func_1097_a(this);
                destroyCurrentEquippedItem();
            }
        }
    }

    public ItemStack getCurrentEquippedItem()
    {
        return inventory.getCurrentItem();
    }

    public void destroyCurrentEquippedItem()
    {
        inventory.setInventorySlotContents(inventory.currentItem, null);
    }

    public double getYOffset()
    {
        return (double)(yOffset - 0.5F);
    }

    public void swingItem()
    {
        swingProgressInt = -1;
        isSwinging = true;
    }

    public void attackTargetEntityWithCurrentItem(Entity entity)
    {
        int i = inventory.getDamageVsEntity(entity);
        if(i > 0)
        {
            entity.attackEntityFrom(this, i);
            ItemStack itemstack = getCurrentEquippedItem();
            if(itemstack != null && (entity instanceof EntityLiving))
            {
                itemstack.hitEntity((EntityLiving)entity, this);
                if(itemstack.stackSize <= 0)
                {
                    itemstack.func_1097_a(this);
                    destroyCurrentEquippedItem();
                }
            }
            if(entity instanceof EntityLiving)
            {
                if(entity.isEntityAlive())
                {
                    func_25055_a((EntityLiving)entity, true);
                }
                func_25058_a(StatList.field_25167_s, i);
            }
        }
    }

    public void respawnPlayer()
    {
    }

    public abstract void func_6420_o();

    public void onItemStackChanged(ItemStack itemstack)
    {
    }

    public void setEntityDead()
    {
        super.setEntityDead();
        inventorySlots.onCraftGuiClosed(this);
        if(craftingInventory != null)
        {
            craftingInventory.onCraftGuiClosed(this);
        }
    }

    public boolean isEntityInsideBlock()
    {
        return !sleeping && super.isEntityInsideBlock();
    }

    public EnumStatus sleepInBedAt(int i, int j, int k)
    {
        if(isPlayerSleeping() || !isEntityAlive())
        {
            return EnumStatus.OTHER_PROBLEM;
        }
        if(worldObj.worldProvider.field_4220_c)
        {
            return EnumStatus.NOT_POSSIBLE_HERE;
        }
        if(worldObj.isDaytime())
        {
            return EnumStatus.NOT_POSSIBLE_NOW;
        }
        if(Math.abs(posX - (double)i) > 3D || Math.abs(posY - (double)j) > 2D || Math.abs(posZ - (double)k) > 3D)
        {
            return EnumStatus.TOO_FAR_AWAY;
        }
        setSize(0.2F, 0.2F);
        yOffset = 0.2F;
        if(worldObj.blockExists(i, j, k))
        {
            int l = worldObj.getBlockMetadata(i, j, k);
            int i1 = BlockBed.getDirectionFromMetadata(l);
            float f = 0.5F;
            float f1 = 0.5F;
            switch(i1)
            {
            case 0: // '\0'
                f1 = 0.9F;
                break;

            case 2: // '\002'
                f1 = 0.1F;
                break;

            case 1: // '\001'
                f = 0.1F;
                break;

            case 3: // '\003'
                f = 0.9F;
                break;
            }
            func_22052_e(i1);
            setPosition((float)i + f, (float)j + 0.9375F, (float)k + f1);
        } else
        {
            setPosition((float)i + 0.5F, (float)j + 0.9375F, (float)k + 0.5F);
        }
        sleeping = true;
        sleepTimer = 0;
        bedChunkCoordinates = new ChunkCoordinates(i, j, k);
        motionX = motionZ = motionY = 0.0D;
        if(!worldObj.multiplayerWorld)
        {
            worldObj.updateAllPlayersSleepingFlag();
        }
        return EnumStatus.OK;
    }

    private void func_22052_e(int i)
    {
        field_22063_x = 0.0F;
        field_22061_z = 0.0F;
        switch(i)
        {
        case 0: // '\0'
            field_22061_z = -1.8F;
            break;

        case 2: // '\002'
            field_22061_z = 1.8F;
            break;

        case 1: // '\001'
            field_22063_x = 1.8F;
            break;

        case 3: // '\003'
            field_22063_x = -1.8F;
            break;
        }
    }

    public void wakeUpPlayer(boolean flag, boolean flag1, boolean flag2)
    {
        setSize(0.6F, 1.8F);
        func_22058_C();
        ChunkCoordinates chunkcoordinates = bedChunkCoordinates;
        ChunkCoordinates chunkcoordinates1 = bedChunkCoordinates;
        if(chunkcoordinates != null && worldObj.getBlockId(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z) == Block.blockBed.blockID)
        {
            BlockBed.setBedOccupied(worldObj, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, false);
            ChunkCoordinates chunkcoordinates2 = BlockBed.getNearestEmptyChunkCoordinates(worldObj, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, 0);
            if(chunkcoordinates2 == null)
            {
                chunkcoordinates2 = new ChunkCoordinates(chunkcoordinates.x, chunkcoordinates.y + 1, chunkcoordinates.z);
            }
            setPosition((float)chunkcoordinates2.x + 0.5F, (float)chunkcoordinates2.y + yOffset + 0.1F, (float)chunkcoordinates2.z + 0.5F);
        }
        sleeping = false;
        if(!worldObj.multiplayerWorld && flag1)
        {
            worldObj.updateAllPlayersSleepingFlag();
        }
        if(flag)
        {
            sleepTimer = 0;
        } else
        {
            sleepTimer = 100;
        }
        if(flag2)
        {
            func_25061_a(bedChunkCoordinates);
        }
    }

    private boolean isBedChunkCoordinatesABedBlock()
    {
        return worldObj.getBlockId(bedChunkCoordinates.x, bedChunkCoordinates.y, bedChunkCoordinates.z) == Block.blockBed.blockID;
    }

    public static ChunkCoordinates func_25060_a(World world, ChunkCoordinates chunkcoordinates)
    {
        IChunkProvider ichunkprovider = world.getIChunkProvider();
        ichunkprovider.func_538_d(chunkcoordinates.x - 3 >> 4, chunkcoordinates.z - 3 >> 4);
        ichunkprovider.func_538_d(chunkcoordinates.x + 3 >> 4, chunkcoordinates.z - 3 >> 4);
        ichunkprovider.func_538_d(chunkcoordinates.x - 3 >> 4, chunkcoordinates.z + 3 >> 4);
        ichunkprovider.func_538_d(chunkcoordinates.x + 3 >> 4, chunkcoordinates.z + 3 >> 4);
        if(world.getBlockId(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z) != Block.blockBed.blockID)
        {
            return null;
        } else
        {
            ChunkCoordinates chunkcoordinates1 = BlockBed.getNearestEmptyChunkCoordinates(world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, 0);
            return chunkcoordinates1;
        }
    }

    public float getBedOrientationInDegrees()
    {
        if(bedChunkCoordinates != null)
        {
            int i = worldObj.getBlockMetadata(bedChunkCoordinates.x, bedChunkCoordinates.y, bedChunkCoordinates.z);
            int j = BlockBed.getDirectionFromMetadata(i);
            switch(j)
            {
            case 0: // '\0'
                return 90F;

            case 1: // '\001'
                return 0.0F;

            case 2: // '\002'
                return 270F;

            case 3: // '\003'
                return 180F;
            }
        }
        return 0.0F;
    }

    public boolean isPlayerSleeping()
    {
        return sleeping;
    }

    public boolean isPlayerFullyAsleep()
    {
        return sleeping && sleepTimer >= 100;
    }

    public int func_22060_M()
    {
        return sleepTimer;
    }

    public void addChatMessage(String s)
    {
    }

    public ChunkCoordinates func_25059_P()
    {
        return field_24900_bi;
    }

    public void func_25061_a(ChunkCoordinates chunkcoordinates)
    {
        if(chunkcoordinates != null)
        {
            field_24900_bi = new ChunkCoordinates(chunkcoordinates);
        } else
        {
            field_24900_bi = null;
        }
    }

    public void func_25058_a(StatBasic statbasic, int i)
    {
    }

    protected void jump()
    {
        super.jump();
        func_25058_a(StatList.field_25171_q, 1);
    }

    public void moveEntityWithHeading(float f, float f1)
    {
        double d = posX;
        double d1 = posY;
        double d2 = posZ;
        super.moveEntityWithHeading(f, f1);
        func_25056_h(posX - d, posY - d1, posZ - d2);
    }

    private void func_25056_h(double d, double d1, double d2)
    {
        if(isInsideOfMaterial(Material.water))
        {
            int i = Math.round(MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2) * 100F);
            if(i > 0)
            {
                func_25058_a(StatList.field_25173_p, i);
            }
        } else
        if(handleWaterMovement())
        {
            int j = Math.round(MathHelper.sqrt_double(d * d + d2 * d2) * 100F);
            if(j > 0)
            {
                func_25058_a(StatList.field_25177_l, j);
            }
        } else
        if(isOnLadder())
        {
            if(d1 > 0.0D)
            {
                func_25058_a(StatList.field_25175_n, (int)Math.round(d1 * 100D));
            }
        } else
        if(onGround)
        {
            int k = Math.round(MathHelper.sqrt_double(d * d + d2 * d2) * 100F);
            if(k > 0)
            {
                func_25058_a(StatList.field_25178_k, k);
            }
        } else
        {
            int l = Math.round(MathHelper.sqrt_double(d * d + d2 * d2) * 100F);
            if(l > 25)
            {
                func_25058_a(StatList.field_25174_o, l);
            }
        }
    }

    protected void fall(float f)
    {
        if(f >= 2.0F)
        {
            func_25058_a(StatList.field_25176_m, (int)Math.round((double)f * 100D));
        }
        super.fall(f);
    }

    public void func_25057_q_()
    {
    }

    public InventoryPlayer inventory;
    public CraftingInventoryCB inventorySlots;
    public CraftingInventoryCB craftingInventory;
    public byte field_9371_f;
    public int score;
    public float field_775_e;
    public float field_774_f;
    public boolean isSwinging;
    public int swingProgressInt;
    public String username;
    public int dimension;
    public String field_20067_q;
    public double field_20066_r;
    public double field_20065_s;
    public double field_20064_t;
    public double field_20063_u;
    public double field_20062_v;
    public double field_20061_w;
    private boolean sleeping;
    private ChunkCoordinates bedChunkCoordinates;
    private int sleepTimer;
    public float field_22063_x;
    public float field_22062_y;
    public float field_22061_z;
    private ChunkCoordinates field_24900_bi;
    private int damageRemainder;
    public EntityFish fishEntity;
}
