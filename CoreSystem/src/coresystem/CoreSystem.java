package coresystem;

import java.util.HashMap;
import java.util.Map;

import plugininterface.DevicePlugin;

public class CoreSystem {
    
    private static final Map<String, String> pluginRegistry = new HashMap<>();

    static {
        
        pluginRegistry.put("iPhone", "pluginimpl.iPhonePlugin");
        pluginRegistry.put("Galaxy", "pluginimpl.GalaxyPlugin");
    }

    public static void main(String[] args) {
        
        assessDevice("iPhone");
        assessDevice("Galaxy");
        assessDevice("Unknown"); 
    }

    public static void assessDevice(String deviceID) {
        try {
            
            String pluginClassName = pluginRegistry.get(deviceID);
            if (pluginClassName == null) {
                System.out.println("No plugin found for device: " + deviceID);
                return;
            }

            
            Class<?> pluginClass = Class.forName(pluginClassName);

            
            DevicePlugin plugin = (DevicePlugin) pluginClass.getDeclaredConstructor().newInstance();

            
            plugin.assess();

        } catch (ClassNotFoundException e) {
            System.err.println("Plugin class not found for device: " + deviceID);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}