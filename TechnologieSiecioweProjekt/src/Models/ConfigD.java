package Models;

import Interfaces.IConfig;

public class ConfigD implements IConfig {
    @Override
    public String GetUrl() {
        return "jdbc:sqlserver://BartekLap:1433;databaseName=ProjektTechnologieSieciowe;encrypt=true;trustServerCertificate=true;";
    }

    @Override
    public String GetUser() {
        return "test";
    }

    @Override
    public String GetPassword() {
        return "test";
    }
}
