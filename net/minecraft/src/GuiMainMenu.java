package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiMainMenu extends GuiScreen
{

    public GuiMainMenu()
    {
        updateCounter = 0.0F;
        splashText = "missingno";
        try
        {
            ArrayList arraylist = new ArrayList();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader((GuiMainMenu.class).getResourceAsStream("/title/splashes.txt"), Charset.forName("UTF-8")));
            String s = "";
            do
            {
                String s1;
                if((s1 = bufferedreader.readLine()) == null)
                {
                    break;
                }
                s1 = s1.trim();
                if(s1.length() > 0)
                {
                    arraylist.add(s1);
                }
            } while(true);
            splashText = "KOCHAM PIWO";
        }
        catch(Exception exception) { }
    }

    public void updateScreen()
    {
        updateCounter++;
    }

    protected void keyTyped(char c, int i)
    {
    }

    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        int i = height / 4 + 48;
        controlList.add(new GuiButton(1, width / 2 - 100, i, stringtranslate.translateKey("menu.singleplayer")));
        controlList.add(field_25096_l = new GuiButton(2, width / 2 - 100, i + 24, stringtranslate.translateKey("menu.multiplayer")));
        controlList.add(new GuiButton(3, width / 2 - 100, i + 48, stringtranslate.translateKey("menu.mods")));
        controlList.add(new GuiButton(3, width / 2 - 100, i + 48, stringtranslate.translateKey("menu.mods")));
        controlList.add(new GuiButton(2137, width / 2 - 100, i + 72, "dwukropek de (((bernard mod)"));
        if(mc.hideQuitButton)
        {
            controlList.add(new GuiButton(0, width / 2 - 100, i + 72 + 24, stringtranslate.translateKey("menu.options")));
        } else
        {
            controlList.add(new GuiButton(0, width / 2 - 100, i + 72 + 24, 98, 20, stringtranslate.translateKey("menu.options")));
            controlList.add(new GuiButton(4, width / 2 + 2, i + 72 + 24, 98, 20, stringtranslate.translateKey("menu.quit")));
        }
        if(mc.session == null)
        {
            field_25096_l.enabled = false;
        }
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(guibutton.id == 0)
        {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }
        if(guibutton.id == 1)
        {
            mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if(guibutton.id == 2)
        {
            mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if(guibutton.id == 3)
        {
            mc.displayGuiScreen(new GuiTexturePacks(this));
        }
        if(guibutton.id == 2137)
        {
            mc.displayGuiScreen(new GuiBernard(this, "bernard moment mod", "darmowy sex????????????", "mozna jak najbardziej", "krutka pilka"));
        }
        if(guibutton.id == 4)
        {
            mc.shutdown();
        }
    }

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        Tessellator tessellator = Tessellator.instance;
        char c = '\u0112';
        int k = width / 2 - c / 2;
        byte byte0 = 30;
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/title/mclogo.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(k + 0, byte0 + 0, 0, 0, 155, 44);
        drawTexturedModalRect(k + 155, byte0 + 0, 0, 45, 155, 44);
        tessellator.setColorOpaque_I(0xffffff);
        GL11.glPushMatrix();
        GL11.glTranslatef(width / 2 + 90, 70F, 0.0F);
        GL11.glRotatef(-20F, 0.0F, 0.0F, 1.0F);
        float f1 = 1.8F - MathHelper.abs(MathHelper.sin(((float)(System.currentTimeMillis() % 1000L) / 1000F) * 3.141593F * 2.0F) * 0.1F);
        f1 = (f1 * 100F) / (float)(fontRenderer.getStringWidth(splashText) + 32);
        GL11.glScalef(f1, f1, f1);
        drawCenteredString(fontRenderer, splashText, 0, -8, 0xffff00);
        GL11.glPopMatrix();
        drawString(fontRenderer, "Minecraft Beta 1.4", 2, 2, 0x505050);
        String s = "prawo autorskie piwc.io nie rozpowszechniaj za darmo tylko za piwo";
        drawString(fontRenderer, s, width - fontRenderer.getStringWidth(s) - 2, height - 10, 0xffffff);
        super.drawScreen(i, j, f);
    }

    private static final Random rand = new Random();
    private float updateCounter;
    private String splashText;
    private GuiButton field_25096_l;

}
