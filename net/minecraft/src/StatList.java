package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.PrintStream;
import java.util.*;

public class StatList
{

    public StatList()
    {
        System.out.println((new StringBuilder()).append("Stats: ").append(field_25188_a.size()).toString());
    }

    public static void func_25152_a(StatBasic statbasic)
    {
        if(field_25169_C.containsKey(Integer.valueOf(statbasic.field_25071_d)))
        {
            throw new RuntimeException((new StringBuilder()).append("Duplicate stat id: ").append(((StatBasic)field_25169_C.get(Integer.valueOf(statbasic.field_25071_d))).field_25070_e).append(" and ").append(statbasic.field_25070_e).append(" at id ").append(statbasic.field_25071_d).toString());
        }
        statbasic.field_25069_f = AchievementMap.func_25208_a(statbasic.field_25071_d);
        field_25188_a.add(statbasic);
        if(statbasic.func_25067_a())
        {
            AchievementList.field_25196_a.add((Achievement)statbasic);
        } else
        if(statbasic instanceof StatCrafting)
        {
            StatCrafting statcrafting = (StatCrafting)statbasic;
            if(statcrafting.func_25072_b() < Block.blocksList.length)
            {
                field_25186_c.add(statcrafting);
            } else
            {
                field_25185_d.add(statcrafting);
            }
        } else
        {
            field_25187_b.add(statbasic);
        }
    }

    public static void func_25154_a()
    {
        field_25172_A = func_25155_a(field_25172_A, "stat.useItem", 0x1020000, 0, Block.blocksList.length);
        field_25170_B = func_25149_b(field_25170_B, "stat.breakItem", 0x1030000, 0, Block.blocksList.length);
        field_25166_D = true;
        func_25157_c();
    }

    public static void func_25151_b()
    {
        field_25172_A = func_25155_a(field_25172_A, "stat.useItem", 0x1020000, Block.blocksList.length, 32000);
        field_25170_B = func_25149_b(field_25170_B, "stat.breakItem", 0x1030000, Block.blocksList.length, 32000);
        field_25164_E = true;
        func_25157_c();
    }

    public static void func_25157_c()
    {
        if(!field_25166_D || !field_25164_E)
        {
            return;
        }
        HashSet hashset = new HashSet();
        IRecipe irecipe;
        for(Iterator iterator = CraftingManager.getInstance().func_25193_b().iterator(); iterator.hasNext(); hashset.add(Integer.valueOf(irecipe.func_25117_b().itemID)))
        {
            irecipe = (IRecipe)iterator.next();
        }

        ItemStack itemstack;
        for(Iterator iterator1 = FurnaceRecipes.smelting().func_25194_b().values().iterator(); iterator1.hasNext(); hashset.add(Integer.valueOf(itemstack.itemID)))
        {
            itemstack = (ItemStack)iterator1.next();
        }

        field_25158_z = new StatBasic[32000];
        Iterator iterator2 = hashset.iterator();
        do
        {
            if(!iterator2.hasNext())
            {
                break;
            }
            Integer integer = (Integer)iterator2.next();
            if(Item.itemsList[integer.intValue()] != null)
            {
                String s = StatCollector.func_25199_a("stat.craftItem", new Object[] {
                    Item.itemsList[integer.intValue()].func_25009_k()
                });
                field_25158_z[integer.intValue()] = (new StatCrafting(0x1010000 + integer.intValue(), s, integer.intValue())).func_25068_c();
            }
        } while(true);
        func_25150_a(field_25158_z);
    }

    private static StatBasic[] func_25153_a(String s, int i)
    {
        StatBasic astatbasic[] = new StatBasic[256];
        for(int j = 0; j < 256; j++)
        {
            if(Block.blocksList[j] != null)
            {
                String s1 = StatCollector.func_25199_a(s, new Object[] {
                    Block.blocksList[j].func_25016_i()
                });
                astatbasic[j] = (new StatCrafting(i + j, s1, j)).func_25068_c();
            }
        }

        func_25150_a(astatbasic);
        return astatbasic;
    }

    private static StatBasic[] func_25155_a(StatBasic astatbasic[], String s, int i, int j, int k)
    {
        if(astatbasic == null)
        {
            astatbasic = new StatBasic[32000];
        }
        for(int l = j; l < k; l++)
        {
            if(Item.itemsList[l] != null)
            {
                String s1 = StatCollector.func_25199_a(s, new Object[] {
                    Item.itemsList[l].func_25009_k()
                });
                astatbasic[l] = (new StatCrafting(i + l, s1, l)).func_25068_c();
            }
        }

        func_25150_a(astatbasic);
        return astatbasic;
    }

    private static StatBasic[] func_25149_b(StatBasic astatbasic[], String s, int i, int j, int k)
    {
        if(astatbasic == null)
        {
            astatbasic = new StatBasic[32000];
        }
        for(int l = j; l < k; l++)
        {
            if(Item.itemsList[l] != null && Item.itemsList[l].func_25007_g())
            {
                String s1 = StatCollector.func_25199_a(s, new Object[] {
                    Item.itemsList[l].func_25009_k()
                });
                astatbasic[l] = (new StatCrafting(i + l, s1, l)).func_25068_c();
            }
        }

        func_25150_a(astatbasic);
        return astatbasic;
    }

    private static void func_25150_a(StatBasic astatbasic[])
    {
        func_25156_a(astatbasic, Block.waterStill.blockID, Block.waterMoving.blockID);
        func_25156_a(astatbasic, Block.lavaStill.blockID, Block.lavaStill.blockID);
        func_25156_a(astatbasic, Block.pumpkinLantern.blockID, Block.pumpkin.blockID);
        func_25156_a(astatbasic, Block.stoneOvenActive.blockID, Block.stoneOvenIdle.blockID);
        func_25156_a(astatbasic, Block.oreRedstoneGlowing.blockID, Block.oreRedstone.blockID);
        func_25156_a(astatbasic, Block.redstoneRepeaterActive.blockID, Block.redstoneRepeaterIdle.blockID);
        func_25156_a(astatbasic, Block.torchRedstoneActive.blockID, Block.torchRedstoneIdle.blockID);
        func_25156_a(astatbasic, Block.mushroomRed.blockID, Block.mushroomBrown.blockID);
        func_25156_a(astatbasic, Block.stairSingle.blockID, Block.stairDouble.blockID);
    }

    private static void func_25156_a(StatBasic astatbasic[], int i, int j)
    {
        astatbasic[i] = astatbasic[j];
        field_25188_a.remove(astatbasic[i]);
        field_25187_b.remove(astatbasic[i]);
    }

    private static Map field_25169_C = new HashMap();
    public static List field_25188_a = new ArrayList();
    public static List field_25187_b = new ArrayList();
    public static List field_25186_c = new ArrayList();
    public static List field_25185_d = new ArrayList();
    public static StatBasic field_25184_e = (new StatBasic(1000, StatCollector.func_25200_a("stat.startGame"))).func_25068_c();
    public static StatBasic field_25183_f = (new StatBasic(1001, StatCollector.func_25200_a("stat.createWorld"))).func_25068_c();
    public static StatBasic field_25182_g = (new StatBasic(1002, StatCollector.func_25200_a("stat.loadWorld"))).func_25068_c();
    public static StatBasic field_25181_h = (new StatBasic(1003, StatCollector.func_25200_a("stat.joinMultiplayer"))).func_25068_c();
    public static StatBasic field_25180_i = (new StatBasic(1004, StatCollector.func_25200_a("stat.leaveGame"))).func_25068_c();
    public static StatBasic field_25179_j = (new StatTime(1100, StatCollector.func_25200_a("stat.playOneMinute"))).func_25068_c();
    public static StatBasic field_25178_k = (new StatDistance(2000, StatCollector.func_25200_a("stat.walkOneCm"))).func_25068_c();
    public static StatBasic field_25177_l = (new StatDistance(2001, StatCollector.func_25200_a("stat.swimOneCm"))).func_25068_c();
    public static StatBasic field_25176_m = (new StatDistance(2002, StatCollector.func_25200_a("stat.fallOneCm"))).func_25068_c();
    public static StatBasic field_25175_n = (new StatDistance(2003, StatCollector.func_25200_a("stat.climbOneCm"))).func_25068_c();
    public static StatBasic field_25174_o = (new StatDistance(2004, StatCollector.func_25200_a("stat.flyOneCm"))).func_25068_c();
    public static StatBasic field_25173_p = (new StatDistance(2005, StatCollector.func_25200_a("stat.diveOneCm"))).func_25068_c();
    public static StatBasic field_25171_q = (new StatBasic(2010, StatCollector.func_25200_a("stat.jump"))).func_25068_c();
    public static StatBasic field_25168_r = (new StatBasic(2011, StatCollector.func_25200_a("stat.drop"))).func_25068_c();
    public static StatBasic field_25167_s = (new StatBasic(2020, StatCollector.func_25200_a("stat.damageDealt"))).func_25068_c();
    public static StatBasic field_25165_t = (new StatBasic(2021, StatCollector.func_25200_a("stat.damageTaken"))).func_25068_c();
    public static StatBasic field_25163_u = (new StatBasic(2022, StatCollector.func_25200_a("stat.deaths"))).func_25068_c();
    public static StatBasic field_25162_v = (new StatBasic(2023, StatCollector.func_25200_a("stat.mobKills"))).func_25068_c();
    public static StatBasic field_25161_w = (new StatBasic(2024, StatCollector.func_25200_a("stat.playerKills"))).func_25068_c();
    public static StatBasic field_25160_x = (new StatBasic(2025, StatCollector.func_25200_a("stat.fishCaught"))).func_25068_c();
    public static StatBasic field_25159_y[] = func_25153_a("stat.mineBlock", 0x1000000);
    public static StatBasic field_25158_z[] = null;
    public static StatBasic field_25172_A[] = null;
    public static StatBasic field_25170_B[] = null;
    private static boolean field_25166_D = false;
    private static boolean field_25164_E = false;

}
