package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.File;
import java.util.List;

public class SaveOldDir extends SaveHandler
{

    public SaveOldDir(File file, String s, boolean flag)
    {
        super(file, s, flag);
    }

    public IChunkLoader getChunkLoader(WorldProvider worldprovider)
    {
        File file = func_22153_a();
        if(worldprovider instanceof WorldProviderHell)
        {
            File file1 = new File(file, "DIM-1");
            file1.mkdirs();
            return new McRegionChunkLoader(file1);
        } else
        {
            return new McRegionChunkLoader(file);
        }
    }

    public void saveWorldInfoAndPlayer(WorldInfo worldinfo, List list)
    {
        worldinfo.setSaveVersion(19132);
        super.saveWorldInfoAndPlayer(worldinfo, list);
    }
}
