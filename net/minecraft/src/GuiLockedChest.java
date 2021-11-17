package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

public class GuiLockedChest extends GuiScreen
{

    public GuiLockedChest()
    {
        field_25094_a = 256;
        field_25095_i = 166;
    }

    public void initGui()
    {
        controlList.clear();
        controlList.add(new GuiSmallButton(0, width / 2 - 110 - 4, height / 2 + 50, 110, 20, "Not now"));
        controlList.add(new GuiSmallButton(1, width / 2 + 4, height / 2 + 50, 110, 20, "Go to store"));
    }

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        func_25092_a(f);
        GL11.glDisable(2896 /*GL_LIGHTING*/);
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
        GL11.glPushMatrix();
        int k = mc.renderEngine.getTexture("/terrain.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        GL11.glTranslatef(width / 2, height / 2 - 20, 0.0F);
        float f1 = 48F;
        GL11.glScalef(f1, f1, f1);
        GL11.glRotatef(190F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(((float)(System.currentTimeMillis() % 10000L) / 10000F) * 360F, 0.0F, 1.0F, 0.0F);
        (new RenderBlocks()).func_1238_a(Block.field_25017_bj, 1.0F);
        GL11.glPopMatrix();
        func_25093_j();
        super.drawScreen(i, j, f);
        GL11.glEnable(2896 /*GL_LIGHTING*/);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(guibutton.id == 0)
        {
            mc.displayGuiScreen(null);
        }
        if(guibutton.id == 1)
        {
            Sys.openURL("http://www.minecraft.net/store/loot.jsp");
        }
    }

    public void updateScreen()
    {
    }

    protected void func_25093_j()
    {
        int i = (width - field_25094_a) / 2;
        int j = (height - field_25095_i) / 2;
        GL11.glPushMatrix();
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glTranslatef(0.0F, 0.0F, 20F);
        fontRenderer.drawString("Steve Co. Supply Crate", (i + 13) / 2, (j + 12) / 2, 0);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        fontRenderer.drawString("?", width / 8 - 2, height / 8 - 8, (System.currentTimeMillis() / 500L) % 2L != 0L ? 0xff000000 : -64);
        GL11.glPopMatrix();
    }

    protected void func_25092_a(float f)
    {
        int i = mc.renderEngine.getTexture("/achievement/bg2.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - field_25094_a) / 2;
        int k = (height - field_25095_i) / 2;
        drawTexturedModalRect(j, k, 0, 0, field_25094_a, field_25095_i);
        fontRenderer.func_25119_a("You need a Steve Co. Supply Crate Key to open this. You can pick one up at the Minecraft store.", j + 15, k + 100, field_25094_a - 30, 0x404040);
        zLevel = 0.0F;
        GL11.glDepthFunc(518);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, -80F);
        GL11.glDepthFunc(515);
        GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
        GL11.glPopMatrix();
        zLevel = 0.0F;
        GL11.glDepthFunc(515);
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }

    protected int field_25094_a;
    protected int field_25095_i;
}
