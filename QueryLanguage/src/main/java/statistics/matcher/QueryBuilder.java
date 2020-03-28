package statistics.matcher;

public class QueryBuilder {
    
    Matcher query;
    
    public QueryBuilder() {
        this.query = new All();
    }
    
    public Matcher build() {
        Matcher palautettava = this.query;
        this.query = new All();
        return palautettava;
    }
    
    public QueryBuilder playsIn(String team) {
        this.query = new And(this.query, new PlaysIn(team));
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
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.query = new Or(matchers);
        return this;
    }
    
}
