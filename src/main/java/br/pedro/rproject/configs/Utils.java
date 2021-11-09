package br.pedro.rproject.configs;

import br.pedro.rproject.models.dtos.HistoryDTO;
import br.pedro.rproject.models.dtos.PersonaDTO;
import br.pedro.rproject.models.embedded.Item;
import br.pedro.rproject.models.embedded.Seal;
import br.pedro.rproject.models.embedded.Spell;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utils {

    public static Map<String,Class<?>> getAvailableParams(){
        Map<String, Class<?>> classes = new HashMap<>();
        classes.put("profile",PersonaDTO.class);
        classes.put("items",Item.class);
        classes.put("spells",Spell.class);
        classes.put("seals",Seal.class);
        classes.put("history",HistoryDTO.class);
        classes.put("general",Map.class);
        classes.put("offensive",Map.class);
        classes.put("basic",Map.class);
        return classes;
    }

    public static String stringGenerate() {
        int leftLimit = 65;
        int rightLimit = 90;
        int targetStringLength = 7;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return  buffer.toString();
    }

    private static int diceRoll(){
        return 1 + (int) (Math.random() * 100);
    }

    public static String diceResult(int attribute){
        int dice = diceRoll();
        String result;

        if(dice <= attribute){
            result = "SUCESSO NORMAL";
            if(dice <= attribute/2){
                result = "SUCESSO SÓLIDO";
            }
            if(dice <= attribute/5){
                result = "SUCESSO EXTREMO";
            }
        } else {
            result = "FRACASSO";
            if(dice >= 98){
                result = "DESASTRE";
            }
        }
        return dice + "," + result;
    }

}
