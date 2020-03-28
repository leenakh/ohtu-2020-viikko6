package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class HasFewerThan implements Matcher {
    
    private int value;
    private String fieldName;
    
    public HasFewerThan(int value, String category) {
        this.value = value;
        this.fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
    }
    
    @Override
    public boolean matches(Player player) {
        try {
            Method method = player.getClass().getMethod(this.fieldName);
            int playersValue = (Integer)method.invoke(player);
            return playersValue < this.value;
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());
        }
    }
    
}
