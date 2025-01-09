package com.gamemanager.config;

import java.io.Serializable;

public class Configuration implements Serializable {
    private String ip;
    private int port;
    private String dbName;
    private String configPath;
    private String asFilePath;
    
    // Constructeur par défaut
    public Configuration() {}
    
    // Getters et Setters
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    
    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }
    
    public String getDbName() { return dbName; }
    public void setDbName(String dbName) { this.dbName = dbName; }
    
    public String getConfigPath() { return configPath; }
    public void setConfigPath(String configPath) { this.configPath = configPath; }
    
    public String getAsFilePath() { return asFilePath; }
    public void setAsFilePath(String asFilePath) { this.asFilePath = asFilePath; }
    
    @Override
    public String toString() {
        return "Configuration{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", dbName='" + dbName + '\'' +
                ", configPath='" + configPath + '\'' +
                ", asFilePath='" + asFilePath + '\'' +
                '}';
    }
}