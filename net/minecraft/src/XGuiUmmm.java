package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class XGuiUmmm extends Gui
{

    public XGuiUmmm(Minecraft minecraft)
    {
        field_25090_a = new ArrayList();
        field_25089_b = minecraft;
    }

    public void func_25088_a()
    {
        for(int i = 0; i < field_25090_a.size(); i++)
        {
            XErrr xerrr = (XErrr)field_25090_a.get(i);
            xerrr.func_25127_a();
            xerrr.func_25125_a(this);
            if(xerrr.field_25139_h)
            {
                field_25090_a.remove(i--);
            }
        }

    }

    public void func_25087_a(float f)
    {
        field_25089_b.renderEngine.bindTexture(field_25089_b.renderEngine.getTexture("/gui/particles.png"));
        for(int i = 0; i < field_25090_a.size(); i++)
        {
            XErrr xerrr = (XErrr)field_25090_a.get(i);
            int j = (int)((xerrr.field_25144_c + (xerrr.field_25146_a - xerrr.field_25144_c) * (double)f) - 4D);
            int k = (int)((xerrr.field_25143_d + (xerrr.field_25145_b - xerrr.field_25143_d) * (double)f) - 4D);
            float f1 = (float)(xerrr.field_25129_r + (xerrr.field_25133_n - xerrr.field_25129_r) * (double)f);
            float f2 = (float)(xerrr.field_25132_o + (xerrr.field_25136_k - xerrr.field_25132_o) * (double)f);
            float f3 = (float)(xerrr.field_25131_p + (xerrr.field_25135_l - xerrr.field_25131_p) * (double)f);
            float f4 = (float)(xerrr.field_25130_q + (xerrr.field_25134_m - xerrr.field_25130_q) * (double)f);
            GL11.glColor4f(f2, f3, f4, f1);
            drawTexturedModalRect(j, k, 40, 0, 8, 8);
        }

    }

    private List field_25090_a;
    private Minecraft field_25089_b;
}
