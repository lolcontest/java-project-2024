package com.gamemanager.config;

import java.io.Serializable;

public class Configuration implements Serializable {
    private String configPath;
    private String dataPath;
    
    public String getConfigPath() { return configPath; }
    public void setConfigPath(String path) { this.configPath = path; }
    
    public String getDataPath() { return dataPath; }
    public void setDataPath(String path) { this.dataPath = path; }
}