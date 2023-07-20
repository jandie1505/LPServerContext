package net.jandie1505.lpservercontext;

import eu.cloudnetservice.common.log.LogManager;
import eu.cloudnetservice.driver.event.EventListener;
import eu.cloudnetservice.driver.event.EventManager;
import eu.cloudnetservice.driver.module.ModuleLifeCycle;
import eu.cloudnetservice.driver.module.ModuleTask;
import eu.cloudnetservice.driver.module.driver.DriverModule;
import eu.cloudnetservice.node.event.service.CloudServicePreProcessStartEvent;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class LPServerContext extends DriverModule {

    @ModuleTask(lifecycle = ModuleLifeCycle.STARTED)
    public void onStart(
            EventManager eventManager
    ) {
        eventManager.registerListener(this);
        LogManager.logger(LPServerContext.class).info("Started LPServerContext");
    }

    @ModuleTask(lifecycle = ModuleLifeCycle.STOPPED)
    public void onStop(
            EventManager eventManager
    ) {
        eventManager.unregisterListener(this);
        LogManager.logger(LPServerContext.class).info("Stopped LPServerContext");
    }

    @EventListener
    public void onCloudServicePreProcessStart(CloudServicePreProcessStartEvent event) {

        Path configPath = event.service().pluginDirectory().resolve("LuckPerms").resolve("config.yml");

        if (Files.exists(configPath) && Files.isRegularFile(configPath)) {
            String taskName = event.service().serviceId().taskName();

            try {

                Yaml yaml = new Yaml();
                Map<String, Object> data = yaml.load(Files.newBufferedReader(configPath));

                data.put("server", taskName);

                yaml.dump(data, Files.newBufferedWriter(configPath));

                LogManager.logger(LPServerContext.class).fine("Updated server context in LuckPerms configuration");

            } catch (IOException e) {
                LogManager.logger(LPServerContext.class).warning("Error while reading/writing LuckPerms configuration", e);
            }

        }

    }

}
