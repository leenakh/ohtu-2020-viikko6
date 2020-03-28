package statistics.matcher;

public class QueryBuilder {
    
    Matcher query;
    
    public Matcher build() {
        return this.query;
    }
    
    public QueryBuilder playsIn(String team) {
        this.query = new PlaysIn(team);
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.query = new And(this.query, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.query = new And(this.query, new HasFewerThan(value, category));
        return this;
    }
    
}
