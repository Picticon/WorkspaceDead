/*package workspacedead.tconstruct;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import workspacedead.effect.ModEffects;

public class DeadInsideModifier extends Modifier {

    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        // 50% chance of applying
        LivingEntity target = context.getLivingTarget();
        if (target != null && target.isAlive() ) {
            // set entity so the potion is attributed as a player kill
            target.setLastHurtMob(context.getAttacker());
            target.addEffect(new MobEffectInstance(ModEffects.DEADINSIDE.get(), 200), context.getAttacker());
        }
        return 0;
    }
} */