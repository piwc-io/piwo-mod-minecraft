package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

public class GuiBernard extends GuiScreen
{

    public GuiBernard(GuiScreen guiscreen, String s, String s1, String s2, String s3)
    {
        parentScreen = guiscreen;
        message1 = s;
        message2 = s1;
        field_22106_k = s2;
        field_22105_l = s3;
    }

    public void initGui()
    {
        int i = height / 4 + 48;
        controlList.add(new GuiButton(0, width / 2 - 100, i + 48, "wyjdz kurwa!!!!!!!!!!!!"));
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        parentScreen.mc.displayGuiScreen(new GuiMainMenu());
    }

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, message1, width / 2, 70, 0xffffff);
        drawCenteredString(fontRenderer, message2, width / 2, 90, 0xffffff);
        super.drawScreen(i, j, f);
    }

    private GuiScreen parentScreen;
    private String message1;
    private String message2;
    private String field_22106_k;
    private String field_22105_l;
}
