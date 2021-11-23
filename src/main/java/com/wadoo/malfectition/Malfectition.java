package com.wadoo.malfectition;



import com.wadoo.malfectition.common.registry.MalfectionEntities;
import com.wadoo.malfectition.common.registry.MalfectionSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("malfection")
public class Malfectition {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "malfection";
    public Malfectition() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        MinecraftForge.EVENT_BUS.register(this);

        MalfectionEntities.ENTITIES.register(bus);
        MalfectionSounds.SOUNDS.register(bus);

        GeckoLib.initialize();
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}
