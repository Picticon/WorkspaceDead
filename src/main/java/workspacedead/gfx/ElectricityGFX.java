package workspacedead.gfx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.phys.Vec3;

public class ElectricityGFX {
    public Vec3 startPos;
    public Vec3 endPos;
    public List<Vec3> subPoints = new ArrayList<>();
    private Random random;
    private int counter = 100;
    private int counterlength = 10;

    public ElectricityGFX(Random rand, Vec3 startPos, Vec3 targetPos) {
        this.startPos = startPos;
        this.endPos = targetPos;
        this.random = rand;
    }

    public void makePoints() {
        subPoints.clear();
        Vec3 dist = endPos.subtract(startPos);
        double points = 5;
        for (int i = 0; i < points; i++) {
            Vec3 sub = startPos.add(dist.x / points * i, dist.y / points * i, dist.z / points * i); // sub section

            double offx = 0, offy = 0, offz = 0;
            switch (i) {
            case 0:
                offx = (random.nextDouble() - .5) * .02f;
                offy = (random.nextDouble() - .5) * .1f;
                offz = (random.nextDouble() - .5) * .02f;
                break;
            case 1:
                offx = (random.nextDouble() - .5) * .05f;
                offy = (random.nextDouble() - .5) * .05f;
                offz = (random.nextDouble() - .5) * .05f;
                break;
            case 2:
                offx = (random.nextDouble() - .5) * .1f;
                offy = (random.nextDouble() - .5) * .1f;
                offz = (random.nextDouble() - .5) * .1f;
                break;
            case 3:
                offx = (random.nextDouble() - .5) * .13f;
                offy = (random.nextDouble() - .5) * .13f;
                offz = (random.nextDouble() - .5) * .13f;
                break;
            case 4:
                offx = (random.nextDouble() - .5) * .1f;
                offy = (random.nextDouble() - .5) * .1f;
                offz = (random.nextDouble() - .5) * .1f;
                break;
            default:
            }
            subPoints.add(sub.add(offx, offy, offz));
        }
        counter = 0;
        counterlength = random.nextInt(10) + 3;
    }

    private void movePoints() {
        this.subPoints.set(0, this.subPoints.get(0).add(0, (this.random.nextDouble() - .5f) * .01f, 0));
        this.subPoints.set(1, this.subPoints.get(1).add((this.random.nextDouble() - .5f) * .002f,
                (this.random.nextDouble() - .5f) * .002f, (this.random.nextDouble() - .5f) * .002f));
        this.subPoints.set(2, this.subPoints.get(2).add((this.random.nextDouble() - .5f) * .05f,
                (this.random.nextDouble() - .5f) * .05f, (this.random.nextDouble() - .5f) * .05f));
        this.subPoints.set(3, this.subPoints.get(3).add((this.random.nextDouble() - .5f) * .002f,
                (this.random.nextDouble() - .5f) * .002f, (this.random.nextDouble() - .5f) * .002f));
        this.subPoints.set(4, this.subPoints.get(4).add((this.random.nextDouble() - .5f) * .01f,
                (this.random.nextDouble() - .5f) * .01f, (this.random.nextDouble() - .5f) * .01f));
    }

    public void drawAnimation(double tileX, double tileY, double tileZ, float[] rgba, float lineWidth,
            MultiBufferSource buffers, PoseStack transform) {
        if (counter > counterlength)
            makePoints();
        else
            movePoints();
        counter++;
        // transform.pushPose();
        //transform.mulPose(new Quaternion(90, 0, 0, true));
        RenderType type = RenderType.lines();//IERenderTypes.getLines(lineWidth);

        // RenderType.State glState = RenderType.State.getBuilder().line(new RenderState.LineState(OptionalDouble.of(1)))
        //         .layer(ObfuscationReflectionHelper.getPrivateValue(RenderState.class, null, "field_228500_J_"))
        //         .transparency(ObfuscationReflectionHelper.getPrivateValue(RenderState.class, null, "field_228515_g_"))
        //         .writeMask(new RenderState.WriteMaskState(true, false))
        //         .depthTest(new RenderState.DepthTestState(GL11.GL_ALWAYS)).build(false);

        VertexConsumer base = buffers.getBuffer(type);
        TransformingVertexBuilder builder = new TransformingVertexBuilder(base, transform, type.format());
        builder.defaultColor((int) (255 * rgba[0]), (int) (255 * rgba[1]), (int) (255 * rgba[2]),
                (int) (255 * rgba[3]));
        for (int i = 0; i < this.subPoints.size() - 1; i++)
            drawLine(this.subPoints.get(i), this.subPoints.get(i + 1), tileX, tileY, tileZ, builder);
        Vec3 end = this.endPos;
        drawLine(this.subPoints.get(this.subPoints.size() - 1), end, tileX, tileY, tileZ, builder);
    }

    private static void drawLine(Vec3 start, Vec3 end, double offX, double offY, double offZ, VertexConsumer out) {
        Vec3 normal = new Vec3(start.x() - end.x(), start.y() - end.y(), start.z() - end.z()).normalize();
        out.vertex(start.x - offX, start.y - offY, start.z - offZ)
                .normal((float) normal.x, (float) normal.y, (float) normal.z).endVertex();
        out.vertex(end.x - offX, end.y - offY, end.z - offZ)
                .normal((float) normal.x, (float) normal.y, (float) normal.z).endVertex();
    }
}