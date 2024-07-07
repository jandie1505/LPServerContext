package net.jandie1505.lpservercontext;

import eu.cloudnetservice.driver.event.EventListener;
import eu.cloudnetservice.driver.event.EventManager;
import eu.cloudnetservice.driver.module.ModuleLifeCycle;
import eu.cloudnetservice.driver.module.ModuleTask;
import eu.cloudnetservice.driver.module.driver.DriverModule;
import eu.cloudnetservice.node.event.service.CloudServiceConfigurationPrePrepareEvent;

public class LPServerContext extends DriverModule {

    @ModuleTask(lifecycle = ModuleLifeCycle.STARTED)
    public void onStart(EventManager eventManager) {
        eventManager.registerListener(this);
    }

    @ModuleTask(lifecycle = ModuleLifeCycle.STOPPED)
    public void onStop(EventManager eventManager) {
        eventManager.unregisterListener(this);
    }

    @EventListener
    public void onCloudServiceConfigurationPrePrepareEvent(CloudServiceConfigurationPrePrepareEvent event) {

        String taskName = event.originalConfiguration().serviceId().taskName();

        event.modifiableConfiguration().modifyJvmOptions(options -> {
            options.add("-Dluckperms.server=" + taskName);
        });

    }

}
