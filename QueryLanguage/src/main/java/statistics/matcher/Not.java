package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {
    
    private Matcher[] matchers;
    
    public Not(Matcher... matchers) {
        this.matchers = matchers;
    }
    
    @Override
    public boolean matches(Player player) {
        for (Matcher m : this.matchers) {
            if (m.matches(player)) {
                return false;
            }
        }
        return true;
    }
}
