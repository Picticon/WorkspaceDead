package workspacedead.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.BoneGolem;
import workspacedead.entity.mob.DraconicBlaze;
import workspacedead.entity.mob.GrassyPotato;
import workspacedead.entity.mob.RottenPotato;
import workspacedead.entity.mob.SkeletonChicken;
import workspacedead.entity.mob.SkeletonCow;
import workspacedead.entity.mob.SkeletonPig;
import workspacedead.entity.mob.SkeletonSheep;
import workspacedead.entity.mob.SkeletonSlime;
import workspacedead.entity.mob.SkeletonSpider;
import workspacedead.entity.projectile.DeadArrow;
import workspacedead.entity.projectile.DirtyArrow;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            WorkspaceDead.MOD_ID);

    // CUSTOM MOBS //

    public static final RegistryObject<EntityType<SkeletonCow>> SKELETONCOW = ENTITY_TYPES.register("skeletoncow", //
            () -> EntityType.Builder.<SkeletonCow>of(SkeletonCow::new, MobCategory.MONSTER).sized(0.9F, 1.4F)
                    .clientTrackingRange(10).build("skeletoncow"));
    public static final RegistryObject<EntityType<SkeletonChicken>> SKELETONCHICKEN = ENTITY_TYPES.register(
            "skeletonchicken", //
            () -> EntityType.Builder.<SkeletonChicken>of(SkeletonChicken::new, MobCategory.MONSTER).sized(0.9F, 1.4F)
                    .clientTrackingRange(10).build("skeletonchicken"));
    public static final RegistryObject<EntityType<SkeletonSheep>> SKELETONSHEEP = ENTITY_TYPES.register("skeletonsheep", //
            () -> EntityType.Builder.<SkeletonSheep>of(SkeletonSheep::new, MobCategory.MONSTER).sized(0.9F, 1.4F)
                    .clientTrackingRange(10).build("skeletonsheep"));
    public static final RegistryObject<EntityType<SkeletonPig>> SKELETONPIG = ENTITY_TYPES.register("skeletonpig", //
            () -> EntityType.Builder.<SkeletonPig>of(SkeletonPig::new, MobCategory.MONSTER).sized(0.9F, 1.4F)
                    .clientTrackingRange(10).build("skeletonpig"));
    public static final RegistryObject<EntityType<SkeletonSlime>> SKELETONSLIME = ENTITY_TYPES.register("skeletonslime", //
            () -> EntityType.Builder.<SkeletonSlime>of(SkeletonSlime::new, MobCategory.MONSTER).sized(0.9F, 1.4F)
                    .clientTrackingRange(10).build("skeletonslime"));
    public static final RegistryObject<EntityType<SkeletonSpider>> SKELETONSPIDER = ENTITY_TYPES.register(
            "skeletonspider", //
            () -> EntityType.Builder.<SkeletonSpider>of(SkeletonSpider::new, MobCategory.MONSTER).sized(0.9F, 1.4F)
                    .clientTrackingRange(10).build("skeletonspider"));
    public static final RegistryObject<EntityType<DraconicBlaze>> DRACONICBLAZE = ENTITY_TYPES.register("draconicblaze", //
            () -> EntityType.Builder.<DraconicBlaze>of(DraconicBlaze::new, MobCategory.MONSTER).sized(0.9F, 1.4F)
                    .clientTrackingRange(10).build("draconicblaze"));

    public static final RegistryObject<EntityType<GrassyPotato>> GRASSYPOTATO = ENTITY_TYPES.register("grassypotato", //
            () -> EntityType.Builder.<GrassyPotato>of(GrassyPotato::new, MobCategory.MONSTER)//
                    .sized(1F, 1F).clientTrackingRange(20).build("grassypotato"));

    public static final RegistryObject<EntityType<RottenPotato>> ROTTENPOTATO = ENTITY_TYPES.register("rottenpotato", //
            () -> EntityType.Builder.<RottenPotato>of(RottenPotato::new, MobCategory.MONSTER)//
                    .sized(1F, 1F).clientTrackingRange(20).build("rottenpotato"));

    public static final RegistryObject<EntityType<BoneGolem>> BONE_GOLEM = ENTITY_TYPES.register("bonegolem", //
            () -> EntityType.Builder.<BoneGolem>of(BoneGolem::new, MobCategory.CREATURE)//
                    .sized(1F, 1F).clientTrackingRange(20).build("bonegolem"));

    public static final RegistryObject<EntityType<DeadArrow>> DEAD_ARROW = ENTITY_TYPES.register("deadarrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<DeadArrow>) DeadArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("deadarrow"));
    public static final RegistryObject<EntityType<DirtyArrow>> DIRTY_ARROW = ENTITY_TYPES.register("dirtyarrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<DirtyArrow>) DirtyArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("dirtyarrow"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
        SpawnEggs.register(eventBus); // ok to make spawn eggs now
    }
}