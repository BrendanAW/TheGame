package org.example;

import java.util.HashMap;
import java.util.Map;

import static org.example.Dialogue.*;

public class FighterController {
    Map<String, Fighter> dataMap = new HashMap<>();
    private static FighterController instance = new FighterController();

    private FighterController() {
    }

    static FighterController getInstance() {
        return instance;
    }

    void addFighter(String type, String name) {
        Fighter temp;
        for (String s : dataMap.keySet()) {
            if (s.equalsIgnoreCase(name))
                return;
        }
        switch (type) {
            case ARCHER:
                temp = new Archer(name);
                break;
            case TANK:
                temp = new Tank(name);
                break;
            case KNIGHT:
                temp = new Knight(name);
                break;
            default:
                return;
        }
        dataMap.put(name, temp);
    }

    void editFighter(String name, String changedName, String fighterType) {
        Fighter originalType = dataMap.get(name);
        Fighter newType = null;
        if (!fighterType.equalsIgnoreCase(originalType.toString())) {
            switch (fighterType) {
                case ARCHER:
                    newType = new Archer(changedName);
                    break;
                case KNIGHT:
                    newType = new Knight(changedName);
                    break;
                case TANK:
                    newType = new Tank(changedName);
                    break;
                default:
                    newType = originalType;
                    break;
            }
        } else newType = originalType;
        newType.setName(changedName);
        if (!name.equalsIgnoreCase(changedName)) {
            dataMap.remove(name);
            dataMap.put(changedName, newType);
        }


    }

    String getFighterType(String name) {
        for (String s : dataMap.keySet()) {
            if (s.equalsIgnoreCase(name))
                return dataMap.get(s).toString();
        }
        return null;
    }
}
