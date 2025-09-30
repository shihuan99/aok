package com.aok.core;

import org.apache.qpid.server.SystemLauncher;
import org.apache.qpid.server.SystemLauncherListener;
import org.apache.qpid.server.model.BrokerImpl;
import org.apache.qpid.server.model.ConfiguredObjectFactory;
import org.apache.qpid.server.model.ConfiguredObjectFactoryImpl;
import org.apache.qpid.server.model.JsonSystemConfigImpl;
import org.apache.qpid.server.model.SystemConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Descriptions.
 *
 * @since 2025/9/29
 */
public class BrokerQpid {
    private static void startBroker(Map<String,Object> attributes) throws Exception
    {
        SystemLauncher systemLauncher = new SystemLauncher(new SystemLauncherListener.DefaultSystemLauncherListener(),
                new SystemLauncherListener.DefaultSystemLauncherListener()
                {
                    @Override
                    public void onShutdown(final int exitCode)
                    {
                        if (exitCode != 0)
                        {
                            shutdown(exitCode);
                        }
                    }
                });
        systemLauncher.startup(attributes);
    }

    private static void shutdown(int exitCode) {
    }

    public static void main(String[] args) throws Exception {
        Map<String,Object> attributes = new HashMap<>();
        attributes.put(SystemConfig.INITIAL_CONFIGURATION_LOCATION, "D:\\projects\\aok\\core\\src\\main\\resources\\config.json");
        attributes.put(SystemConfig.TYPE, JsonSystemConfigImpl.SYSTEM_CONFIG_TYPE);
        startBroker(attributes);
    }
}
