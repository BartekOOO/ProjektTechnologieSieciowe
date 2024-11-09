package Interfaces;

import java.util.Dictionary;

public interface IUpdateData extends IInsertData {
    Dictionary<String,Object>GetUpdateParameters();
}
