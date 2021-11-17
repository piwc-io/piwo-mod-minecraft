package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import org.lwjgl.opengl.GL11;

public class ModelWolf extends ModelBase
{

    public ModelWolf()
    {
        float f = 0.0F;
        float f1 = 13.5F;
        field_25114_a = new ModelRenderer(0, 0);
        field_25114_a.addBox(-3F, -3F, -2F, 6, 6, 4, f);
        field_25114_a.setPosition(-1F, f1, -7F);
        field_25113_b = new ModelRenderer(18, 14);
        field_25113_b.addBox(-4F, -2F, -3F, 6, 9, 6, f);
        field_25113_b.setPosition(0.0F, 14F, 2.0F);
        field_25104_k = new ModelRenderer(21, 0);
        field_25104_k.addBox(-4F, -3F, -3F, 8, 6, 7, f);
        field_25104_k.setPosition(-1F, 14F, 2.0F);
        field_25112_c = new ModelRenderer(0, 18);
        field_25112_c.addBox(-1F, 0.0F, -1F, 2, 8, 2, f);
        field_25112_c.setPosition(-2.5F, 16F, 7F);
        field_25111_d = new ModelRenderer(0, 18);
        field_25111_d.addBox(-1F, 0.0F, -1F, 2, 8, 2, f);
        field_25111_d.setPosition(0.5F, 16F, 7F);
        field_25110_e = new ModelRenderer(0, 18);
        field_25110_e.addBox(-1F, 0.0F, -1F, 2, 8, 2, f);
        field_25110_e.setPosition(-2.5F, 16F, -4F);
        field_25109_f = new ModelRenderer(0, 18);
        field_25109_f.addBox(-1F, 0.0F, -1F, 2, 8, 2, f);
        field_25109_f.setPosition(0.5F, 16F, -4F);
        field_25105_j = new ModelRenderer(9, 18);
        field_25105_j.addBox(-1F, 0.0F, -1F, 2, 8, 2, f);
        field_25105_j.setPosition(-1F, 12F, 8F);
        field_25108_g = new ModelRenderer(16, 14);
        field_25108_g.addBox(-3F, -5F, 0.0F, 2, 2, 1, f);
        field_25108_g.setPosition(-1F, f1, -7F);
        field_25107_h = new ModelRenderer(16, 14);
        field_25107_h.addBox(1.0F, -5F, 0.0F, 2, 2, 1, f);
        field_25107_h.setPosition(-1F, f1, -7F);
        field_25106_i = new ModelRenderer(0, 10);
        field_25106_i.addBox(-2F, 0.0F, -5F, 3, 3, 4, f);
        field_25106_i.setPosition(-0.5F, f1, -7F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        field_25114_a.func_25122_b(f5);
        field_25113_b.render(f5);
        field_25112_c.render(f5);
        field_25111_d.render(f5);
        field_25110_e.render(f5);
        field_25109_f.render(f5);
        field_25108_g.func_25122_b(f5);
        field_25107_h.func_25122_b(f5);
        field_25106_i.func_25122_b(f5);
        field_25105_j.func_25122_b(f5);
        field_25104_k.render(f5);
    }

    public void func_25103_a(EntityLiving entityliving, float f, float f1, float f2)
    {
        EntityWolf entitywolf = (EntityWolf)entityliving;
        if(entitywolf.func_25040_C())
        {
            field_25105_j.rotateAngleY = 0.0F;
        } else
        {
            field_25105_j.rotateAngleY = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        }
        if(entitywolf.func_25034_B())
        {
            field_25104_k.setPosition(-1F, 16F, -3F);
            field_25104_k.rotateAngleX = 1.256637F;
            field_25104_k.rotateAngleY = 0.0F;
            field_25113_b.setPosition(0.0F, 18F, 0.0F);
            field_25113_b.rotateAngleX = 0.7853982F;
            field_25105_j.setPosition(-1F, 21F, 6F);
            field_25112_c.setPosition(-2.5F, 22F, 2.0F);
            field_25112_c.rotateAngleX = 4.712389F;
            field_25111_d.setPosition(0.5F, 22F, 2.0F);
            field_25111_d.rotateAngleX = 4.712389F;
            field_25110_e.rotateAngleX = 5.811947F;
            field_25110_e.setPosition(-2.49F, 17F, -4F);
            field_25109_f.rotateAngleX = 5.811947F;
            field_25109_f.setPosition(0.51F, 17F, -4F);
        } else
        {
            field_25113_b.setPosition(0.0F, 14F, 2.0F);
            field_25113_b.rotateAngleX = 1.570796F;
            field_25104_k.setPosition(-1F, 14F, -3F);
            field_25104_k.rotateAngleX = field_25113_b.rotateAngleX;
            field_25105_j.setPosition(-1F, 12F, 8F);
            field_25112_c.setPosition(-2.5F, 16F, 7F);
            field_25111_d.setPosition(0.5F, 16F, 7F);
            field_25110_e.setPosition(-2.5F, 16F, -4F);
            field_25109_f.setPosition(0.5F, 16F, -4F);
            field_25112_c.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
            field_25111_d.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
            field_25110_e.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
            field_25109_f.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        }
        float f3 = entitywolf.func_25033_c(f2) + entitywolf.func_25042_a(f2, 0.0F);
        field_25114_a.rotateAngleZ = f3;
        field_25108_g.rotateAngleZ = f3;
        field_25107_h.rotateAngleZ = f3;
        field_25106_i.rotateAngleZ = f3;
        field_25104_k.rotateAngleZ = entitywolf.func_25042_a(f2, -0.08F);
        field_25113_b.rotateAngleZ = entitywolf.func_25042_a(f2, -0.16F);
        field_25105_j.rotateAngleZ = entitywolf.func_25042_a(f2, -0.2F);
        if(entitywolf.func_25039_v())
        {
            float f4 = entitywolf.getEntityBrightness(f2) * entitywolf.func_25043_b_(f2);
            GL11.glColor3f(f4, f4, f4);
        }
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5);
        field_25114_a.rotateAngleX = -(f4 / 57.29578F);
        field_25114_a.rotateAngleY = f3 / 57.29578F;
        field_25108_g.rotateAngleY = field_25114_a.rotateAngleY;
        field_25108_g.rotateAngleX = field_25114_a.rotateAngleX;
        field_25107_h.rotateAngleY = field_25114_a.rotateAngleY;
        field_25107_h.rotateAngleX = field_25114_a.rotateAngleX;
        field_25106_i.rotateAngleY = field_25114_a.rotateAngleY;
        field_25106_i.rotateAngleX = field_25114_a.rotateAngleX;
        field_25105_j.rotateAngleX = f2;
    }

    public ModelRenderer field_25114_a;
    public ModelRenderer field_25113_b;
    public ModelRenderer field_25112_c;
    public ModelRenderer field_25111_d;
    public ModelRenderer field_25110_e;
    public ModelRenderer field_25109_f;
    ModelRenderer field_25108_g;
    ModelRenderer field_25107_h;
    ModelRenderer field_25106_i;
    ModelRenderer field_25105_j;
    ModelRenderer field_25104_k;
}
